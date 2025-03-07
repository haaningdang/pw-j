package com.pw.application.config.cache;

import cn.hutool.cache.impl.TimedCache;
import com.pw.cache.spec.PwCacheLocalSpec;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(prefix = "pw.cache", value = "open", havingValue = "local")
public class PwCacheLocalConfigure {

    @Bean
    public PwCacheLocalSpec<String> pwCacheLocalSpecConfigure() {
        return new PwCacheLocalSpec<String>(new TimedCache<>(900000L));
    }

}
