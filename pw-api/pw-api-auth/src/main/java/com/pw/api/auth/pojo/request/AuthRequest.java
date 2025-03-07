package com.pw.api.auth.pojo.request;

import lombok.Data;

@Data
public class AuthRequest {

    private String account;

    private String password;

}
