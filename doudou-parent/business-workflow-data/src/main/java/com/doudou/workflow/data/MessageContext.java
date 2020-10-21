package com.doudou.workflow.data;

/**
 * @author niubq
 * @date 2020/6/28 10:27
 * @description
 */
public class MessageContext extends SubscriberContext{
    /**
     * 消息交换机名称
     */
    private String exchangeName;
    /**
     * 路由地址
     */
    private String routingKey;

    public String getExchangeName() {
        return exchangeName;
    }

    public void setExchangeName(String exchangeName) {
        this.exchangeName = exchangeName;
    }

    public String getRoutingKey() {
        return routingKey;
    }

    public void setRoutingKey(String routingKey) {
        this.routingKey = routingKey;
    }
}
