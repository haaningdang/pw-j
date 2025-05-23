package com.pw.api.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pw.api.sys.entity.SysUserRole;

public interface SysUserRoleService extends IService<SysUserRole> {

    void saveUserRole(SysUserRole sysUserRole);

    void deleteByUserId(Long userId);

    void deleteByRoleId(Long roleId);

}
