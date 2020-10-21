package com.doudou.workflow.core;

import com.alibaba.fastjson.JSON;
import com.doudou.workflow.data.MessageContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author niubq
 * @date 2020/6/24 15:50
 * @description
 */
public interface TargetInvokable {
    Logger logger = LoggerFactory.getLogger(TargetInvokable.class);

    void receive(Object payload);

    default void resolve(Object payload){
        logger.info("解析{}", payload);
        MessageContext messageContext = JSON.parseObject(payload.toString(), MessageContext.class);
//        if (messageContext.getInDTC()) {
//            if (messageContext.getXid() != null) {
//                RootContext.bind(messageContext.getXid());
//                targetInvoke(messageContext);
//                RootContext.unbind();
//                return;
//            }
//        }
//
        targetInvoke(messageContext);
    }

    void targetInvoke(Object payload);
}
