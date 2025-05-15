package com.pw.modular.sys.controller;

import com.pw.api.sys.pojo.request.role.PageRequest;
import com.pw.api.sys.pojo.request.role.RoleRequest;
import com.pw.api.sys.pojo.request.role.RoleResourceRequest;
import com.pw.api.sys.service.SysRoleService;
import com.pw.core.annotation.PwFetch;
import com.pw.core.annotation.PwRoute;
import com.pw.core.basic.response.PwResponse;
import com.pw.security.annotation.PwSecurity;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;

@PwRoute
public class SysRoleController {

    @Resource
    private SysRoleService sysRoleService;

    @PwFetch(url = "/sys/role/page")
    @PwSecurity
    public PwResponse page(@RequestBody PageRequest request) {
        return sysRoleService.page(request);
    }

    @PwFetch(url = "/sys/role/add")
    @PwSecurity
    public PwResponse add(@RequestBody RoleRequest request) {
        return sysRoleService.add(request);
    }

    @PwFetch(url = "/sys/role/delete")
    @PwSecurity
    public PwResponse delete(@RequestBody RoleRequest request) {
        return sysRoleService.del(request);
    }

    @PwFetch(url = "/sys/role/select")
    @PwSecurity
    public PwResponse select() {
        return sysRoleService.select();
    }

    @PwFetch(url = "/sys/role/resource")
    @PwSecurity
    public PwResponse resource(@RequestBody RoleResourceRequest request) {
        return sysRoleService.resource(request);
    }

}
