
package com.doudou.workflow.core.netty;

import com.doudou.workflow.common.util.CollectionUtils;
import com.doudou.workflow.core.Disposable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author niubq
 * @date 2020/7/1 15:07
 * @description
 */
public class ShutdownHook extends Thread {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShutdownHook.class);

    private static final ShutdownHook SHUTDOWN_HOOK = new ShutdownHook("ShutdownHook");

    private Set<Disposable> disposables = new TreeSet<>();

    private final AtomicBoolean destroyed = new AtomicBoolean(false);

    /**
     * default 10. Lower values have higher priority
     */
    private static final int DEFAULT_PRIORITY = 10;

    static {
        Runtime.getRuntime().addShutdownHook(SHUTDOWN_HOOK);
    }

    private ShutdownHook(String name) {
        super(name);
    }

    public static ShutdownHook getInstance() {
        return SHUTDOWN_HOOK;
    }

    public void addDisposable(Disposable disposable) {
        addDisposable(disposable, DEFAULT_PRIORITY);
    }

    public void addDisposable(Disposable disposable, int priority) {
        disposables.add(new DisposablePriorityWrapper(disposable, priority));
    }

    @Override
    public void run() {
        destroyAll();
    }

    public void destroyAll() {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("destoryAll starting");
        }
        if (!destroyed.compareAndSet(false, true) && CollectionUtils.isEmpty(disposables)) {
            return;
        }
        for (Disposable disposable : disposables) {
            disposable.destroy();
        }
    }

    /**
     * for spring context
     */
    public static void removeRuntimeShutdownHook() {
        Runtime.getRuntime().removeShutdownHook(SHUTDOWN_HOOK);
    }

    private static class DisposablePriorityWrapper implements Comparable<DisposablePriorityWrapper>, Disposable {

        private Disposable disposable;

        private int priority;

        public DisposablePriorityWrapper(Disposable disposable, int priority) {
            this.disposable = disposable;
            this.priority = priority;
        }

        @Override
        public int compareTo(DisposablePriorityWrapper disposablePriorityWrapper) {
            return priority - disposablePriorityWrapper.priority;
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(this.priority);
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof DisposablePriorityWrapper)) {
                return false;
            }
            DisposablePriorityWrapper dpw = (DisposablePriorityWrapper)other;
            return this.priority == dpw.priority && this.disposable.equals(dpw.disposable);
        }

        @Override
        public void destroy() {
            disposable.destroy();
        }
    }

}

