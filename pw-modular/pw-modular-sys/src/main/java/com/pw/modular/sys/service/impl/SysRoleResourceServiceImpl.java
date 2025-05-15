package com.pw.modular.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pw.api.sys.entity.SysRoleResource;
import com.pw.api.sys.pojo.request.role.RoleResourceRequest;
import com.pw.api.sys.service.SysRoleResourceService;
import com.pw.modular.sys.mapper.SysRoleResourceMapper;
import org.springframework.stereotype.Service;

@Service
public class SysRoleResourceServiceImpl extends ServiceImpl<SysRoleResourceMapper, SysRoleResource> implements SysRoleResourceService {


    @Override
    public void saveRoleResource(RoleResourceRequest request) {
        for(Long resource: request.getResources()){
            SysRoleResource sysRoleResource = new SysRoleResource();
            sysRoleResource.setRoleId(request.getRoleId());
            sysRoleResource.setResId(resource);
            this.baseMapper.insert(sysRoleResource);
        }
    }

    @Override
    public void deleteRoleResourceByRoleId(Long roleId) {
        this.baseMapper.deleteRoleResourceByRoleId(roleId);
    }
}
