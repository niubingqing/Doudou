
package com.doudou.workflow.core.processor.client;

import com.doudou.workflow.core.processor.SchedulingProcessor;
import com.doudou.workflow.core.protocol.HealthyMessage;
import com.doudou.workflow.core.protocol.SchedulingMessage;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author niubq
 * @date 2020/7/1 15:07
 * @description
 */
public class ClientHeartbeatProcessor implements SchedulingProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientHeartbeatProcessor.class);

    @Override
    public void process(ChannelHandlerContext ctx, SchedulingMessage rpcMessage) throws Exception {
        if (rpcMessage.getBody() == HealthyMessage.PONG) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("received PONG from {}", ctx.channel().remoteAddress());
            }
        }
    }
}
