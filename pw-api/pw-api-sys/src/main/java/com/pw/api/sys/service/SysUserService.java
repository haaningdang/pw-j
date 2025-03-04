package com.pw.api.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pw.api.sys.entity.SysUser;

public interface SysUserService extends IService<SysUser> {

    void getSysUser();

}
