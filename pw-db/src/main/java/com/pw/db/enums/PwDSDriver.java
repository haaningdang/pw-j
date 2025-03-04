package com.pw.db.enums;

public enum PwDSDriver {
    MYSQL("com.mysql.cj.jdbc.Driver")
    ;

    private String driver;

    public String get(){
        return driver;
    }

    PwDSDriver(String driver) {
        this.driver = driver;
    }
}
