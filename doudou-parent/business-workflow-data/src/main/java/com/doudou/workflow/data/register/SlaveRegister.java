package com.doudou.workflow.data.register;

/**
 * @author niubq
 * @date 2020/6/28 11:04
 * @description
 */
public class SlaveRegister {
    private Long regId;
    private String slaveName;
    private String url;
    private Long regTime;

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
}
