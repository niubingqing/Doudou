package com.doudou.workflow.core;

import com.doudou.workflow.core.netty.RpcContext;
import com.doudou.workflow.core.protocol.AbstractMessage;
import com.doudou.workflow.core.protocol.AbstractResultMessage;

/**
 * @author niubq
 * @date 2020/7/2 14:28
 * @description
 */
public interface SchedulerMessageHandler {
    /**
     * On a request received.
     *
     * @param request received request message
     * @param context context of the RPC
     * @return response to the request
     */
    AbstractResultMessage onRequest(AbstractMessage request, RpcContext context);

    /**
     * On a response received.
     *
     * @param response received response message
     * @param context  context of the RPC
     */
    void onResponse(AbstractResultMessage response, RpcContext context);
}
