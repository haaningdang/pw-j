package com.pw.core.context.config;

import cn.hutool.core.util.StrUtil;
import com.pw.core.constants.context.PwContextConstants;
import lombok.Data;
import org.springframework.core.env.ConfigurableEnvironment;

@Data
public class PwConfigContext {

    private String key;

    private String secret;

    private String[] excludes;

    public static PwConfigContext create(ConfigurableEnvironment environment) {
        PwConfigContext context = new PwConfigContext();
        context.setKey(environment.getProperty(PwContextConstants.PW_SECURITY_KEY));
        context.setSecret(environment.getProperty(PwContextConstants.PW_SECURITY_SECRET));
        String excludes = environment.getProperty(PwContextConstants.PW_SECURITY_EXCLUDES);
        if(StrUtil.isNotBlank(excludes)) {
            context.setExcludes(excludes.split(","));
        }
        return context;
    }

}
