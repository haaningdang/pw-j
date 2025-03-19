package com.pw.application.config.security;

import cn.hutool.core.convert.Convert;
import com.pw.api.auth.service.AuthService;
import com.pw.api.sys.entity.SysUser;
import com.pw.core.context.PwApplicationContext;
import com.pw.core.exception.PwAuthException;
import com.pw.security.interceptor.PwSecurityInterceptor;
import com.pw.security.pojo.PwSecurityContext;
import com.pw.security.pojo.PwSecurityDefinition;
import com.pw.security.util.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
@Slf4j
public class PwSecurityInterceptorConfigure extends PwSecurityInterceptor {

    @Resource
    private AuthService authService;

    @Override
    public PwSecurityContext context() {
        PwSecurityContext context = new PwSecurityContext();
        context.setKey(PwApplicationContext.inst().getConfig().getKey());
        context.setSecret(PwApplicationContext.inst().getConfig().getSecret());
        return context;
    }

    @Override
    public void filter(PwSecurityDefinition definition) {
        PwSecurityContext context = definition.getContext();
        if(context == null){
            throw new PwAuthException("系统环境异常");
        }

        String token = definition.getToken();
        try{
            Claims claims = JwtUtil.claims(token, context.getSecret());
            String account = Convert.toStr(claims.get("account"), "").trim();

            // 用户的校验
            SysUser user = authService.fetchSysUserByAccount(account);
            if (user == null) {
                throw new PwAuthException("用户的账号错误");
            }

            // 状态
            boolean flag = authService.validateSysUser(user);
            if (!flag) {
               throw new PwAuthException("用户账号状态异常");
            }

            // 用户权限
        }catch (Exception e){
            if(e instanceof PwAuthException){
                throw e;
            }
            throw new PwAuthException("授权过期，请重新登录");
        }
    }

    @Override
    public void defaultFilter(PwSecurityDefinition definition) {
        // 其他校验
    }

}
