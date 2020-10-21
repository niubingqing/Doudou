package com.doudou.workflow.core;

import com.doudou.workflow.core.processor.SchedulingProcessor;
import com.doudou.workflow.core.protocol.AbstractMessage;
import com.doudou.workflow.core.protocol.SchedulingMessage;
import io.netty.channel.Channel;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeoutException;

/**
 * @author niubq
 * @date 2020/7/1 13:45
 * @description
 */
public interface SchedulingClient {
    /**
     * Send msg with response object.
     *
     * @param msg     the msg
     * @param timeout the timeout
     * @return the object
     * @throws TimeoutException the timeout exception
     */
    Object sendMsgWithResponse(Object msg, long timeout) throws TimeoutException;

    /**
     * Send msg with response object.
     *
     * @param serverAddress the server address
     * @param msg           the msg
     * @param timeout       the timeout
     * @return the object
     * @throws TimeoutException the timeout exception
     */
    Object sendMsgWithResponse(String serverAddress, Object msg, long timeout) throws TimeoutException;

    /**
     * Send msg with response object.
     *
     * @param msg the msg
     * @return the object
     * @throws TimeoutException the timeout exception
     */
    Object sendMsgWithResponse(Object msg) throws TimeoutException;

    /**
     * Send response.
     *
     * @param request       the msg id
     * @param serverAddress the server address
     * @param msg           the msg
     */
    void sendResponse(SchedulingMessage request, String serverAddress, Object msg);

    /**
     * On register msg success.
     *
     * @param serverAddress  the server address
     * @param channel        the channel
     * @param response       the response
     * @param requestMessage the request message
     */
    void onRegisterMsgSuccess(String serverAddress, Channel channel, Object response, AbstractMessage requestMessage);

    /**
     * On register msg fail.
     *
     * @param serverAddress  the server address
     * @param channel        the channel
     * @param response       the response
     * @param requestMessage the request message
     */
    void onRegisterMsgFail(String serverAddress, Channel channel, Object response, AbstractMessage requestMessage);

    /**
     * register processor
     *
     * @param messageType {@link io.seata.core.protocol.MessageType}
     * @param processor   {@link SchedulingProcessor}
     * @param executor    thread pool
     */
    void registerProcessor(final int messageType, final SchedulingProcessor processor, final ExecutorService executor);

}
