package com.doudou.workflow.core.processor.server;

import com.doudou.workflow.core.ChannelManager;
import com.doudou.workflow.core.SchedulingServer;
import com.doudou.workflow.core.processor.SchedulingProcessor;
import com.doudou.workflow.core.protocol.RegisterClientRequest;
import com.doudou.workflow.core.protocol.RegisterClientResponse;
import com.doudou.workflow.core.protocol.SchedulingMessage;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author niubq
 * @date 2020/7/2 11:12
 * @description
 */
public class RegClientProcessor implements SchedulingProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegClientProcessor.class);

    private SchedulingServer schedulingServer;

    public RegClientProcessor(SchedulingServer schedulingServer) {
        this.schedulingServer = schedulingServer;
    }

    @Override
    public void process(ChannelHandlerContext ctx, SchedulingMessage rpcMessage) throws Exception {
        onRegClientMsg(ctx, rpcMessage);
    }

    private void onRegClientMsg(ChannelHandlerContext ctx, SchedulingMessage rpcMessage) {
        RegisterClientRequest request = (RegisterClientRequest) rpcMessage.getBody();
        boolean isSuccess = false;
        try {
//            String ipAndPort = NetUtil.toStringAddress(ctx.channel().remoteAddress());
            ChannelManager.registerClientChannel(request, ctx.channel());
            isSuccess = true;
        } catch (Exception e) {
            isSuccess = false;
            LOGGER.error(e.getMessage());
        }

        schedulingServer.sendResponse(rpcMessage, ctx.channel(), new RegisterClientResponse(isSuccess));
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("client register success,message:{},channel:{}", request, ctx.channel());
        }
    }
}
