package com.pw.api.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pw.api.sys.entity.SysRole;
import com.pw.api.sys.pojo.request.role.PageRequest;
import com.pw.api.sys.pojo.request.role.RoleRequest;
import com.pw.core.basic.response.PwResponse;

import java.util.List;

public interface SysRoleService extends IService<SysRole> {

    List<SysRole> fetchRoleByUserId(Long userId);

    PwResponse page(PageRequest request);

    PwResponse add(RoleRequest request);

    PwResponse select();

}
