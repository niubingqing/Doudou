package com.doudou.workflow.core.processor.server;

import com.doudou.workflow.core.SchedulingServer;
import com.doudou.workflow.core.processor.SchedulingProcessor;
import com.doudou.workflow.core.protocol.SchedulingMessage;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author niubq
 * @date 2020/7/2 14:58
 * @description
 */
public class RegSlaveProcessor implements SchedulingProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(RegSlaveProcessor.class);
    private SchedulingServer schedulingServer;

    public RegSlaveProcessor(SchedulingServer schedulingServer) {
        this.schedulingServer = schedulingServer;
    }

    @Override
    public void process(ChannelHandlerContext ctx, SchedulingMessage rpcMessage) throws Exception {

    }
}
