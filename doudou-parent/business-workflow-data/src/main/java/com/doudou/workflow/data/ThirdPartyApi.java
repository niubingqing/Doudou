package com.doudou.workflow.data;

import com.alibaba.fastjson.JSONObject;
import com.doudou.workflow.data.enums.HttpRequestTypeEnum;

/**
 * @author niubq
 * @date 2020/6/28 13:35
 * @description
 */
public class ThirdPartyApi {
    private String resourceName;
    /**
     * 请求类型
     */
    private HttpRequestTypeEnum requestType;
    /**
     * 是否为HTTP
     */
    private Boolean http;
    /**
     * host_port
     */
    private String hostPort;
    /**
     * request_url
     */
    private String requestUrl;
    /**
     * 入参json结构
     */
    private JSONObject inQueryJson;

    private JSONObject inHeaderJson;

    private JSONObject inBodyJson;
    /**
     * 出参json结构
     */
    private JSONObject outParamJson;
    /**
     * 描述
     */
    private String description;

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public HttpRequestTypeEnum getRequestType() {
        return requestType;
    }

    public void setRequestType(HttpRequestTypeEnum requestType) {
        this.requestType = requestType;
    }

    public Boolean getHttp() {
        return http;
    }

    public void setHttp(Boolean http) {
        this.http = http;
    }

    public String getHostPort() {
        return hostPort;
    }

    public void setHostPort(String hostPort) {
        this.hostPort = hostPort;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public JSONObject getInQueryJson() {
        return inQueryJson;
    }

    public void setInQueryJson(JSONObject inQueryJson) {
        this.inQueryJson = inQueryJson;
    }

    public JSONObject getInHeaderJson() {
        return inHeaderJson;
    }

    public void setInHeaderJson(JSONObject inHeaderJson) {
        this.inHeaderJson = inHeaderJson;
    }

    public JSONObject getInBodyJson() {
        return inBodyJson;
    }

    public void setInBodyJson(JSONObject inBodyJson) {
        this.inBodyJson = inBodyJson;
    }

    public JSONObject getOutParamJson() {
        return outParamJson;
    }

    public void setOutParamJson(JSONObject outParamJson) {
        this.outParamJson = outParamJson;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
