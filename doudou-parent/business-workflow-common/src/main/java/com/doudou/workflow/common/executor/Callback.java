
package com.doudou.workflow.common.executor;

/**
 * @author niubq
 * @date 2020/7/1 15:07
 * @description
 */
public interface Callback<T> {

    /**
     * Execute t.
     *
     * @return the t
     * @throws Throwable the throwable
     */
    T execute() throws Throwable;
}

