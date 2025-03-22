package com.pw.modular.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pw.api.sys.entity.SysRole;
import com.pw.api.sys.service.SysRoleService;
import com.pw.modular.sys.mapper.SysRoleMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Override
    public List<SysRole> fetchRoleByUserId(Long userId) {
        return this.baseMapper.fetchRoleByUserId(userId);
    }

}
