package com.pw.application.config.db;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.pw.application.config.db.mbplus.PwDefaultMetaObjectHandler;
import com.pw.core.bean.PwBeanNameGenerator;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = {"com.pw.**.mapper"}, nameGenerator = PwBeanNameGenerator.class)
public class MybatisPlusCustomConfiguration {

    @Bean
    public PaginationInnerInterceptor paginationInnerInterceptor(){
        return new PaginationInnerInterceptor();
    }


    @Bean
    public MetaObjectHandler metaObjectHandler(){
        return new PwDefaultMetaObjectHandler();
    }

}
