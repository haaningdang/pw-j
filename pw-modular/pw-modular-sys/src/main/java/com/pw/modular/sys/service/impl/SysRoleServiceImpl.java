package com.pw.modular.sys.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pw.api.sys.entity.SysRole;
import com.pw.api.sys.pojo.request.role.PageRequest;
import com.pw.api.sys.service.SysRoleService;
import com.pw.core.basic.response.PwResponse;
import com.pw.mbp.page.PwPageUtil;
import com.pw.modular.sys.mapper.SysRoleMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Override
    public List<SysRole> fetchRoleByUserId(Long userId) {
        return this.baseMapper.fetchRoleByUserId(userId);
    }

    @Override
    public PwResponse page(PageRequest request) {
        Page<SysRole> page = this.baseMapper.page(PwPageUtil.wrapper(request), request);
        return PwResponse.success(PwPageUtil.response(page));
    }

}
