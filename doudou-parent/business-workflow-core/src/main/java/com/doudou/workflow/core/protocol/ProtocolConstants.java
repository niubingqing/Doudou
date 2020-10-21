
package com.doudou.workflow.core.protocol;

/**
 * @author niubq
 * @date 2020/7/1 15:07
 * @description
 */
public class ProtocolConstants {

    /**
     * Magic code
     */
    public static final byte[] MAGIC_CODE_BYTES = {(byte) 0xda, (byte) 0xda};

    /**
     * Protocol version
     */
    public static final byte VERSION = 1;

    /**
     * Max frame length
     */
    public static final int MAX_FRAME_LENGTH = 8 * 1024 * 1024;

    /**
     * HEAD_LENGTH of protocol v1
     */
    public static final int V1_HEAD_LENGTH = 16;
    
    /**
     * Message type: Request
     */
    public static final byte MSGTYPE_RESQUEST = 0;
    /**
     * Message type: Response
     */
    public static final byte MSGTYPE_RESPONSE = 1;
    /**
     * Message type: Request which no need response
     */
    public static final byte MSGTYPE_RESQUEST_ONEWAY = 2;
    /**
     * Message type: Heartbeat Request
     */
    public static final byte MSGTYPE_HEARTBEAT_REQUEST = 3;
    /**
     * Message type: Heartbeat Response
     */
    public static final byte MSGTYPE_HEARTBEAT_RESPONSE = 4;

     public static final byte MSGTYPE_NEGOTIATOR_REQUEST = 5;
     public static final byte MSGTYPE_NEGOTIATOR_RESPONSE = 6;
//
//    /**
//     * Configured codec by user, default is SEATA
//     *
//     * @see SerializerType#SEATA
//     */
//    public static final byte CONFIGURED_CODEC = SerializerType.getByName(ConfigurationFactory.getInstance()
//            .getConfig(ConfigurationKeys.SERIALIZE_FOR_RPC, SerializerType.SEATA.name())).getCode();
//
//    /**
//     * Configured compressor by user, default is NONE
//     *
//     * @see CompressorType#NONE
//     */
//    public static final byte CONFIGURED_COMPRESSOR = CompressorType.getByName(ConfigurationFactory.getInstance()
//            .getConfig(ConfigurationKeys.COMPRESSOR_FOR_RPC, CompressorType.NONE.name())).getCode();

}
