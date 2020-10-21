package com.doudou.workflow.core.processor.client;

import com.doudou.workflow.core.SchedulerMessageHandler;
import com.doudou.workflow.core.processor.SchedulingProcessor;
import com.doudou.workflow.core.protocol.*;
import com.newpwr.workflow.core.protocol.*;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author niubq
 * @date 2020/7/1 15:07
 * @description
 */
public class ClientOnResponseProcessor implements SchedulingProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientOnResponseProcessor.class);

    /**
     * The Merge msg map from io.seata.core.rpc.netty.AbstractNettyRemotingClient#mergeMsgMap.
     */
    private Map<Integer, MergeMessage> mergeMsgMap;

    /**
     * The Futures from io.seata.core.rpc.netty.AbstractNettyRemoting#futures
     */
    private ConcurrentMap<Integer, MessageFuture> futures;

    private SchedulerMessageHandler schedulerMessageHandler;

    /**
     * To handle the received RPC message on upper level.
     */
//    private TransactionMessageHandler transactionMessageHandler;

    public ClientOnResponseProcessor(Map<Integer, MergeMessage> mergeMsgMap,
                                     ConcurrentHashMap<Integer, MessageFuture> futures,
                                     SchedulerMessageHandler schedulerMessageHandler) {
        this.mergeMsgMap = mergeMsgMap;
        this.futures = futures;
        this.schedulerMessageHandler = schedulerMessageHandler;
    }

    @Override
    public void process(ChannelHandlerContext ctx, SchedulingMessage rpcMessage) throws Exception {
        if (rpcMessage.getBody() instanceof MergeResultMessage) {
            MergeResultMessage results = (MergeResultMessage) rpcMessage.getBody();
            MergedWarpMessage mergeMessage = (MergedWarpMessage) mergeMsgMap.remove(rpcMessage.getId());
            for (int i = 0; i < mergeMessage.msgs.size(); i++) {
                int msgId = mergeMessage.msgIds.get(i);
                MessageFuture future = futures.remove(msgId);
                if (future == null) {
                    if (LOGGER.isInfoEnabled()) {
                        LOGGER.info("msg: {} is not found in futures.", msgId);
                    }
                } else {
                    future.setResultMessage(results.getMsgs()[i]);
                }
            }
        } else {
            MessageFuture messageFuture = futures.remove(rpcMessage.getId());
            if (messageFuture != null) {
                messageFuture.setResultMessage(rpcMessage.getBody());
            } else {
                if (rpcMessage.getBody() instanceof AbstractResultMessage) {
                    if (schedulerMessageHandler != null) {
                        schedulerMessageHandler.onResponse((AbstractResultMessage) rpcMessage.getBody(), null);
                    }
                }
            }
        }
    }
}
