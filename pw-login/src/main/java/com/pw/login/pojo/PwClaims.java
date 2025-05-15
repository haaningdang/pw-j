package com.pw.login.pojo;

import com.pw.core.context.PwApplicationContext;
import com.pw.security.util.JwtUtil;

import java.util.HashMap;

public class PwClaims extends HashMap<String, Object> {

    public PwClaims(Long id, String account) {
        this.put("id", id);
        this.put("account", account);
    }

    /**
     * 登录生成token
     * @param id
     * @param account
     * @return
     */
    public static String login(Long id, String account){
        PwClaims claims = new PwClaims(id, account);
        return JwtUtil.generate(PwApplicationContext.inst().getConfig().getKey(),
                PwApplicationContext.inst().getConfig().getSecret(), claims, 2*60*60);
    }

}
