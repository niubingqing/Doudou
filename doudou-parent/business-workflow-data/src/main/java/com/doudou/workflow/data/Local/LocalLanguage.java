package com.doudou.workflow.data.Local;

/**
 * @author niubq
 * @date 2020/6/28 10:30
 * @description
 */
public class LocalLanguage {
    public static final String USER_ID = "User-Id";
    public static final String USER_NAME = "User-Name";
    public static final String COMPANY_ID = "Company-Id";
    public static final String REQUEST_TYPE = "Request-Type";
    public static final String APP_CODE = "Application-Code";
    public static final String DEFAULT_LANGUAGE = "zh_CN";
    public static final Long DEFAULT_USER_ID = 0L;
    public static final String DEFAULT_USER_NAME = "system";
    public static final Long DEFAULT_COMPANY_ID = 0L;
    public static final Integer DEFAULT_REQUEST_TYPE = 0;
    public static final String DEFAULT_APP_CODE = "";
    private Long userId;
    private String userName;
    private String acceptLanguage;
    private Long companyId;
    private Integer requestType;
    private String appCode;

    public Long getUserId() {
        return this.userId;
    }

    public String getUserName() {
        return this.userName;
    }

    public String getAcceptLanguage() {
        return this.acceptLanguage;
    }

    public Long getCompanyId() {
        return this.companyId;
    }

    public Integer getRequestType() {
        return this.requestType;
    }

    public String getAppCode() {
        return this.appCode;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setAcceptLanguage(String acceptLanguage) {
        this.acceptLanguage = acceptLanguage;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public void setRequestType(Integer requestType) {
        this.requestType = requestType;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }
}
