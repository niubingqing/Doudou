package com.doudou.workflow.core.health;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * @author niubq
 * @date 2020/6/30 16:20
 * @description
 */
public class HealthyHandler extends IdleStateHandler {
    final Logger logger = LoggerFactory.getLogger(HealthyHandler.class);
    private static final int DEFAULT_READER_IDLE_TIME = 15;
    private int readerTime;

    public HealthyHandler(int readerIdleTime) {
        super(readerIdleTime == 0 ? DEFAULT_READER_IDLE_TIME : readerIdleTime, 0, 0, TimeUnit.SECONDS);
        readerTime = readerIdleTime == 0 ? DEFAULT_READER_IDLE_TIME : readerIdleTime;
    }

    @Override
    protected void channelIdle(ChannelHandlerContext ctx, IdleStateEvent evt) throws Exception {
        logger.warn("[{}] Hasn't read data after {} seconds, will close the channel:{}",
                HealthyHandler.class.getSimpleName(), readerTime, ctx.channel());
        ctx.channel().close();
    }
}
