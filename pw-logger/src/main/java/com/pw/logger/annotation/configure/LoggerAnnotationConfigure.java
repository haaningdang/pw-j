package com.pw.logger.annotation.configure;

import com.pw.core.util.HttpContextUtil;
import com.pw.logger.pojo.PwRequestDefinition;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

@Slf4j
@Aspect
public abstract class LoggerAnnotationConfigure {

    @Pointcut("@within(com.pw.logger.annotation.Logger) " +
            "|| @annotation(com.pw.logger.annotation.Logger) " +
            "|| @within(com.pw.core.annotation.PwFetch) " +
            "|| @annotation(com.pw.core.annotation.PwFetch)")
    public void cut(){}

    private void parseRequestPoint(PwRequestDefinition definition, ProceedingJoinPoint point) {
        try {
            MethodSignature signature = (MethodSignature) point.getSignature();
            Method current = point.getTarget().getClass().getMethod(signature.getName(), signature.getParameterTypes());
            definition.setClassName(point.getTarget().getClass().getName());
            definition.setMethodName(current.getName());

        }catch (Exception e){
            log.error("trace={}", e);
        }
    }

    private PwRequestDefinition create(ProceedingJoinPoint point){
        PwRequestDefinition definition = new PwRequestDefinition();
        definition.setStart(System.currentTimeMillis());
        parseRequestPoint(definition, point);

        definition.setMethod(HttpContextUtil.method());
        definition.setPath(HttpContextUtil.path());
        definition.setHeaders(HttpContextUtil.headers());
        try{
            definition.setParameters(HttpContextUtil.getParametersStr());
        }catch(Exception e){
            log.error("trace={}", e);
        }

        try{
            definition.setBody(HttpContextUtil.getRequestBodyStr());
        }catch(Exception e){
            log.error("trace={}", e);
        }
        return definition;
    }


    @Around("cut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        PwRequestDefinition definition = create(point);
        try{
            Object result = point.proceed();
            definition.setResponse(result);
            return result;
        }catch (Throwable throwable) {
            log.error("trace={}", throwable);
            definition.setTrace(throwable.getMessage());
            throw throwable;
        }finally {
            definition.setEnd(System.currentTimeMillis());
            record(definition);
        }
    }

    public abstract void record(PwRequestDefinition definition);

}
