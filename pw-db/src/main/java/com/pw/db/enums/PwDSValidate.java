package com.pw.db.enums;

public enum PwDSValidate {
    MYSQL("select 1")
    ;

    private String validate;

    public String get(){
        return validate;
    }

    PwDSValidate(String validate) {
        this.validate = validate;
    }
}
