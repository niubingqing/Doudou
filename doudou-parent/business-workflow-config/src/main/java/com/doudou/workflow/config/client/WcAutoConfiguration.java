package com.doudou.workflow.config.client;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author niubq
 * @date 2020/7/3 15:45
 * @description
 */
@Configuration
@EnableConfigurationProperties({WcConfigProperties.class})
@ConditionalOnProperty(prefix = "genoany.workflow.client", value = "enabled", matchIfMissing = true)
public class WcAutoConfiguration {
    private final WcConfigProperties wcConfigProperties;

    public WcAutoConfiguration(WcConfigProperties wcConfigProperties) {
        this.wcConfigProperties = wcConfigProperties;
    }
}
