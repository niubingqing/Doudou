package com.newpwr.workflow.data;

/**
 * @author niubq
 * @date 2020/6/28 10:28
 * @description
 */
public class SubscirberData {
    /**
     * 调用的全路径类名
     */
    private String classFullName;
    /**
     * 调用的方法名
     */
    private String methodName;
    /**
     * 入参
     */
    private Object[] args;
    /**
     * 返回值
     */
    private Object returnValue;

    public String getClassFullName() {
        return classFullName;
    }

    public void setClassFullName(String classFullName) {
        this.classFullName = classFullName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    public Object getReturnValue() {
        return returnValue;
    }

    public void setReturnValue(Object returnValue) {
        this.returnValue = returnValue;
    }
}
