package com.doudou.workflow.core.protocol;

import java.io.Serializable;

/**
 * @author niubq
 * @date 2020/7/1 16:46
 * @description
 */
public class RegisterSlaveRequest extends AbstractMessage implements Serializable {

    private Long regId;
    private String slaveName;
    private String url;
    private Long regTime;
    private String applicationId;

    public Long getRegId() {
        return regId;
    }

    public void setRegId(Long regId) {
        this.regId = regId;
    }

    public String getSlaveName() {
        return slaveName;
    }

    public void setSlaveName(String slaveName) {
        this.slaveName = slaveName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getRegTime() {
        return regTime;
    }

    public void setRegTime(Long regTime) {
        this.regTime = regTime;
    }

    @Override
    public short getTypeCode() {
        return MessageType.TYPE_REG_SLAVE_MSG;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }
}
