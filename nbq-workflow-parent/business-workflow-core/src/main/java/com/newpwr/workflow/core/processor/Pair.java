
package com.newpwr.workflow.core.processor;

/**
 * @author niubq
 * @date 2020/7/1 15:07
 * @description
 */
public final class Pair<T1, T2> {
    private final T1 first;
    private final T2 second;

    public Pair(T1 first, T2 second) {
        this.first = first;
        this.second = second;
    }

    public T1 getFirst() {
        return first;
    }

    public T2 getSecond() {
        return second;
    }
}
