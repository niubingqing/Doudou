package com.newpwr.workflow.core.processor;

import com.newpwr.workflow.core.protocol.SchedulingMessage;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author niubq
 * @date 2020/7/1 13:31
 * @description
 */
public interface SchedulingProcessor {
    /**
     * Process message
     *
     * @param ctx        Channel handler context.
     * @param rpcMessage rpc message.
     * @throws Exception throws exception process message error.
     */
    void process(ChannelHandlerContext ctx, SchedulingMessage rpcMessage) throws Exception;
}
