package com.pw.api.sys.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pw.api.sys.entity.SysUser;
import com.pw.api.sys.pojo.request.user.PageRequest;
import com.pw.api.sys.pojo.request.user.UserRequest;
import com.pw.core.basic.response.PwResponse;

import java.util.List;

public interface SysUserService extends IService<SysUser> {

    List<SysUser> fetchByWrapper(LambdaQueryWrapper<SysUser> wrapper);

    List<SysUser> fetchSysUserByUserId(Long userId);

    List<SysUser> fetchSysUserByAccount(String account);

    /**
     * 用户分页
     * @param request
     * @return
     */
    PwResponse page(PageRequest request);

    /**
     * 新增用户
     * @param request
     * @return
     */
    PwResponse add(UserRequest request);

    /**
     * 删除用户
     * @param request
     * @return
     */
    PwResponse del(UserRequest request);

    /**
     * 重置密码
     * @param request
     * @return
     */
    PwResponse reset(UserRequest request);

}
