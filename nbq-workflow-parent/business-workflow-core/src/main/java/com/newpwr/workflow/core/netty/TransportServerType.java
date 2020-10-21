package com.newpwr.workflow.core.netty;

/**
 * @author niubq
 * @date 2020/7/3 13:27
 * @description
 */
public enum TransportServerType {
    /**
     * Native transport server type.
     */
    NATIVE("native"),
    /**
     * Nio transport server type.
     */
    NIO("nio");

    /**
     * The Name.
     */
    public final String name;

    TransportServerType(String name) {
        this.name = name;
    }
}
