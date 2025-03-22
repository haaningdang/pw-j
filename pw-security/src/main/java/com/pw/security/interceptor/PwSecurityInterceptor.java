package com.pw.security.interceptor;

import com.pw.core.util.HttpContextUtil;
import com.pw.security.annotation.PwSecurity;
import com.pw.security.pojo.PwSecurityContext;
import com.pw.security.pojo.PwSecurityDefinition;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class PwSecurityInterceptor implements HandlerInterceptor {

    private final static String authorization = "authorization";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        PwSecurityDefinition definition = parseDefinition();
        // 获取token
        definition.setToken(HttpContextUtil.header(authorization));
        definition.setContext(context());

        if(handler instanceof HandlerMethod method) {
            if(method.hasMethodAnnotation(PwSecurity.class)){
                PwSecurity security = method.getMethodAnnotation(PwSecurity.class);
                if(security != null){
                    filter(definition);
                }
            }
        }

        defaultFilter(definition);

        return true;
    }

    private PwSecurityDefinition parseDefinition(){
        PwSecurityDefinition definition = new PwSecurityDefinition();
        definition.setUrl(HttpContextUtil.path());
        definition.setMethod(HttpContextUtil.method());
        definition.setHeaders(HttpContextUtil.headers());
        definition.setBody(HttpContextUtil.getRequestBodyStr());
        definition.setParameters(HttpContextUtil.getParametersStr());
        definition.setRequest(HttpContextUtil.getRequest());
        return definition;
    }

    public abstract PwSecurityContext context();

    public abstract void filter(PwSecurityDefinition definition);

    public abstract void defaultFilter(PwSecurityDefinition definition);
}
