package com.pw.application.config.context;

import com.pw.core.context.PwApplicationContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationContextInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;

@Slf4j
public class PwApplicationContextListener implements ApplicationListener<ApplicationContextInitializedEvent>, Ordered {

    @Override
    public void onApplicationEvent(ApplicationContextInitializedEvent event) {
        log.info("-----------------------------------------------------------------------");
        ConfigurableApplicationContext applicationContext = event.getApplicationContext();
        if(applicationContext instanceof AnnotationConfigApplicationContext){
            return;
        }

        ConfigurableEnvironment environment = event.getApplicationContext().getEnvironment();
        PwApplicationContext.inst().set(environment);
        log.info("-------------------- application context initial ----------------------");
        log.info("-----------------------------------------------------------------------");
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
