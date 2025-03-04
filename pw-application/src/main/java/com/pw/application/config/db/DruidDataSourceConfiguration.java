package com.pw.application.config.db;

import com.pw.db.DataSourceRoutingConfigure;
import com.pw.db.annotation.configure.DSAnnotationConfigure;
import com.pw.db.factory.DruidDataSourceFactory;
import com.pw.db.properties.DataSourceProperties;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@AutoConfigureBefore({DataSourceAutoConfiguration.class})
@Import(DSAnnotationConfigure.class)
public class DruidDataSourceConfiguration {

    @Bean("mysqlProperties")
    @ConfigurationProperties(prefix = "spring.datasource.mysql")
    public DataSourceProperties mysqlProperties(){
        return new DataSourceProperties();
    }

    @Bean
    public DataSource multipleDataSource(){

        DataSource mysql = DruidDataSourceFactory.ds(mysqlProperties());

        DataSourceRoutingConfigure configure = new DataSourceRoutingConfigure();
        Map<Object, Object> configs = new HashMap<>();
        configs.put("mysql", mysql);
        configure.setTargetDataSources(configs);
        configure.setDefaultTargetDataSource(mysql);

        return configure;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource ds){
        return new JdbcTemplate(ds);
    }

}
