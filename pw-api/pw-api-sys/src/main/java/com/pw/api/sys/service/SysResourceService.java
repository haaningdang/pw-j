package com.pw.api.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pw.api.sys.entity.SysResource;
import com.pw.api.sys.pojo.request.resource.ResourceRequest;
import com.pw.core.basic.response.PwResponse;

import java.util.List;

public interface SysResourceService extends IService<SysResource> {

    List<SysResource> fetchResourceByRoleId(List<String> roles);

    List<SysResource> fetchResource();

    PwResponse page();

    PwResponse resource();

    PwResponse fetchRoleResource(ResourceRequest request);

}
