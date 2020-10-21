package com.newpwr.workflow.config.client;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

/**
 * @author niubq
 * @date 2020/7/3 15:37
 * @description
 */
@ConfigurationProperties(prefix = "genoany.workflow.client")
public class WcConfigProperties {
    private String host;
    private int port;
    private String applicationId;
    private String clientId;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
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
}
