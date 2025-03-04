package com.pw.db.factory;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.alibaba.druid.pool.DruidDataSource;
import com.pw.db.enums.PwDS;
import com.pw.db.properties.DataSourceProperties;
import lombok.extern.slf4j.Slf4j;

import java.sql.SQLException;

@Slf4j
public class DruidDataSourceFactory {

    private DruidDataSourceFactory(){}

    public static DruidDataSource ds(DataSourceProperties properties){
        DruidDataSource dataSource = new DruidDataSource();

        // uri为空的返回null
        if(StrUtil.isBlank(properties.getUrl())){
            return null;
        }

        String driver = Convert.toStr(properties.getDriverClassName(), "").trim();
        if(StrUtil.isBlank(driver)){
            driver = PwDS.getDriverByUri(properties.getUrl());
        }
        dataSource.setDriverClassName(driver);

        dataSource.setUrl(properties.getUrl());
        dataSource.setUsername(properties.getUsername());
        dataSource.setPassword(properties.getPassword());
        dataSource.setInitialSize(properties.getInitialSize());
        dataSource.setMaxActive(properties.getMaxActive());
        dataSource.setMinIdle(properties.getMinIdle());
        dataSource.setMaxWait(properties.getMaxWait());
        dataSource.setPoolPreparedStatements(properties.getPoolPreparedStatements());
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(properties.getMaxPoolPreparedStatementPerConnectionSize());

        String validate = Convert.toStr(properties.getValidationQuery(), "").trim();
        if(StrUtil.isBlank(validate)){
            validate = PwDS.getValidateByUri(properties.getUrl());
        }
        dataSource.setValidationQuery(validate);

        dataSource.setValidationQueryTimeout(properties.getValidationQueryTimeout());
        dataSource.setTestOnBorrow(properties.getTestOnBorrow());
        dataSource.setTestOnReturn(properties.getTestOnReturn());
        dataSource.setTestWhileIdle(properties.getTestWhileIdle());
        dataSource.setKeepAlive(properties.getKeepAlive());
        dataSource.setTimeBetweenEvictionRunsMillis(properties.getTimeBetweenEvictionRunsMills());
        dataSource.setMinEvictableIdleTimeMillis(properties.getMinEvictableIdleTimeMills());
        try{
            dataSource.setFilters(properties.getFilters());
        }catch (SQLException e){
            log.error("trace={}", e);
        }
        return dataSource;

    }

}
