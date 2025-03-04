package com.pw.db.util;

import cn.hutool.core.util.StrUtil;
import com.pw.db.enums.PwDS;
import com.pw.db.factory.DruidDataSourceFactory;
import com.pw.db.properties.DataSourceProperties;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class DatabaseUtil {

    public static String url(PwDS ds, String ip, int port, String db){
        return StrUtil.format(ds.getUri(), ip, port, db);
    }

    public static DataSource ds(PwDS ds, String ip, int port, String db, String username, String password){
        DataSourceProperties properties = new DataSourceProperties();
        properties.setUrl(url(ds, ip, port, db));
        properties.setUsername(username);
        properties.setPassword(password);
        return DruidDataSourceFactory.ds(properties);
    }

    public static JdbcTemplate template(DataSource ds){
        return new JdbcTemplate(ds);
    }

    public static JdbcTemplate template(PwDS ds, String ip, int port, String db, String username, String password){
        return new JdbcTemplate(ds(ds, ip, port, db, username, password));
    }

}
