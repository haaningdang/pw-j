package com.pw.modular.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pw.api.sys.entity.SysRoleResource;
import com.pw.api.sys.service.SysRoleResourceService;
import com.pw.modular.sys.mapper.SysRoleResourceMapper;
import org.springframework.stereotype.Service;

@Service
public class SysRoleResourceServiceImpl extends ServiceImpl<SysRoleResourceMapper, SysRoleResource> implements SysRoleResourceService {
}
