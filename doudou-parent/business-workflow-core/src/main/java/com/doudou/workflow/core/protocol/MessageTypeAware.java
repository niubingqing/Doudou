package com.doudou.workflow.core.protocol;

/**
 * @author niubq
 * @date 2020/7/1 13:48
 * @description
 */
public interface MessageTypeAware {
    /**
     * return the message type
     * @return
     */
    short getTypeCode();
}
