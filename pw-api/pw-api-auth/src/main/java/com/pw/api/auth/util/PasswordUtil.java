package com.pw.api.auth.util;

import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.StrUtil;
import com.pw.api.auth.pojo.dto.PwPassword;
import com.pw.core.enums.crypto.PwCrypto;
import com.pw.core.util.CryptoUtil;
import com.pw.core.util.RandomGenerator;

public class PasswordUtil {

    private static final String TEMPLATE = "password={};salt={};@pw.com";

    public static String generate(String password, String salt){
        return CryptoUtil.des(StrUtil.format(TEMPLATE, password, salt), salt, PwCrypto.ENCODE);
    }

    public static PwPassword generate(String password){
        String salt = RandomGenerator.random(16);
        return PwPassword.builder().password(password).salt(salt).crypto(generate(password, salt)).build();
    }

    public static boolean verify(String crypto, String password, String salt){
        try{
            return CharSequenceUtil.equals(crypto, generate(password, salt));
        }catch (Exception e){
            return false;
        }
    }

}
