package com.pw.api.sys.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pw.api.sys.entity.SysUser;

import java.util.List;

public interface SysUserService extends IService<SysUser> {

    List<SysUser> fetchByWrapper(LambdaQueryWrapper<SysUser> wrapper);

    List<SysUser> fetchSysUserByAccount(String account);

}
