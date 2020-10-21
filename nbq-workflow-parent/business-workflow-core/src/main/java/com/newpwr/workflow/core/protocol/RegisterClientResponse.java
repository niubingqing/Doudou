package com.newpwr.workflow.core.protocol;

import java.io.Serializable;

/**
 * @author niubq
 * @date 2020/7/2 11:23
 * @description
 */
public class RegisterClientResponse extends AbstractIdentifyResponse implements Serializable {
    /**
     * Instantiates a new Register rm response.
     */
    public RegisterClientResponse() {
        this(true);
    }

    /**
     * Instantiates a new Register rm response.
     *
     * @param result the result
     */
    public RegisterClientResponse(boolean result) {
        super();
        setIdentified(result);
    }

    @Override
    public short getTypeCode() {
        return MessageType.TYPE_REG_CLIENT_RESULT;
    }
}
