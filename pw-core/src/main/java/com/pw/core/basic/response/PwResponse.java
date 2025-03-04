package com.pw.core.basic.response;

import java.util.HashMap;

public class PwResponse extends HashMap<String, Object> {

    private final static int SUCCESS = 1;

    private final static int FAILURE = 0;

    private final static String MESSAGE = "success";

    public PwResponse(int code, String message) {
        this.put("code", code);
        this.put("message", message);
        this.put("timestamp", System.currentTimeMillis());
    }

    public PwResponse(int code, String message, Object data){
        this.put("code", code);
        this.put("message", message);
        this.put("data", data);
        this.put("timestamp", System.currentTimeMillis());
    }

    public static PwResponse success(){
        return new PwResponse(SUCCESS, MESSAGE);
    }

    public static PwResponse success(Object data){
        return new PwResponse(SUCCESS, MESSAGE, data);
    }

    public static PwResponse success(int code, String message, Object data){
        return new PwResponse(code, message, data);
    }

    public static PwResponse failure(String message){
        return new PwResponse(FAILURE, message);
    }

    public static PwResponse failure(int code, String message){
        return new PwResponse(code, message);
    }

}
