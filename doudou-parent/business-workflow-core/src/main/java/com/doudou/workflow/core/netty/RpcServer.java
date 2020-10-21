
package com.doudou.workflow.core.netty;

import com.doudou.workflow.core.ChannelManager;
import com.doudou.workflow.core.SchedulerMessageHandler;
import com.doudou.workflow.core.processor.server.HealthyProcessor;
import com.doudou.workflow.core.processor.server.RegClientProcessor;
import com.doudou.workflow.core.processor.server.SchedulerOnRequestProcessor;
import com.doudou.workflow.core.processor.server.SchedulerOnResponseProcessor;
import com.doudou.workflow.core.protocol.HealthyMessage;
import com.doudou.workflow.core.protocol.MessageType;
import com.doudou.workflow.core.protocol.SchedulingMessage;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeoutException;

/**
 * @author niubq
 * @date 2020/7/1 15:07
 * @description
 */
public class RpcServer extends AbstractRpcRemotingServer {

    private static final Logger LOGGER = LoggerFactory.getLogger(RpcServer.class);


    /**
     * Instantiates a new Abstract rpc server.
     *
     * @param messageExecutor the message executor
     */
    public RpcServer(ThreadPoolExecutor messageExecutor) {
        super(messageExecutor, new NettyServerConfig());
    }

    private SchedulerMessageHandler schedulerMessageHandler;

    public SchedulerMessageHandler getSchedulerMessageHandler() {
        return schedulerMessageHandler;
    }

    public void setSchedulerMessageHandler(SchedulerMessageHandler schedulerMessageHandler) {
        this.schedulerMessageHandler = schedulerMessageHandler;
    }

    /**
     * Init.
     */
    @Override
    public void init() {
        // registry processor
        registerProcessor();
        setChannelHandlers(new AbstractRpcRemotingServer.ServerHandler());
        super.init();
    }

    /**
     * Destroy.
     */
    @Override
    public void destroy() {
        super.destroy();
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("destroyed rpcServer");
        }
    }


    /**
     * Send response.
     * rm reg,rpc reg,inner response
     *
     * @param request the request
     * @param channel the channel
     * @param msg     the msg
     */
    @Override
    public void sendResponse(SchedulingMessage request, Channel channel, Object msg) {
        Channel clientChannel = channel;
        if (!(msg instanceof HealthyMessage)) {
            clientChannel = ChannelManager.getSameClientChannel(channel);
        }
        if (clientChannel != null) {
            super.defaultSendResponse(request, clientChannel, msg);
        } else {
            throw new RuntimeException("channel is error. channel:" + clientChannel);
        }
    }

    /**
     * Send request with response object.
     * send syn request for rm
     *
     * @param resourceId the db key
     * @param clientId   the client ip
     * @param message    the message
     * @param timeout    the timeout
     * @return the object
     * @throws TimeoutException the timeout exception
     */
    @Override
    public Object sendSyncRequest(String resourceId, String clientId, Object message,
                                  long timeout) throws TimeoutException {
        Channel clientChannel = ChannelManager.getChannel(resourceId, clientId);
        if (clientChannel == null) {
            throw new RuntimeException("rm client is not connected. dbkey:" + resourceId
                    + ",clientId:" + clientId);

        }
        return sendAsyncRequestWithResponse(null, clientChannel, message, timeout);
    }

    /**
     * Send request with response object.
     * send syn request for rm
     *
     * @param clientChannel the client channel
     * @param message       the message
     * @return the object
     * @throws TimeoutException the timeout exception
     */
    @Override
    public Object sendSyncRequest(Channel clientChannel, Object message) throws TimeoutException {
        return sendSyncRequest(clientChannel, message, NettyServerConfig.getRpcRequestTimeout());
    }

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
    @Override
    public Object sendSyncRequest(Channel clientChannel, Object message, long timeout) throws TimeoutException {
        if (clientChannel == null) {
            throw new RuntimeException("rm client is not connected");

        }
        return sendAsyncRequestWithResponse(null, clientChannel, message, timeout);
    }

    /**
     * Send request with response object.
     *
     * @param resourceId the db key
     * @param clientId   the client ip
     * @param message    the msg
     * @return the object
     * @throws TimeoutException the timeout exception
     */
    @Override
    public Object sendSyncRequest(String resourceId, String clientId, Object message)
            throws TimeoutException {
        return sendSyncRequest(resourceId, clientId, message, NettyServerConfig.getRpcRequestTimeout());
    }

    /**
     * Send request with response object.
     *
     * @param channel the channel
     * @param message the msg
     * @return the object
     * @throws TimeoutException the timeout exception
     */
    @Override
    public Object sendASyncRequest(Channel channel, Object message) throws TimeoutException {
        return sendAsyncRequestWithoutResponse(channel, message);
    }

    private void registerProcessor() {
        SchedulerOnRequestProcessor schedulerOnRequestProcessor = new SchedulerOnRequestProcessor(this, schedulerMessageHandler);
        super.registerProcessor(MessageType.TYPE_RPC_CLIENT_MSG, schedulerOnRequestProcessor, messageExecutor);
        SchedulerOnResponseProcessor onResponseProcessor = new SchedulerOnResponseProcessor(schedulerMessageHandler, futures);
        super.registerProcessor(MessageType.TYPE_REG_CLIENT_RESULT, onResponseProcessor, messageExecutor);
        RegClientProcessor regClientProcessor = new RegClientProcessor(this);
        super.registerProcessor(MessageType.TYPE_REG_CLIENT_MSG, regClientProcessor, messageExecutor);
        HealthyProcessor heartbeatMessageProcessor = new HealthyProcessor(this);
        super.registerProcessor(MessageType.TYPE_HEARTBEAT_MSG, heartbeatMessageProcessor, null);
    }
}