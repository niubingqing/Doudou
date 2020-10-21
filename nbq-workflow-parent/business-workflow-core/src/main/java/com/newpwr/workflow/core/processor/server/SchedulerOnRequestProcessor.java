package com.newpwr.workflow.core.processor.server;

import com.newpwr.workflow.common.util.NetUtil;
import com.newpwr.workflow.core.ChannelManager;
import com.newpwr.workflow.core.SchedulerMessageHandler;
import com.newpwr.workflow.core.SchedulingServer;
import com.newpwr.workflow.core.netty.RpcContext;
import com.newpwr.workflow.core.processor.SchedulingProcessor;
import com.newpwr.workflow.core.protocol.*;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author niubq
 * @date 2020/7/1 15:19
 * @description
 */
public class SchedulerOnRequestProcessor implements SchedulingProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(SchedulerOnRequestProcessor.class);
    private SchedulingServer remotingServer;
    private SchedulerMessageHandler schedulerMessageHandler;

    public SchedulerOnRequestProcessor(SchedulingServer remotingServer) {
        this.remotingServer = remotingServer;
    }

    public SchedulerOnRequestProcessor(SchedulingServer remotingServer, SchedulerMessageHandler schedulerMessageHandler) {
        this.remotingServer = remotingServer;
        this.schedulerMessageHandler = schedulerMessageHandler;
    }

    @Override
    public void process(ChannelHandlerContext ctx, SchedulingMessage rpcMessage) throws Exception {
        if (ChannelManager.isRegistered(ctx.channel())) {
            onRequestMessage(ctx, rpcMessage);
        } else {
            try {
                if (LOGGER.isInfoEnabled()) {
                    LOGGER.info("closeChannelHandlerContext channel:" + ctx.channel());
                }
                ctx.disconnect();
                ctx.close();
            } catch (Exception exx) {
                LOGGER.error(exx.getMessage());
            }
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info(String.format("close a unhandled connection! [%s]", ctx.channel().toString()));
            }
        }
    }

    private void onRequestMessage(ChannelHandlerContext ctx, SchedulingMessage rpcMessage) {
        Object message = rpcMessage.getBody();
        RpcContext rpcContext = ChannelManager.getContextFromIdentified(ctx.channel());
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("server received:{},clientIp:{}", message,
                    NetUtil.toIpAddress(ctx.channel().remoteAddress()));
        } else {

        }
        if (!(message instanceof AbstractMessage)) {
            return;
        }
        if (message instanceof MergedWarpMessage) {
            AbstractResultMessage[] results = new AbstractResultMessage[((MergedWarpMessage) message).msgs.size()];
            for (int i = 0; i < results.length; i++) {
                final AbstractMessage subMessage = ((MergedWarpMessage) message).msgs.get(i);
                results[i] = schedulerMessageHandler.onRequest(subMessage, rpcContext);
            }
            MergeResultMessage resultMessage = new MergeResultMessage();
            resultMessage.setMsgs(results);
            remotingServer.sendResponse(rpcMessage, ctx.channel(), resultMessage);
        } else {
            // the single send request message
            final AbstractMessage msg = (AbstractMessage) message;
            AbstractResultMessage result = schedulerMessageHandler.onRequest(msg, rpcContext);
            remotingServer.sendResponse(rpcMessage, ctx.channel(), result);
        }
    }
}
