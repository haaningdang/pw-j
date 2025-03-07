package com.pw.application.config.validate;

import com.pw.validate.PwValidator;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AutoConfigureBefore(ValidationAutoConfiguration.class)
public class PwValidateConfigure {

    @Bean
    public PwValidator validator(){
        return new PwValidator();
    }

}
