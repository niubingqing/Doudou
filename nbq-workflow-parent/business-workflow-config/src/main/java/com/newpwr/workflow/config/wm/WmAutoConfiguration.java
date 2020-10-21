package com.newpwr.workflow.config.wm;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author niubq
 * @date 2020/7/3 15:44
 * @description
 */
@Configuration
@EnableConfigurationProperties({WmConfigProperties.class})
@ConditionalOnProperty(prefix = "genoany.workflow.wm", value = "enabled", matchIfMissing = true)
public class WmAutoConfiguration {
    private final WmConfigProperties wmConfigProperties;

    public WmAutoConfiguration(WmConfigProperties wmConfigProperties) {
        this.wmConfigProperties = wmConfigProperties;
    }
}
