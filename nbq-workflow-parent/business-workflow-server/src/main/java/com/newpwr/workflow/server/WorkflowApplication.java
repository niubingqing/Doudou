package com.newpwr.workflow.server;

import com.newpwr.workflow.common.thread.NamedThreadFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author niubq
 * @date 2020/6/30 9:30
 * @description
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class WorkflowApplication {
    private static final int MIN_SERVER_POOL_SIZE = 50;
    private static final int MAX_SERVER_POOL_SIZE = 500;
    private static final int MAX_TASK_QUEUE_SIZE = 20000;
    private static final int KEEP_ALIVE_TIME = 500;
    private static final ThreadPoolExecutor WORKING_THREADS = new ThreadPoolExecutor(MIN_SERVER_POOL_SIZE,
            MAX_SERVER_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(MAX_TASK_QUEUE_SIZE),
            new NamedThreadFactory("ServerHandlerThread", MAX_SERVER_POOL_SIZE), new ThreadPoolExecutor.CallerRunsPolicy());

    public static void main(String[] args) throws IOException {
        SpringApplication.run(WorkflowApplication.class, args);
//        int port = PortHelper.getPort(args);
//
//        RpcServer rpcServer = new RpcServer(WORKING_THREADS);
//        rpcServer.setListenPort(port);
//        rpcServer.init();
    }
}