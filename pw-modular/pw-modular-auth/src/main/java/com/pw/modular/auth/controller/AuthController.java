package com.pw.modular.auth.controller;


import com.pw.api.auth.pojo.request.AuthRequest;
import com.pw.api.auth.service.AuthService;
import com.pw.core.annotation.PwFetch;
import com.pw.core.annotation.PwRoute;
import com.pw.core.basic.response.PwResponse;
import com.pw.security.annotation.PwSecurity;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;

@PwRoute
public class AuthController {
    @Resource
    private AuthService authService;

    @PwSecurity
    @PwFetch(url = "/auth/login")
    public PwResponse login(@RequestBody AuthRequest request) {
        return authService.login(request);
    }

    @PwSecurity
    @PwFetch(url = "/auth/logout")
    public PwResponse logout(@RequestBody AuthRequest request) {
        return authService.logout(request);
    }
}
