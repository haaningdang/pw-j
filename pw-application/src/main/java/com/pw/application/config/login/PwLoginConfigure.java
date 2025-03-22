package com.pw.application.config.login;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PwLoginConfigure {

    @Bean
    public PwLoginSpec configure(){
        return new PwLoginSpec();
    }

}
