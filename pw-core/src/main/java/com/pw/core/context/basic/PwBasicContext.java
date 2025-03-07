package com.pw.core.context.basic;

import com.pw.core.constants.context.PwContextConstants;
import lombok.Data;
import org.springframework.core.env.ConfigurableEnvironment;

@Data
public class PwBasicContext {

    private String active;
    private String port;
    private String application;

    public static PwBasicContext create(ConfigurableEnvironment environment) {
        PwBasicContext context = new PwBasicContext();
        context.setActive(environment.getProperty(PwContextConstants.BASIC_ACTIVE));
        context.setPort(environment.getProperty(PwContextConstants.BASIC_PORT));
        context.setApplication(environment.getProperty(PwContextConstants.BASIC_APPLICATION));
        return context;
    }

}
