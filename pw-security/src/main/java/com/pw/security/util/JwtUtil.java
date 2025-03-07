package com.pw.security.util;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.text.CharSequenceUtil;
import com.pw.core.util.RandomGenerator;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Map;
import java.util.Optional;


@Slf4j
public class JwtUtil {

    public static String generate(String iss, String secret, Map<String, Object> claims, int seconds){
        Date now = new Date();
        String timestamp = Convert.toStr(claims.get("timestamp"), "").trim();
        if(CharSequenceUtil.equals("", timestamp)){
            claims.put("timestamp", System.currentTimeMillis());
        }

        String random = Convert.toStr(claims.get("random"), "").trim();
        if(CharSequenceUtil.equals("", random)){
            claims.put("random", RandomGenerator.random(32));
        }

        JwtBuilder builder = Jwts.builder().setClaims(claims);
        if(seconds != -1){
            Date expire = DateUtil.offsetSecond(now, seconds);
            builder.setExpiration(expire);
        }
        return builder
                .setIssuer(iss)
                .setIssuedAt(now)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public static Claims claims(String token, String secret){
        log.error("secret={}", secret);
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }



    public static boolean expired(String token, String secret){
        Claims claims = claims(token, secret);
        Date date = claims.getExpiration();
        Date now = new Date();
        if(Optional.ofNullable(date).isEmpty()){
            return false;
        }
        return date.before(now);
    }

}
