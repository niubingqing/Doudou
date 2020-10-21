package com.doudou.workflow.core.mq;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

import java.util.concurrent.*;

/**
 * @author niubq
 * @date 2020/6/24 15:53
 * @description
 */
@Slf4j
public class RabbitMqTargetInvoker extends AbstractMqTargetInvoker {

    private static final String NAME_FORMAT = "mq-pool-%d";
    private static final int CORE_POOL_SIZE = 50;
    private static final int MAXIMUM_POOL_SIZE = 200;
    private static final long KEEP_ALIVE_TIME = 0L;
    private static final int POOL_CAPACITY = 1024;

    private ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat(NAME_FORMAT).build();
    private ExecutorService pool = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(POOL_CAPACITY), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());

    @Override
    @StreamListener(Sink.INPUT)
    public void receive(Object payload) {
        System.out.println("监听" + payload);
        pool.submit(() -> resolve(payload));
    }

    @Override
    public void targetInvoke(Object payload) {

    }
}
