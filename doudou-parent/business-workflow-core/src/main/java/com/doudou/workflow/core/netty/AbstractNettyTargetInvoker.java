package com.doudou.workflow.core.netty;

import com.doudou.workflow.core.Disposable;
import com.doudou.workflow.core.SchedulerMessageHandler;
import com.doudou.workflow.core.TargetInvokable;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * @author niubq
 * @date 2020/6/24 15:52
 * @description
 */
public abstract class AbstractNettyTargetInvoker extends ChannelInboundHandlerAdapter implements TargetInvokable, SchedulerMessageHandler, Disposable {
    final Logger logger = LoggerFactory.getLogger(AbstractNettyTargetInvoker.class);

    private static final String NAME_FORMAT = "rpc-pool-%d";
    private static final int CORE_POOL_SIZE = 50;
    private static final int MAXIMUM_POOL_SIZE = 200;
    private static final long KEEP_ALIVE_TIME = 0L;
    private static final int POOL_CAPACITY = 1024;

    private ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat(NAME_FORMAT).build();
    private ExecutorService pool = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(POOL_CAPACITY), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());

    @Override
    public void receive(Object payload) {
        logger.info("监听{}", payload);
        pool.submit(() -> resolve(payload));
    }
}
