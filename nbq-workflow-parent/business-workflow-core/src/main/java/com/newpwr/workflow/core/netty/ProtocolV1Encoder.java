
package com.newpwr.workflow.core.netty;

import com.newpwr.workflow.core.compressor.Compressor;
import com.newpwr.workflow.core.compressor.CompressorFactory;
import com.newpwr.workflow.core.protocol.ProtocolConstants;
import com.newpwr.workflow.core.protocol.SchedulingMessage;
import com.newpwr.workflow.core.serializer.Serializer;
import com.newpwr.workflow.core.serializer.SerializerFactory;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * <pre>
 * 0     1     2     3     4     5     6     7     8     9    10     11    12    13    14    15    16
 * +-----+-----+-----+-----+-----+-----+-----+-----+-----+-----+-----+-----+-----+-----+-----+-----+
 * |   magic   |Proto|     Full length       |    Head   | Msg |Seria|Compr|     RequestId         |
 * |   code    |colVer|    (head+body)      |   Length  |Type |lizer|ess  |                       |
 * +-----------+-----------+-----------+-----------+-----------+-----------+-----------+-----------+
 * |                                                                                               |
 * |                                   Head Map [Optional]                                         |
 * +-----------+-----------+-----------+-----------+-----------+-----------+-----------+-----------+
 * |                                                                                               |
 * |                                         body                                                  |
 * |                                                                                               |
 * |                                        ... ...                                                |
 * +-----------------------------------------------------------------------------------------------+
 * </pre>
 * <p>
 * <li>Full Length: include all data </li>
 * <li>Head Length: include head data from magic code to head map. </li>
 * <li>Body Length: Full Length - Head Length</li>
 * </p>
 */
public class ProtocolV1Encoder extends MessageToByteEncoder {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProtocolV1Encoder.class);

    @Override
    public void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) {
        try {
            if (msg instanceof SchedulingMessage) {
                SchedulingMessage rpcMessage = (SchedulingMessage) msg;

                int fullLength = ProtocolConstants.V1_HEAD_LENGTH;
                int headLength = ProtocolConstants.V1_HEAD_LENGTH;

                byte messageType = rpcMessage.getMessageType();
                out.writeBytes(ProtocolConstants.MAGIC_CODE_BYTES);
                out.writeByte(ProtocolConstants.VERSION);
                // full Length(4B) and head length(2B) will fix in the end. 
                out.writerIndex(out.writerIndex() + 6);
                out.writeByte(messageType);
                out.writeByte(rpcMessage.getCodec());
                out.writeByte(rpcMessage.getCompressor());
                out.writeInt(rpcMessage.getId());

                // direct write head with zero-copy
                Map<String, String> headMap = rpcMessage.getHeadMap();
                if (headMap != null && !headMap.isEmpty()) {
                    int headMapBytesLength = HeadMapSerializer.getInstance().encode(headMap, out);
                    headLength += headMapBytesLength;
                    fullLength += headMapBytesLength;
                }

                byte[] bodyBytes = null;
                if (messageType != ProtocolConstants.MSGTYPE_HEARTBEAT_REQUEST
                        && messageType != ProtocolConstants.MSGTYPE_HEARTBEAT_RESPONSE) {
                    // heartbeat has no body
                    Serializer serializer = SerializerFactory.getSerializer(rpcMessage.getCodec());
                    bodyBytes = serializer.serialize(rpcMessage.getBody());
                    Compressor compressor = CompressorFactory.getCompressor(rpcMessage.getCompressor());
                    bodyBytes = compressor.compress(bodyBytes);
                    fullLength += bodyBytes.length;
                }

                if (bodyBytes != null) {
                    out.writeBytes(bodyBytes);
                }

                // fix fullLength and headLength
                int writeIndex = out.writerIndex();
                // skip magic code(2B) + version(1B)
                out.writerIndex(writeIndex - fullLength + 3);
                out.writeInt(fullLength);
                out.writeShort(headLength);
                out.writerIndex(writeIndex);
            } else {
                throw new UnsupportedOperationException("Not support this class:" + msg.getClass());
            }
        } catch (Throwable e) {
            LOGGER.error("Encode request error!", e);
        }
    }
}
