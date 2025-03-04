package com.pw.modular.sys.controller;

import com.pw.api.sys.service.SysUserService;
import com.pw.core.annotation.PwFetch;
import com.pw.core.annotation.PwRoute;
import com.pw.core.basic.response.PwResponse;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

@PwRoute
public class SysUserController {

    @Resource
    private SysUserService sysUserService;

    @PwFetch(url = "/sys/user/get", method = {RequestMethod.GET, RequestMethod.POST})
    public PwResponse get(){
        sysUserService.getSysUser();
        return PwResponse.success();
    }

}
