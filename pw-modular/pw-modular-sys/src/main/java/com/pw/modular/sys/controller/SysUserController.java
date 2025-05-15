package com.pw.modular.sys.controller;

import com.pw.api.sys.pojo.request.user.PageRequest;
import com.pw.api.sys.pojo.request.user.UserRequest;
import com.pw.api.sys.service.SysUserService;
import com.pw.core.annotation.PwFetch;
import com.pw.core.annotation.PwRoute;
import com.pw.core.basic.response.PwResponse;
import com.pw.security.annotation.PwSecurity;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;

@PwRoute
public class SysUserController {

    @Resource
    private SysUserService sysUserService;


    @PwFetch(url = "/sys/user/page")
    @PwSecurity
    public PwResponse page(@RequestBody PageRequest request) {
        return sysUserService.page(request);
    }

    @PwFetch(url = "/sys/user/add")
    @PwSecurity
    public PwResponse add(@RequestBody UserRequest request) {
        return sysUserService.add(request);
    }

    @PwFetch(url = "/sys/user/delete")
    @PwSecurity
    public PwResponse delete(@RequestBody UserRequest request) {
        return sysUserService.del(request);
    }

    @PwFetch(url = "/sys/user/reset")
    @PwSecurity
    public PwResponse reset(@RequestBody UserRequest request) {
        return sysUserService.reset(request);
    }



}
