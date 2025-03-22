package com.pw.application.config.login;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import com.pw.cache.PwCacheApi;
import com.pw.core.util.HttpContextUtil;
import com.pw.login.PwLoginApi;
import com.pw.login.pojo.PwLogin;

import javax.annotation.Resource;
import java.util.Optional;

public class PwLoginSpec implements PwLoginApi<PwLogin> {

    @Resource
    private PwCacheApi<String> pwCacheApi;

    private String token(){
        String token = HttpContextUtil.header("authorization");
        return Optional.ofNullable(token).orElse("");
    }

    @Override
    public PwLogin context() {
        // 获取token
        String token = token();
        if(StrUtil.isBlank(token)){
            return null;
        }

        String loginString =pwCacheApi.get("LOGIN_"+token);
        if(StrUtil.isBlank(loginString)){
            return null;
        }

        try{
            return JSON.parseObject(loginString, new TypeReference<PwLogin>() {});
        }catch (Exception e){
            return null;
        }
    }

}
