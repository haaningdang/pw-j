package com.pw.security.pojo;

import lombok.Data;

import java.util.Map;

@Data
public class PwSecurityDefinition {

    private String url;

    private String method;

    private String token;

    private Map<String, String> headers;

    private String body;

    private String parameters;

    private Map<String, Object> request;

    private PwSecurityContext context;

}
