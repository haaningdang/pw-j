package com.pw.db.properties;

import lombok.Data;

@Data
public class DataSourceProperties {

    private String driverClassName;

    private String url;

    private String username;

    private String password;

    private Integer initialSize = 2;

    private Integer maxActive = 20;

    private Integer minIdle = 1;

    private Integer maxWait = 60000;

    private Boolean poolPreparedStatements = true;

    private Integer maxPoolPreparedStatementPerConnectionSize = 100;

    private String validationQuery;

    private Integer validationQueryTimeout = 10;

    private Boolean testOnBorrow = true;

    private Boolean testOnReturn = true;

    private Boolean testWhileIdle = true;

    private Boolean keepAlive = false;

    private Integer timeBetweenEvictionRunsMills = 60000;

    private Integer minEvictableIdleTimeMills = 300000;

    private String filters = "stat,wall";

}
