package com.doudou.workflow.core.protocol;

/**
 * @author niubq
 * @date 2020/7/1 13:49
 * @description
 */
public class MessageType {

    public static final short TYPE_REG_SLAVE_MSG = 1;

    public static final short TYPE_REG_SLAVE_RESULT = 2;
    /**
     * 客户端注册
     */
    public static final short TYPE_REG_CLIENT_MSG = 3;

    public static final short TYPE_REG_CLIENT_RESULT = 4;


    public static final short TYPE_RPC_CLIENT_MSG = 11;

    public static final short TYPE_RPC_CLIENT_RESULT = 12;
    /**
     * the constant TYPE_ELECTION_PRELIMINARY_MSG
     */
    public static final short TYPE_ELECTION_PRELIMINARY_MSG = 49;

    /**
     * the constant TYPE_ELECTION_MIDTERM_MSG
     */
    public static final short TYPE_ELECTION_MIDTERM_MSG = 97;

    /**
     * the constant TYPE_ELECTION_FINAL_MSG
     */
    public static final short TYPE_ELECTION_FINAL_MSG = 99;
    /**
     * the constant TYPE_HEARTBEAT_MSG
     */
    public static final short TYPE_HEARTBEAT_MSG = 120;

    public static final short TYPE_MERGE_MSG = 201;

    public static final short TYPE_MERGE_RESULT = 201;
}
