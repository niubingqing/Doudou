package com.newpwr.workflow.data;

import com.alibaba.fastjson.JSONObject;

/**
 * @author niubq
 * @date 2020/6/28 13:35
 * @description
 */
public class SqlResource {
    /**
     * 数据库连接名称
     */
    private String connectionName;
    /**
     * 数据库类型
     */
    private Integer dbType;
    /**
     * 服务器地址
     */
    private String serverAddress;
    /**
     * 服务名称
     */
    private String serviceName;
    /**
     * 服务端口号
     */
    private Integer servicePort;
    /**
     * 数据库名称
     */
    private String dbName;
    /**
     * 用户名称
     */
    private String userName;
    /**
     * 用户密码
     */
    private String userPassword;
    /**
     * 资源名称
     */
    private String resourceName;
    /**
     * 数据库连接ID
     */
    private Long dbConnId;
    /**
     * SQL语句
     */
    private String sqlContent;
    /**
     * SQL入参JSON结构
     */
    private JSONObject sqlParamJson;
    /**
     * SQL执行结果的json结构
     */
    private JSONObject sqlJsonResult;
    /**
     * 描述
     */
    private String description;

    public String getConnectionName() {
        return connectionName;
    }

    public void setConnectionName(String connectionName) {
        this.connectionName = connectionName;
    }

    public Integer getDbType() {
        return dbType;
    }

    public void setDbType(Integer dbType) {
        this.dbType = dbType;
    }

    public String getServerAddress() {
        return serverAddress;
    }

    public void setServerAddress(String serverAddress) {
        this.serverAddress = serverAddress;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Integer getServicePort() {
        return servicePort;
    }

    public void setServicePort(Integer servicePort) {
        this.servicePort = servicePort;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public Long getDbConnId() {
        return dbConnId;
    }

    public void setDbConnId(Long dbConnId) {
        this.dbConnId = dbConnId;
    }

    public String getSqlContent() {
        return sqlContent;
    }

    public void setSqlContent(String sqlContent) {
        this.sqlContent = sqlContent;
    }

    public JSONObject getSqlParamJson() {
        return sqlParamJson;
    }

    public void setSqlParamJson(JSONObject sqlParamJson) {
        this.sqlParamJson = sqlParamJson;
    }

    public JSONObject getSqlJsonResult() {
        return sqlJsonResult;
    }

    public void setSqlJsonResult(JSONObject sqlJsonResult) {
        this.sqlJsonResult = sqlJsonResult;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
