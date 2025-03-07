package com.pw.application.config.web;

import com.pw.application.config.security.PwSecurityInterceptorConfigure;
import com.pw.core.context.PwApplicationContext;
import com.pw.core.filter.request.RequestReaderHttpServletRequestFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;


@Configuration
public class SpringMvcConfiguration implements WebMvcConfigurer {

    @Resource
    private PwSecurityInterceptorConfigure pwSecurityInterceptorConfigure;

    @Bean
    public FilterRegistrationBean httpServletRequestReplacedFilter(){
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new RequestReaderHttpServletRequestFilter());
        bean.addUrlPatterns("/*");
        bean.setName("httpServletRequestReplacedFilter");
        bean.setOrder(1);
        return bean;
    }


    // 过滤的路径通过配置正则配置
    // pw.security.excludes
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(pwSecurityInterceptorConfigure);
    }

}
