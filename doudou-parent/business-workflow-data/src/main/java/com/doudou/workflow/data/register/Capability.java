package com.doudou.workflow.data.register;

/**
 * @author niubq
 * @date 2020/6/30 14:44
 * @description
 */
public class Capability {
    private String methodName;
    private String methodDesc;
    private String parameters;

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getMethodDesc() {
        return methodDesc;
    }

    public void setMethodDesc(String methodDesc) {
        this.methodDesc = methodDesc;
    }

    public String getParameters() {
        return parameters;
    }

    public void setParameters(String parameters) {
        this.parameters = parameters;
    }
}
