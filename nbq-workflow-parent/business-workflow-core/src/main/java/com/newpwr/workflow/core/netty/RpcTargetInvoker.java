package com.newpwr.workflow.core.netty;

import com.newpwr.workflow.core.protocol.AbstractMessage;
import com.newpwr.workflow.core.protocol.AbstractResultMessage;
import lombok.extern.slf4j.Slf4j;

/**
 * @author niubq
 * @date 2020/6/28 10:25
 * @description
 */
@Slf4j
public class RpcTargetInvoker extends AbstractNettyTargetInvoker {

    @Override
    public void targetInvoke(Object payload) {

    }

    @Override
    public void destroy() {

    }

    @Override
    public AbstractResultMessage onRequest(AbstractMessage request, RpcContext context) {

//        targetInvoke(request);
        return null;
    }

    @Override
    public void onResponse(AbstractResultMessage response, RpcContext context) {

    }
}
