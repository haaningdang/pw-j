package com.pw.application.config.login;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import com.pw.api.sys.entity.SysRole;
import com.pw.api.sys.service.SysRoleService;
import com.pw.cache.PwCacheApi;
import com.pw.core.context.PwApplicationContext;
import com.pw.core.exception.PwAuthException;
import com.pw.core.util.HttpContextUtil;
import com.pw.login.PwLoginApi;
import com.pw.login.pojo.PwLogin;
import com.pw.security.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class PwLoginSpec implements PwLoginApi<PwLogin> {

    @Resource
    private PwCacheApi<String> pwCacheApi;

    @Resource
    private SysRoleService sysRoleService;

    /**
     * 获取请求的token
     * @return
     */
    private String token(){
        String token = HttpContextUtil.header("authorization");
        if(StrUtil.isBlank(token)){
            throw new PwAuthException("用户登录过期，请重新登录");
        }

        return token;
    }

    /**
     * 获取token解析的内容
     * @param token
     * @return
     */
    private Map<String, Object> getClaims(String token){
        return JwtUtil.claims(token, PwApplicationContext.inst().getConfig().getSecret());
    }

    @Override
    public PwLogin context() {
        // 获取token
        String token = token();

        String loginString =pwCacheApi.get("LOGIN_"+token);
        if(StrUtil.isBlank(loginString)){
            throw new PwAuthException("用户登录过期，请重新登录");
        }

        try{
            return JSON.parseObject(loginString, new TypeReference<PwLogin>() {});
        }catch (Exception e){
            log.info("parse token error");
            throw new PwAuthException("用户登录过期，请重新登录");
        }
    }

    /**
     * 登录信息
     */
    @Override
    public void login(String token) {
        // 解析
        Map<String, Object> claims = getClaims(token);
        String account = Convert.toStr(claims.get("account"), "");
        Long id = Convert.toLong(claims.get("id"));

        // 获取用户角色
        List<SysRole> roles = sysRoleService.fetchRoleByUserId(id);

        // 获取用户资源
        // 获取用户按钮权限

        // 构造登录信息缓存
        PwLogin login = new PwLogin();
        login.setAccount(account);
        login.setId(id);
        login.setRole(roles.stream().map(SysRole::getId).collect(Collectors.toList()));
        login.setToken(token);

        // 设置缓存
        pwCacheApi.set("LOGIN_"+token, JSON.toJSONString(login), 2*60*60*1000+2*60*1000);
    }

    /**
     * 刷新用户登录信息
     */
    @Override
    public void refresh() {
        this.login(token());
    }

}
