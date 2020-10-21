
package com.doudou.workflow.common.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;

/**
 * @author niubq
 * @date 2020/7/1 15:07
 * @description
 */
public final class RejectedPolicies {

    /**
     * when rejected happened ,add the new task and run the oldest task
     *
     * @return rejected execution handler
     */
    public static RejectedExecutionHandler runsOldestTaskPolicy() {
        return (r, executor) -> {
            if (executor.isShutdown()) {
                return;
            }
            BlockingQueue<Runnable> workQueue = executor.getQueue();
            Runnable firstWork = workQueue.poll();
            boolean newTaskAdd = workQueue.offer(r);
            if (firstWork != null) {
                firstWork.run();
            }
            if (!newTaskAdd) {
                executor.execute(r);
            }
        };
    }
}
