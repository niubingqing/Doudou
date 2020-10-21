package com.newpwr.workflow.core.netty;

import com.newpwr.workflow.core.protocol.AbstractMessage;

/**
 * @author niubq
 * @date 2020/7/1 15:09
 * @description
 */
public class RpcPoolKey {
    private RpcRole rpcRole;
    private String address;
    private AbstractMessage message;

    public RpcPoolKey(RpcRole rpcRole, String address, AbstractMessage message) {
        this.rpcRole = rpcRole;
        this.address = address;
        this.message = message;
    }

    public RpcPoolKey(RpcRole rpcRole, String address) {
        this.rpcRole = rpcRole;
        this.address = address;
    }


    /**
     * Gets get client role.
     *
     * @return the get client role
     */
    public RpcRole getRpcRole() {
        return rpcRole;
    }

    /**
     * Sets set client role.
     *
     * @param rpcRole the client role
     * @return the client role
     */
    public RpcPoolKey setRpcRole(RpcRole rpcRole) {
        this.rpcRole = rpcRole;
        return this;
    }

    /**
     * Gets get address.
     *
     * @return the get address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets set address.
     *
     * @param address the address
     * @return the address
     */
    public RpcPoolKey setAddress(String address) {
        this.address = address;
        return this;
    }

    /**
     * Gets message.
     *
     * @return the message
     */
    public AbstractMessage getMessage() {
        return message;
    }

    /**
     * Sets message.
     *
     * @param message the message
     */
    public void setMessage(AbstractMessage message) {
        this.message = message;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("workflow role:");
        sb.append(rpcRole.name());
        sb.append(",");
        sb.append("address:");
        sb.append(address);
        sb.append(",");
        sb.append("msg:< ");
        sb.append(message.toString());
        sb.append(" >");
        return sb.toString();
    }

    public enum RpcRole {
        LEADER(1),
        SLAVE(2),
        CLIENT(3);

        RpcRole(int value) {
            this.value = value;
        }

        /**
         * Gets value.
         *
         * @return value value
         */
        public int getValue() {
            return value;
        }

        /**
         * value
         */
        private int value;
    }
}
