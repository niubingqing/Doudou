package com.newpwr.workflow.core.netty;

import com.newpwr.workflow.common.thread.NamedThreadFactory;
import com.newpwr.workflow.core.processor.client.ClientHeartbeatProcessor;
import com.newpwr.workflow.core.protocol.AbstractMessage;
import com.newpwr.workflow.core.protocol.MessageType;
import com.newpwr.workflow.core.protocol.RegisterClientRequest;
import io.netty.channel.Channel;
import io.netty.util.concurrent.EventExecutorGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Function;

/**
 * @author niubq
 * @date 2020/7/7 16:14
 * @description
 */
public class RpcClient extends AbstractRpcRemotingClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(RpcClient.class);
    //    private ResourceManager resourceManager;
    private static volatile RpcClient instance;
    private final AtomicBoolean initialized = new AtomicBoolean(false);
    private static final long KEEP_ALIVE_TIME = Integer.MAX_VALUE;
    private static final int MAX_QUEUE_SIZE = 20000;
    private String applicationId;
    private String serviceGroup;

    private RpcClient(NettyClientConfig nettyClientConfig, EventExecutorGroup eventExecutorGroup,
                      ThreadPoolExecutor messageExecutor) {
        super(nettyClientConfig, eventExecutorGroup, messageExecutor, RpcPoolKey.RpcRole.CLIENT);
    }

    /**
     * Gets instance.
     *
     * @param applicationId the application id
     * @param serviceGroup  the service group
     * @return the instance
     */
    public static RpcClient getInstance(String applicationId, String serviceGroup) {
        RpcClient rmRpcClient = getInstance();
        rmRpcClient.setApplicationId(applicationId);
        rmRpcClient.setServiceGroup(serviceGroup);
        return rmRpcClient;
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static RpcClient getInstance() {
        if (null == instance) {
            synchronized (RpcClient.class) {
                if (null == instance) {
                    NettyClientConfig nettyClientConfig = new NettyClientConfig();
                    final ThreadPoolExecutor messageExecutor = new ThreadPoolExecutor(
                            nettyClientConfig.getClientWorkerThreads(), nettyClientConfig.getClientWorkerThreads(),
                            KEEP_ALIVE_TIME, TimeUnit.SECONDS, new LinkedBlockingQueue<>(MAX_QUEUE_SIZE),
                            new NamedThreadFactory(nettyClientConfig.getRmDispatchThreadPrefix(),
                                    nettyClientConfig.getClientWorkerThreads()), new ThreadPoolExecutor.CallerRunsPolicy());
                    instance = new RpcClient(nettyClientConfig, null, messageExecutor);
                }
            }
        }
        return instance;
    }

    @Override
    public void init() {
        // registry processor
        registerProcessor();
        if (initialized.compareAndSet(false, true)) {
            super.init();
        }
    }


    @Override
    public void destroy() {
        super.destroy();
        initialized.getAndSet(false);
        instance = null;
    }

    @Override
    protected Function<String, RpcPoolKey> getPoolKeyFunction() {
        return (serverAddress) -> {
//            String resourceIds = getMergedResourceKeys();
//            if (null != resourceIds && LOGGER.isInfoEnabled()) {
//                LOGGER.info("RM will register :{}", resourceIds);
//            }
//            RegisterRMRequest message = new RegisterRMRequest(applicationId, transactionServiceGroup);
//            message.setResourceIds(resourceIds);
            RegisterClientRequest message = new RegisterClientRequest();
            return new RpcPoolKey(RpcPoolKey.RpcRole.CLIENT, serverAddress, message);
        };
    }

    @Override
    protected String getServiceGroup() {
        return serviceGroup;
    }

    @Override
    public void onRegisterMsgSuccess(String serverAddress, Channel channel, Object response, AbstractMessage requestMessage) {
//        if (LOGGER.isInfoEnabled()) {
//            LOGGER.info("register RM success. server version:{},channel:{}", ((RegisterRMResponse) response).getVersion(), channel);
//        }
        getClientChannelManager().registerChannel(serverAddress, channel);
//        String dbKey = getMergedResourceKeys();
//        RegisterRMRequest message = (RegisterRMRequest) requestMessage;
//        if (message.getResourceIds() != null) {
//            if (!message.getResourceIds().equals(dbKey)) {
//                sendRegisterMessage(serverAddress, channel, dbKey);
//            }
//        }
    }

    @Override
    public void onRegisterMsgFail(String serverAddress, Channel channel, Object response, AbstractMessage requestMessage) {
//        if (response instanceof RegisterRMResponse && LOGGER.isInfoEnabled()) {
//            LOGGER.info("register RM failed. server version:{}", ((RegisterRMResponse) response).getVersion());
//        }
//        throw new FrameworkException("register RM failed, channel:" + channel);
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }


    public void setServiceGroup(String serviceGroup) {
        this.serviceGroup = serviceGroup;
    }

    public void sendRegisterMessage(String serverAddress, Channel channel, String resourceId) {
//        RegisterRMRequest message = new RegisterRMRequest(applicationId, transactionServiceGroup);
//        message.setResourceIds(resourceId);
//        try {
//            super.sendAsyncRequestWithoutResponse(channel, message);
//        } catch (FrameworkException e) {
//            if (e.getErrcode() == FrameworkErrorCode.ChannelIsNotWritable && serverAddress != null) {
//                getClientChannelManager().releaseChannel(channel, serverAddress);
//                if (LOGGER.isInfoEnabled()) {
//                    LOGGER.info("remove not writable channel:{}", channel);
//                }
//            } else {
//                LOGGER.error("register resource failed, channel:{},resourceId:{}", channel, resourceId, e);
//            }
//        } catch (TimeoutException e) {
//            LOGGER.error(e.getMessage());
//        }
    }

    private void registerProcessor() {
//        ClientOnResponseProcessor onResponseProcessor =
//                new ClientOnResponseProcessor(mergeMsgMap, super.getFutures(), getTransactionMessageHandler());
//        super.registerProcessor(MessageType.TYPE_SEATA_MERGE_RESULT, onResponseProcessor, null);
//        super.registerProcessor(MessageType.TYPE_BRANCH_REGISTER_RESULT, onResponseProcessor, null);
//        super.registerProcessor(MessageType.TYPE_BRANCH_STATUS_REPORT_RESULT, onResponseProcessor, null);
//        super.registerProcessor(MessageType.TYPE_GLOBAL_LOCK_QUERY_RESULT, onResponseProcessor, null);
//        super.registerProcessor(MessageType.TYPE_REG_RM_RESULT, onResponseProcessor, null);
        ClientHeartbeatProcessor clientHeartbeatProcessor = new ClientHeartbeatProcessor();
        super.registerProcessor(MessageType.TYPE_HEARTBEAT_MSG, clientHeartbeatProcessor, null);
    }
}
