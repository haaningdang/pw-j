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

    /**
     * 资源树
     * @return
     */
    @PwFetch(url = "/sys/resource/select")
    public PwResponse select() {
        return sysResourceService.select();
    }

    @PwFetch(url = "/sys/resource/add")
    public PwResponse add(@RequestBody ResourceRequest request) {
        return sysResourceService.add(request);
    }

    @PwFetch(url = "/sys/resource/update")
    public PwResponse update(@RequestBody ResourceRequest request) {
        return sysResourceService.update(request);
    }

    @PwFetch(url = "/sys/resource/delete")
    public PwResponse delete(@RequestBody ResourceRequest request) {
        return sysResourceService.delete(request);
    }

}
