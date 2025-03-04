package com.pw.modular.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pw.api.sys.entity.SysUser;
import com.pw.api.sys.service.SysUserService;
import com.pw.modular.sys.mapper.SysUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Override
    public void getSysUser() {
        log.error("data={}", this.list());
        log.error("data={}", this.baseMapper.fetch());
    }

}
