package com.pw.db.enums;

import lombok.Getter;

@Getter
public enum PwDS {

    MYSQL("MYSQL", "mysql", PwDSDriver.MYSQL.get(), PwDSUri.MYSQL.get(), PwDSValidate.MYSQL.get())
    ;

    private String name;

    private String keyword;

    private String driver;

    private String uri;

    private String validate;


    PwDS(String name, String keyword, String driver, String uri, String validate) {
        this.name = name;
        this.keyword = keyword;
        this.driver = driver;
        this.uri = uri;
        this.validate = validate;
    }

    public static PwDS getByUri(String uri){
        for (PwDS ds : values()) {
            if(uri.contains(ds.keyword)){
                return ds;
            }
        }
        return null;
    }

    public static String getValidateByUri(String uri) {
        if(getByUri(uri) == null){
            return "";
        }

        return getByUri(uri).getValidate();
    }

    public static String getDriverByUri(String uri) {
        if(getByUri(uri) == null){
            return "";
        }

        return getByUri(uri).getDriver();
    }



}
