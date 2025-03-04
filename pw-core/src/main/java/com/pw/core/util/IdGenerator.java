package com.pw.core.util;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.IdUtil;

import java.text.DecimalFormat;
import java.text.FieldPosition;
import java.text.NumberFormat;
import java.util.Date;

public class IdGenerator {

    private static final FieldPosition POSITION = new FieldPosition(0);

    private static final NumberFormat NUMERIC = new DecimalFormat("00");

    private static int seq = 0;

    private static final int MAX = 99;

    public static synchronized String create(){
        return uid(24);
    }

    public static synchronized String uid(int length){
        if(length < 20){
            length = 20;
        }
        StringBuffer buffer = new StringBuffer(length);
        buffer.append(DateUtil.format(new Date(), "yyyyMMddHHmmssSSS"));
        NUMERIC.format(seq, buffer, POSITION);
        if(seq == MAX){
            seq = 0;
        }else{
            seq ++;
        }
        int left = length - buffer.length();
        return buffer.append(RandomGenerator.numeric(left)).toString();
    }

    public static String uuid(){
        return UUID.fastUUID().toString();
    }

    public static String simpleUuid(){
        return uuid().replace("-", "");
    }

    public static Long snow(){
        Snowflake snowflake = IdUtil.getSnowflake();
        return snowflake.nextId();
    }

}
