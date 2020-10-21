
package com.doudou.workflow.common.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author niubq
 * @date 2020/7/1 15:07
 * @description
 */
public class PositiveAtomicCounter {
    private static final int MASK = 0x7FFFFFFF;
    private final AtomicInteger atom;

    public PositiveAtomicCounter() {
        atom = new AtomicInteger(0);
    }

    public final int incrementAndGet() {
        return atom.incrementAndGet() & MASK;
    }

    public final int getAndIncrement() {
        return atom.getAndIncrement() & MASK;
    }

    public int get() {
        return atom.get() & MASK;
    }

}