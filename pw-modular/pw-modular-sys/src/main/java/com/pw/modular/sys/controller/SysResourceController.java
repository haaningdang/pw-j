package com.pw.modular.sys.controller;

import com.pw.api.sys.pojo.request.resource.ResourceRequest;
import com.pw.api.sys.service.SysResourceService;
import com.pw.core.annotation.PwFetch;
import com.pw.core.annotation.PwRoute;
import com.pw.core.basic.response.PwResponse;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;

@PwRoute
public class SysResourceController {

    @Resource
    private SysResourceService sysResourceService;

    /**
     * 资源分页
     * @return
     */
    @PwFetch(url = "/sys/resource/page")
    public PwResponse page() {
        return sysResourceService.page();
    }

    /**
     * 获取资源
     * @return
     */
    @PwFetch(url = "/sys/resource/tree")
    public PwResponse tree() {
        return sysResourceService.resource();
    }

    /**
     * 获取角色的资源
     * @param request
     * @return
     */
    @PwFetch(url = "/sys/resource/role")
    public PwResponse roleResource(@RequestBody ResourceRequest request) {
        return sysResourceService.fetchRoleResource(request);
    }

}
