package com.pw.application.config.logger;

import com.pw.logger.annotation.configure.LoggerAnnotationConfigure;
import com.pw.logger.pojo.PwRequestDefinition;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class LoggerConfiguration extends LoggerAnnotationConfigure {

    @Override
    public void record(PwRequestDefinition definition) {
        log.info("request={}", definition);
    }

}
