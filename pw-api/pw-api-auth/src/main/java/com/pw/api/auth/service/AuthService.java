package com.pw.api.auth.service;

import com.pw.api.auth.pojo.request.AuthRequest;
import com.pw.api.sys.entity.SysUser;
import com.pw.core.basic.response.PwResponse;

public interface AuthService {

    /**
     * 通过账号校验
     * @param account
     * @return
     */
    SysUser fetchSysUserByAccount(String account);

    /**
     * 校验用户状态
     * @param sysUser
     * @return
     */
    boolean validateSysUser(SysUser sysUser);

    PwResponse login(AuthRequest request);

    PwResponse logout(AuthRequest request);

}
