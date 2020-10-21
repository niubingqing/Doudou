package com.doudou.workflow.data.register;


import java.util.List;

/**
 * @author niubq
 * @date 2020/6/30 14:43
 * @description
 */
public class CapabilityClass {
    private String className;
    private String classDesc;
    private List<Capability> capabilities;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassDesc() {
        return classDesc;
    }

    public void setClassDesc(String classDesc) {
        this.classDesc = classDesc;
    }

    public List<Capability> getCapabilities() {
        return capabilities;
    }

    public void setCapabilities(List<Capability> capabilities) {
        this.capabilities = capabilities;
    }
}
