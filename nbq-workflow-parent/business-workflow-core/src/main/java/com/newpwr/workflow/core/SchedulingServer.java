package com.newpwr.workflow.core;

import com.newpwr.workflow.core.processor.SchedulingProcessor;
import com.newpwr.workflow.core.protocol.SchedulingMessage;
import io.netty.channel.Channel;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeoutException;

/**
 * @author niubq
 * @date 2020/7/1 13:40
 * @description
 */
public interface SchedulingServer {
    /**
     * Send response.
     *
     * @param request the request
     * @param channel the channel
     * @param msg     the msg
     */
    void sendResponse(SchedulingMessage request, Channel channel, Object msg);

    /**
     * Sync call to RM with timeout.
     *
     * @param resourceId Resource ID
     * @param clientId   Client ID
     * @param message    Request message
     * @param timeout    timeout of the call
     * @return Response message
     * @throws IOException      .
     * @throws TimeoutException the timeout exception
     */
    Object sendSyncRequest(String resourceId, String clientId, Object message, long timeout)
            throws IOException, TimeoutException;

    /**
     * Sync call to RM
     *
     * @param resourceId Resource ID
     * @param clientId   Client ID
     * @param message    Request message
     * @return Response message
     * @throws IOException      .
     * @throws TimeoutException the timeout exception
     */
    Object sendSyncRequest(String resourceId, String clientId, Object message) throws IOException, TimeoutException;

    /**
     * Send request with response object.
     * send syn request for rm
     *
     * @param clientChannel the client channel
     * @param message       the message
     * @return the object
     * @throws TimeoutException the timeout exception
     */
    Object sendSyncRequest(Channel clientChannel, Object message) throws TimeoutException;

    /**
     * Send request with response object.
     * send syn request for rm
     *
     * @param clientChannel the client channel
     * @param message       the message
     * @param timeout       the timeout
     * @return the object
     * @throws TimeoutException the timeout exception
     */
    Object sendSyncRequest(Channel clientChannel, Object message, long timeout) throws TimeoutException;

    /**
     * ASync call to RM
     *
     * @param channel channel
     * @param message Request message
     * @return Response message
     * @throws IOException      .
     * @throws TimeoutException the timeout exception
     */
    Object sendASyncRequest(Channel channel, Object message) throws IOException, TimeoutException;

    /**
     * register processor
     *
     * @param messageType
     * @param processor
     * @param executor    thread pool
     */
    void registerProcessor(final int messageType, final SchedulingProcessor processor, final ExecutorService executor);
}
