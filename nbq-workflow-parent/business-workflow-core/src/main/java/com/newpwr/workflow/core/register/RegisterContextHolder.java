package com.newpwr.workflow.core.register;

import com.newpwr.workflow.data.register.ClientRegister;
import com.newpwr.workflow.data.register.SlaveRegister;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author niubq
 * @date 2020/6/30 16:07
 * @description
 */
public class RegisterContextHolder {
    private final static ConcurrentHashMap<String, ClientRegister> CLIENT_REGISTER_CONTEXT_HOLDER = new ConcurrentHashMap<>(16);
    private final static ConcurrentHashMap<String, SlaveRegister> SLAVE_REGISTER_CONTEXT_HOLDER = new ConcurrentHashMap<>(16);

    public static void registSlave(String key, SlaveRegister slaveRegister) {
        synchronized (SLAVE_REGISTER_CONTEXT_HOLDER) {
            SLAVE_REGISTER_CONTEXT_HOLDER.put(key, slaveRegister);
        }
    }

    public static void registClient(String key, ClientRegister clientRegister) {
        synchronized (CLIENT_REGISTER_CONTEXT_HOLDER) {
            CLIENT_REGISTER_CONTEXT_HOLDER.put(key, clientRegister);
        }
    }
}
