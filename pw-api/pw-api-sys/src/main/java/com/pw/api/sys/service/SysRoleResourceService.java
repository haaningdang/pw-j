package com.pw.api.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pw.api.sys.entity.SysRoleResource;
import com.pw.api.sys.pojo.request.role.RoleResourceRequest;

public interface SysRoleResourceService extends IService<SysRoleResource> {

    void saveRoleResource(RoleResourceRequest request);

    void deleteRoleResourceByRoleId(Long roleId);

}
