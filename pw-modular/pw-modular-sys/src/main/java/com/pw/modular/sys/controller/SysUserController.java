package com.pw.modular.sys.controller;

import com.pw.api.sys.service.SysUserService;
import com.pw.core.annotation.PwRoute;

import javax.annotation.Resource;

@PwRoute
public class SysUserController {

    @Resource
    private SysUserService sysUserService;



}
