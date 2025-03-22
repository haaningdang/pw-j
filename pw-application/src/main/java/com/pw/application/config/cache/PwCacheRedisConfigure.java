package com.pw.application.config.cache;

import com.pw.cache.spec.PwCacheRedisSpec;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(prefix = "pw.cache", value = "open", havingValue = "redis")
public class PwCacheRedisConfigure {

    @Bean
    public PwCacheRedisSpec<String> pwCacheRedisSpecConfigure() {
        return new PwCacheRedisSpec<String>("pw_",null);
    }

}
