package com.pw.modular.sys.controller;

import com.pw.api.sys.pojo.request.role.PageRequest;
import com.pw.api.sys.service.SysRoleService;
import com.pw.core.annotation.PwFetch;
import com.pw.core.annotation.PwRoute;
import com.pw.core.basic.response.PwResponse;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;

@PwRoute
public class SysRoleController {

    @Resource
    private SysRoleService sysRoleService;

    @PwFetch(url = "/sys/role/page")
    public PwResponse page(@RequestBody PageRequest request) {
        return sysRoleService.page(request);
    }

}
