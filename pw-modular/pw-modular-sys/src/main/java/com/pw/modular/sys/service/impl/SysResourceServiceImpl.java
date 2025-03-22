package com.pw.modular.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pw.api.sys.entity.SysResource;
import com.pw.api.sys.service.SysResourceService;
import com.pw.modular.sys.mapper.SysResourceMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysResourceServiceImpl extends ServiceImpl<SysResourceMapper, SysResource> implements SysResourceService {

    @Override
    public List<SysResource> fetchResourceByRoleId(List<String> roles) {
        return this.baseMapper.fetchResourceByRoleId(roles);
    }

}
