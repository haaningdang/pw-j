package com.pw.api.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pw.api.sys.entity.SysResource;
import com.pw.api.sys.entity.SysRole;

import java.util.List;

public interface SysRoleService extends IService<SysRole> {

    List<SysRole> fetchRoleByUserId(Long userId);

}
