package com.pw.db.context;

public class DataSourceContext {

    private static final ThreadLocal<String> CONTEXT = new InheritableThreadLocal<>();

    public static void set(String code){
        CONTEXT.set(code);
    }

    public static String get(){
        return CONTEXT.get();
    }

    public static void clear(){
        CONTEXT.remove();
    }

}
