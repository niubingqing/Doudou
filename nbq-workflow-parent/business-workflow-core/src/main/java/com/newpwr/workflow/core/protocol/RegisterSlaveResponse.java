package com.newpwr.workflow.core.protocol;

import java.io.Serializable;

/**
 * @author niubq
 * @date 2020/7/2 14:40
 * @description
 */
public class RegisterSlaveResponse extends AbstractIdentifyResponse implements Serializable {
    @Override
    public short getTypeCode() {
        return MessageType.TYPE_REG_SLAVE_RESULT;
    }
}
