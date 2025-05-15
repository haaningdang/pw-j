package com.pw.modular.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pw.api.sys.entity.SysUser;
import com.pw.api.sys.pojo.request.user.PageRequest;
import com.pw.api.sys.service.SysUserService;
import com.pw.core.basic.response.PwResponse;
import com.pw.mbp.page.PwPageUtil;
import com.pw.modular.sys.mapper.SysUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {


    @Override
    public List<SysUser> fetchByWrapper(LambdaQueryWrapper<SysUser> wrapper) {
        return this.list(wrapper);
    }

    private LambdaQueryWrapper<SysUser> createWrapperFetchSysUser(Long id) {
        return new LambdaQueryWrapper<SysUser>().eq(SysUser::getId, id);
    }

    @Override
    public List<SysUser> fetchSysUserByUserId(Long userId) {
        return fetchByWrapper(createWrapperFetchSysUser(userId));
    }

    private LambdaQueryWrapper<SysUser> createWrapperFetchSysUserByAccount(String account) {
        return new LambdaQueryWrapper<SysUser>().eq(SysUser::getAccount, account);
    }

    @Override
    public List<SysUser> fetchSysUserByAccount(String account) {
        return fetchByWrapper(createWrapperFetchSysUserByAccount(account));
    }

    @Override
    public PwResponse page(PageRequest request) {
        Page<SysUser> page = this.baseMapper.page(PwPageUtil.wrapper(request), request);
        return PwResponse.success(PwPageUtil.response(page));
    }
}
