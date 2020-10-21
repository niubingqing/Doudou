package com.newpwr.workflow.core.protocol;

/**
 * @author niubq
 * @date 2020/7/1 15:20
 * @description
 */
public abstract class AbstractResultMessage extends AbstractMessage  {

    private ResultCode resultCode;

    private String msg;

    /**
     * Gets result code.
     *
     * @return the result code
     */
    public ResultCode getResultCode() {
        return resultCode;
    }

    /**
     * Sets result code.
     *
     * @param resultCode the result code
     */
    public void setResultCode(ResultCode resultCode) {
        this.resultCode = resultCode;
    }

    /**
     * Gets msg.
     *
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * Sets msg.
     *
     * @param msg the msg
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

}