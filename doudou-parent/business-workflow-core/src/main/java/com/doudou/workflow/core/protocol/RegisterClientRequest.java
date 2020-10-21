package com.doudou.workflow.core.protocol;

import com.doudou.workflow.data.register.CapabilityClass;

import java.io.Serializable;
import java.util.List;

/**
 * @author niubq
 * @date 2020/7/1 16:41
 * @description
 */
public class RegisterClientRequest extends AbstractMessage implements Serializable {

    /**
     * Instantiates a new Register rm request.
     */
    public RegisterClientRequest() { }

    private Long regId;
    private String clientName;
    private String url;
    private Long regTime;
    private List<CapabilityClass> capabilities;
    private String applicationId;
    private String serverAddress;

    public String getServerAddress() {
        return serverAddress;
    }

    public void setServerAddress(String serverAddress) {
        this.serverAddress = serverAddress;
    }

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

    @Override
    public short getTypeCode() {
        return MessageType.TYPE_REG_CLIENT_MSG;
    }

    @Override
    public String toString() {
        return "RegisterClientRequest{" +
                "regId='" + regId + '\'' +
                ", clientName='" + clientName + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }
}
