package com.pw.core.annotation;


import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@RestController
public @interface PwRoute {

    // 模块名称
    String value() default "";

}
