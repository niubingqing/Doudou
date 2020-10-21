package com.doudou.workflow.common;

import java.nio.charset.Charset;

/**
 * @author niubq
 * @date 2020/7/1 15:00
 * @description
 */
public class Constants {
    public static final String IP_PORT_SPLIT_CHAR = ":";
    public static final String CLIENT_ID_SPLIT_CHAR = ":";
    public static final String ENDPOINT_BEGIN_CHAR = "/";
    public static final String DBKEYS_SPLIT_CHAR = ",";
    public static final String START_TIME = "start-time";
    public static final String APP_NAME = "appName";
    public static final String ACTION_START_TIME = "action-start-time";
    public static final String ACTION_NAME = "actionName";
    public static final String HOST_NAME = "host-name";
    public static final String METHOD_RESULT = "result";
    public static final String METHOD_ARGUMENTS = "arguments";
    public static final String ACTIVITY_CONTEXT = "activityContext";
    public static final String ACTION_CONTEXT = "actionContext";
    public static final String DEFAULT_CHARSET_NAME = "UTF-8";
    public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
    public static final String OBJECT_KEY_SPRING_APPLICATION_CONTEXT = "springApplicationContext";
    public static final String BEAN_NAME_SPRING_APPLICATION_CONTEXT_PROVIDER = "springApplicationContextProvider";
    public static final String BEAN_NAME_FAILURE_HANDLER = "failureHandler";
}
