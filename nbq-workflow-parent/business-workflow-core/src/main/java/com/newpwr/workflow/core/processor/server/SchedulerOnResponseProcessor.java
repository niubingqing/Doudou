package com.newpwr.workflow.core.processor.server;

import com.newpwr.workflow.core.ChannelManager;
import com.newpwr.workflow.core.SchedulerMessageHandler;
import com.newpwr.workflow.core.netty.RpcContext;
import com.newpwr.workflow.core.processor.SchedulingProcessor;
import com.newpwr.workflow.core.protocol.AbstractResultMessage;
import com.newpwr.workflow.core.protocol.MessageFuture;
import com.newpwr.workflow.core.protocol.SchedulingMessage;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author niubq
 * @date 2020/7/1 15:19
 * @description
 */
public class SchedulerOnResponseProcessor implements SchedulingProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(SchedulerOnResponseProcessor.class);

    private ConcurrentMap<Integer, MessageFuture> futures;
    private SchedulerMessageHandler schedulerMessageHandler;

    public SchedulerOnResponseProcessor(SchedulerMessageHandler schedulerMessageHandler,
                                     ConcurrentHashMap<Integer, MessageFuture> futures) {
        this.schedulerMessageHandler = schedulerMessageHandler;
        this.futures = futures;
    }

    @Override
    public void process(ChannelHandlerContext ctx, SchedulingMessage rpcMessage) throws Exception {
        MessageFuture messageFuture = futures.remove(rpcMessage.getId());
        if (messageFuture != null) {
            messageFuture.setResultMessage(rpcMessage.getBody());
        } else {
            if (ChannelManager.isRegistered(ctx.channel())) {
                onResponseMessage(ctx, rpcMessage);
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
    }

    private void onResponseMessage(ChannelHandlerContext ctx, SchedulingMessage rpcMessage) {
        if (rpcMessage.getBody() instanceof AbstractResultMessage) {
            RpcContext rpcContext = ChannelManager.getContextFromIdentified(ctx.channel());
            schedulerMessageHandler.onResponse((AbstractResultMessage) rpcMessage.getBody(), rpcContext);
        }
    }
}
