package com.doudou.workflow.data;

import com.alibaba.fastjson.JSONObject;
import com.doudou.workflow.data.enums.HttpRequestTypeEnum;

/**
 * @author niubq
 * @date 2020/6/28 13:33
 * @description
 */
public class MSApi {
    /**
     * 接口描述
     */
    private String description;
    /**
     * 微服务名称;微服务名（微服务名+版本号）
     */
    private String serviceName;

    /**
     * controller全路径
     */
    private String className;

    /**
     * 请求路径
     */
    private String requestMapping;

    /**
     * api路径
     */
    private String pathMapping;

    /**
     * api方式
     */
    private HttpRequestTypeEnum httpRequest;

    /**
     * 中文名
     */
    private String aliasName;

    /**
     * 方法名
     */
    private String methodName;

    /**
     * 入参
     */
    private JSONObject inQueryJson;

    /**
     * 入参
     */
    private JSONObject inBodyJson;
    /**
     * 出参
     */
    private JSONObject outParamJson;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getRequestMapping() {
        return requestMapping;
    }

    public void setRequestMapping(String requestMapping) {
        this.requestMapping = requestMapping;
    }

    public String getPathMapping() {
        return pathMapping;
    }

    public void setPathMapping(String pathMapping) {
        this.pathMapping = pathMapping;
    }

    public HttpRequestTypeEnum getHttpRequest() {
        return httpRequest;
    }

    public void setHttpRequest(HttpRequestTypeEnum httpRequest) {
        this.httpRequest = httpRequest;
    }

    public String getAliasName() {
        return aliasName;
    }

    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public JSONObject getInQueryJson() {
        return inQueryJson;
    }

    public void setInQueryJson(JSONObject inQueryJson) {
        this.inQueryJson = inQueryJson;
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
}
