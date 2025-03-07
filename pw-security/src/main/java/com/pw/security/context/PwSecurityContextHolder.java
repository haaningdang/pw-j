package com.pw.security.context;

import com.pw.security.pojo.PwSecurityContext;

public class PwSecurityContextHolder {

    private static final ThreadLocal<PwSecurityContext> CONTEXT = new InheritableThreadLocal<>();

    public static void set(PwSecurityContext context){
        CONTEXT.set(context);
    }

    public static PwSecurityContext get(){
        return CONTEXT.get();
    }

    public static void clear(){
        CONTEXT.remove();
    }

}
