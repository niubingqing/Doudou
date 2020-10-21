package com.doudou.workflow.data.register;

import java.util.List;

/**
 * @author niubq
 * @date 2020/6/28 11:04
 * @description
 */
public class ClientRegister {
    private Long regId;
    private String clientName;
    private String url;
    private Long regTime;
    private List<CapabilityClass> capabilities;

    public Long getRegId() {
        return regId;
    }

    public void setRegId(Long regId) {
        this.regId = regId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<CapabilityClass> getCapabilities() {
        return capabilities;
    }

    public void setCapabilities(List<CapabilityClass> capabilities) {
        this.capabilities = capabilities;
    }

    public Long getRegTime() {
        return regTime;
    }

    public void setRegTime(Long regTime) {
        this.regTime = regTime;
    }

}
