package com.pw.modular.auth.controller;


import com.pw.api.auth.pojo.request.AuthRequest;
import com.pw.api.auth.service.AuthService;
import com.pw.core.annotation.PwFetch;
import com.pw.core.annotation.PwRoute;
import com.pw.core.basic.response.PwResponse;
import com.pw.security.annotation.PwSecurity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;

@PwRoute
public class AuthController {
    @Resource
    private AuthService authService;

    @PwFetch(url = "/auth/login")
    public PwResponse login(@Validated(AuthRequest.login.class) @RequestBody AuthRequest request) {
        return authService.login(request);
    }

    @PwSecurity
    @PwFetch(url = "/auth/logout")
    public PwResponse logout(@RequestBody AuthRequest request) {
        return authService.logout(request);
    }

    @PwSecurity
    @PwFetch(url = "/auth/permission")
    public PwResponse permission() {
        return authService.permission();
    }

    @PwSecurity
    @PwFetch(url = "/auth/menu")
    public PwResponse menu() {
        return authService.menu();
    }
}
