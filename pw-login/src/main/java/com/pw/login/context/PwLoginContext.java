package com.pw.login.context;

import cn.hutool.extra.spring.SpringUtil;
import com.pw.login.PwLoginApi;

public class PwLoginContext {

    public static <T> PwLoginApi<T> inst(){
        return SpringUtil.getBean(PwLoginApi.class);
    }

}
