package com.doudou.workflow.core.health;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author niubq
 * @date 2020/6/30 16:31
 * @description
 */
public class HealthyChecker extends ChannelInboundHandlerAdapter {
    final Logger logger = LoggerFactory.getLogger(HealthyHandler.class);
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
    }

//    private static final int DEFAULT_PING_INTERVAL = 5;
//    private Client client;
//    private int pingInterval;
//    public HealthyChecker(int pingInterval) {
////        Assert.notNull(client, "client can not be null");
////        this.client = client;
//        this.pingInterval = pingInterval <= 0 ? DEFAULT_PING_INTERVAL : pingInterval;
//    }
//    @Override
//    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        super.channelActive(ctx);
//        schedulePing(ctx);
//    }
//    private void schedulePing(ChannelHandlerContext ctx) {
//        ctx.executor().schedule(() -> {
//            Channel channel = ctx.channel();
//            if (channel.isActive()) {
//                logger.debug("[{}] Send a PingPacket", HealthyChecker.class.getSimpleName());
//                channel.writeAndFlush(new PingPacket());
//                schedulePing(ctx);
//            }
//        }, pingInterval, TimeUnit.SECONDS);
//    }
//
//    @Override
//    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
//        ctx.executor().schedule(() -> {
//            logger.info("[{}] Try to reconnecting...", HealthyChecker.class.getSimpleName());
//            client.connect();
//        }, 5, TimeUnit.SECONDS);
//        ctx.fireChannelInactive();
//    }
}
