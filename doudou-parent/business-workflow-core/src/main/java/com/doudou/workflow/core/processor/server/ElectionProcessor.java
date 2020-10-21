package com.doudou.workflow.core.processor.server;

import com.doudou.workflow.common.util.NetUtil;
import com.doudou.workflow.core.ChannelManager;
import com.doudou.workflow.core.SchedulingServer;
import com.doudou.workflow.core.netty.RpcContext;
import com.doudou.workflow.core.processor.SchedulingProcessor;
import com.doudou.workflow.core.protocol.AbstractMessage;
import com.doudou.workflow.core.protocol.SchedulingMessage;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author niubq
 * @date 2020/7/1 14:31
 * @description
 */
public class ElectionProcessor implements SchedulingProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(ElectionProcessor.class);
    private SchedulingServer schedulingServer;

    public ElectionProcessor(SchedulingServer schedulingServer) {
        this.schedulingServer = schedulingServer;
    }

    @Override
    public void process(ChannelHandlerContext ctx, SchedulingMessage rpcMessage) throws Exception {
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
    }
}
