package com.pw.modular.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pw.api.sys.entity.SysUserRole;
import com.pw.api.sys.service.SysUserRoleService;
import com.pw.modular.sys.mapper.SysUserRoleMapper;
import org.springframework.stereotype.Service;

@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService {

    @Override
    public void saveUserRole(SysUserRole sysUserRole) {
        this.save(sysUserRole);
    }

    @Override
    public void deleteByUserId(Long userId) {
        this.baseMapper.deleteByUserId(userId);
    }

    @Override
    public void deleteByRoleId(Long roleId) {
        this.baseMapper.deleteByRoleId(roleId);
    }

}
