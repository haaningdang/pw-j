package com.pw.core.util;

import cn.hutool.core.util.RandomUtil;
import com.pw.core.enums.random.PwRandom;

public class RandomGenerator {

    private static String getTemplate(PwRandom random){
        if(random.equals(PwRandom.NUMERIC)){
            return RandomUtil.BASE_NUMBER;
        }
        if(random.equals(PwRandom.UPPER)){
            return RandomUtil.BASE_CHAR.toUpperCase();
        }
        if(random.equals(PwRandom.LOWER)){
            return RandomUtil.BASE_CHAR;
        }
        if(random.equals(PwRandom.SPEC)){
            return "~!@#$%^&*";
        }
        return "";
    }

    public static String getTemplates(PwRandom... randoms){
        if(randoms.length == 0){
            return "";
        }
        String template = "";
        for(PwRandom random : randoms){
            template += getTemplate(random);
        }
        return template;
    }

    public static String random(int length, String template){
        return RandomUtil.randomString(template, length);
    }

    public static String random(int length, PwRandom... randoms){
        return random(length, getTemplates(randoms));
    }

    public static String random(int length){
        return random(length, PwRandom.NUMERIC, PwRandom.UPPER, PwRandom.LOWER);
    }

    public static String numeric(int length){
        return random(length, PwRandom.NUMERIC);
    }

}
