package com.pw.core.context.ds;

import com.pw.core.constants.context.PwContextConstants;
import lombok.Data;
import org.springframework.core.env.ConfigurableEnvironment;

@Data
public class PwDsContext {

    private String url;
    private String username;
    private String password;
    private String driver;

    public static PwDsContext create(ConfigurableEnvironment environment) {
        PwDsContext context = new PwDsContext();
        context.setUrl(environment.getProperty(PwContextConstants.DS_URL));
        context.setDriver(environment.getProperty(PwContextConstants.DS_DRIVER));
        context.setUsername(environment.getProperty(PwContextConstants.DS_USERNAME));
        context.setPassword(environment.getProperty(PwContextConstants.DS_PASSWORD));
        return context;
    }

}
