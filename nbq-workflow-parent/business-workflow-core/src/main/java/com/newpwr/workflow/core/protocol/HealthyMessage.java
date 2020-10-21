package com.newpwr.workflow.core.protocol;


import java.io.Serializable;

/**
 * @author niubq
 * @date 2020/7/1 13:47
 * @description
 */
public class HealthyMessage implements MessageTypeAware, Serializable {

    private static final long serialVersionUID = -985316399527884899L;
    private boolean ping = true;
    /**
     * The constant PING.
     */
    public static final HealthyMessage PING = new HealthyMessage(true);
    /**
     * The constant PONG.
     */
    public static final HealthyMessage PONG = new HealthyMessage(false);

    private HealthyMessage(boolean ping) {
        this.ping = ping;
    }

    @Override
    public short getTypeCode() {
        return MessageType.TYPE_HEARTBEAT_MSG;
    }

    @Override
    public String toString() {
        return this.ping ? "services ping" : "services pong";
    }

    public boolean isPing() {
        return ping;
    }

    public void setPing(boolean ping) {
        this.ping = ping;
    }

}
