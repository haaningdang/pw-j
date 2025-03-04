package com.pw.core.annotation;


import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@RequestMapping
public @interface PwFetch {

    // 路由名称
    String name() default "";

    // 路由路径
    @AliasFor( value = "value", annotation = RequestMapping.class)
    String value() default "";

    // 路由路径
    @AliasFor( value = "value", annotation = RequestMapping.class)
    String url() default "";

    // 路由请求方法
    @AliasFor(annotation = RequestMapping.class)
    RequestMethod[] method() default {RequestMethod.POST};

}
