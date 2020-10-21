package com.newpwr.workflow.core.processor.server;

import com.newpwr.workflow.core.SchedulingServer;
import com.newpwr.workflow.core.processor.SchedulingProcessor;
import com.newpwr.workflow.core.protocol.HealthyMessage;
import com.newpwr.workflow.core.protocol.SchedulingMessage;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author niubq
 * @date 2020/7/1 13:34
 * @description
 */
public class HealthyProcessor implements SchedulingProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(HealthyProcessor.class);

    private SchedulingServer schedulingServer;

    public HealthyProcessor(SchedulingServer schedulingServer) {
        this.schedulingServer = schedulingServer;
    }

    @Override
    public void process(ChannelHandlerContext ctx, SchedulingMessage rpcMessage) throws Exception {
        try {
            schedulingServer.sendResponse(rpcMessage, ctx.channel(), HealthyMessage.PONG);
        } catch (Throwable throwable) {
            LOGGER.error("send response error: {}", throwable.getMessage(), throwable);
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("received PING from {}", ctx.channel().remoteAddress());
        }
    }
}
