package com.pw.api.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pw.api.sys.entity.SysResource;

import java.util.List;

public interface SysResourceService extends IService<SysResource> {

    List<SysResource> fetchResourceByRoleId(List<String> roles);

}
