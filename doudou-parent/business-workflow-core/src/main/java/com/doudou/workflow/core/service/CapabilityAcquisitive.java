package com.doudou.workflow.core.service;

import com.doudou.workflow.core.annotaition.TargetClass;
import com.doudou.workflow.core.annotaition.TargetMethod;
import com.doudou.workflow.core.util.JsonUtil;
import com.doudou.workflow.data.register.Capability;
import com.doudou.workflow.data.register.CapabilityClass;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author niubq
 * @date 2020/6/30 13:35
 * @description
 */
public interface CapabilityAcquisitive {
    default CapabilityClass acquisityCapability(Class clazz) {
        if (!clazz.isAnnotationPresent(TargetClass.class)) {
            return null;
        }

        TargetClass targetClass = this.getClass().getAnnotation(TargetClass.class);
        CapabilityClass capabilityClass = new CapabilityClass();
        capabilityClass.setClassName(clazz.getName());
        capabilityClass.setClassDesc(targetClass.value());

        Method[] methods = clazz.getDeclaredMethods();
        List<Capability> capabilities = new ArrayList<>(methods.length);
        for (Method method : methods) {
            if (!method.isAnnotationPresent(TargetMethod.class)) {
                TargetMethod targetMethod = method.getAnnotation(TargetMethod.class);
                Capability capability = new Capability();
                capability.setMethodName(targetMethod.value());
                capability.setMethodDesc(targetMethod.desc());
                capability.setParameters(JsonUtil.parse(targetMethod.params()));
                capabilities.add(capability);
            }
        }
        capabilityClass.setCapabilities(capabilities);

        return capabilityClass;
    }
}
