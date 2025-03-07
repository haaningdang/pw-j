package com.pw.modular.auth.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapBuilder;
import com.pw.api.auth.pojo.request.AuthRequest;
import com.pw.api.auth.service.AuthService;
import com.pw.api.sys.entity.SysUser;
import com.pw.api.sys.service.SysUserService;
import com.pw.core.basic.response.PwResponse;
import com.pw.core.context.PwApplicationContext;
import com.pw.security.util.JwtUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AuthServiceImpl implements AuthService {

    @Resource
    private SysUserService sysUserService;

    @Override
    public PwResponse login(AuthRequest request) {
        // 查询用户账号是否存在
        List<SysUser> sysUsers = sysUserService.fetchSysUserByAccount(request.getAccount());
        if(CollUtil.isEmpty(sysUsers)){
            return PwResponse.failure("用户账号或者密码错误");
        }

        // 查询用户密码是否正确

        // 查询用户状态是否正确
        int flag = sysUsers.get(0).getFlag();
        if(flag != 1){
            return PwResponse.failure("用户状态异常");
        }

        // 构建token
        Map<String, Object> claims = new HashMap<>();
        claims.put("account", request.getAccount());
        String token = JwtUtil.generate(PwApplicationContext.inst().getConfig().getKey(),
                PwApplicationContext.inst().getConfig().getSecret(), claims, 2*60*60);

        // 返回内容
        return PwResponse.success(MapBuilder.create()
                .put("account", request.getAccount())
                .put("token", token).build());
    }

    @Override
    public PwResponse logout(AuthRequest request) {
        return PwResponse.success();
    }

}
