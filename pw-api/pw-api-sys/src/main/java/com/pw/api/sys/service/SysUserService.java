package com.pw.api.sys.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pw.api.sys.entity.SysUser;
import com.pw.api.sys.pojo.request.user.PageRequest;
import com.pw.core.basic.response.PwResponse;

import java.util.List;

public interface SysUserService extends IService<SysUser> {

    List<SysUser> fetchByWrapper(LambdaQueryWrapper<SysUser> wrapper);

    List<SysUser> fetchSysUserByUserId(Long userId);

    List<SysUser> fetchSysUserByAccount(String account);

    PwResponse page(PageRequest request);

}
