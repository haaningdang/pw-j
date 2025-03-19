package com.pw.api.auth.pojo.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AuthRequest {

    @NotBlank(groups = {login.class}, message = "账号不能为空")
    private String account;

    @NotBlank(groups = {login.class}, message = "密码不能为空")
    private String password;

    public interface login{};
}
