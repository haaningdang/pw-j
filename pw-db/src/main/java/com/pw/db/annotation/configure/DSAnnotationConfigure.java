package com.pw.db.annotation.configure;

import com.pw.db.annotation.DS;
import com.pw.db.context.DataSourceContext;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class DSAnnotationConfigure {

    @Pointcut("@within(com.pw.db.annotation.DS) || @annotation(com.pw.db.annotation.DS)")
    public void cut(){}

    @Before("cut() && @annotation(code)")
    public void before(DS code){
        DataSourceContext.set(code.value());
    }

    @After("cut()")
    public void after(){
        DataSourceContext.clear();
    }

}
