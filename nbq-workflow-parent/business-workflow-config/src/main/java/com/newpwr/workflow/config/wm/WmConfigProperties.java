package com.newpwr.workflow.config.wm;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Set;

/**
 * @author niubq
 * @date 2020/7/3 15:37
 * @description
 */
@ConfigurationProperties(prefix = "genoany.workflow.wm")
public class WmConfigProperties {
    private Set<String> group;
    private String applicationId;
    private String clientId;

    private CacheMode cacheMode;

    public Set<String> getGroup() {
        return group;
    }

    public void setGroup(Set<String> group) {
        this.group = group;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public CacheMode getCacheMode() {
        return cacheMode;
    }

    public void setCacheMode(CacheMode cacheMode) {
        this.cacheMode = cacheMode;
    }
}
