package com.newpwr.workflow.core.netty;

/**
 * @author niubq
 * @date 2020/7/3 13:27
 * @description
 */
public enum TransportProtocolType {
    /**
     * Tcp transport protocol type.
     */
    TCP("tcp"),

    /**
     * Udt transport protocol type.
     */
    UDT("udt"),
    /**
     * Unix domain socket transport protocol type.
     */
    UNIX_DOMAIN_SOCKET("unix-domain-socket");

    /**
     * The Name.
     */
    public final String name;

    TransportProtocolType(String name) {
        this.name = name;
    }
}
