package com.pw.db.enums;

import cn.hutool.core.util.StrUtil;

public enum PwDSUri {

    MYSQL("jdbc:mysql://{ip}:{port}/{db}?autoReconnect=true&useUnicode=true&charsetEncoding=utf8&useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true")
    ;

    private String uri;

    public String get(){
        return this.uri;
    }

    PwDSUri(String uri) {
        this.uri = uri;
    }

    public String format(String ip, int port, String db){
        return StrUtil.format(get(), ip, port, db);
    }

}
