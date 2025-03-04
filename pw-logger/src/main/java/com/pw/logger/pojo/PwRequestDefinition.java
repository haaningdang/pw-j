package com.pw.logger.pojo;

import lombok.Data;

import java.util.Map;

@Data
public class PwRequestDefinition {

    private String className;

    private String methodName;

    private String body;

    private String parameters;

    private Object response;

    private String trace;

    private String path;

    private String method;

    private Map<String, String> headers;

    private long start;

    private long end;

}
