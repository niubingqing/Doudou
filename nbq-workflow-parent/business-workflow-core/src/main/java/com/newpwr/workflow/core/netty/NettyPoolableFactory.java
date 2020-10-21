
package com.newpwr.workflow.core.netty;

import com.newpwr.workflow.common.exception.FrameworkException;
import com.newpwr.workflow.common.util.NetUtil;
import com.newpwr.workflow.core.protocol.RegisterClientResponse;
import com.newpwr.workflow.core.protocol.RegisterSlaveResponse;
import io.netty.channel.Channel;
import org.apache.commons.pool.KeyedPoolableObjectFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;

/**
 * @author niubq
 * @date 2020/7/1 15:07
 * @description
 */
public class NettyPoolableFactory implements KeyedPoolableObjectFactory<RpcPoolKey, Channel> {

    private static final Logger LOGGER = LoggerFactory.getLogger(NettyPoolableFactory.class);

    private final AbstractRpcRemotingClient rpcRemotingClient;

    private final RpcClientBootstrap clientBootstrap;

    /**
     * Instantiates a new Netty key poolable factory.
     *
     * @param rpcRemotingClient the rpc remoting client
     */
    public NettyPoolableFactory(AbstractRpcRemotingClient rpcRemotingClient, RpcClientBootstrap clientBootstrap) {
        this.rpcRemotingClient = rpcRemotingClient;
        this.clientBootstrap = clientBootstrap;
    }

    @Override
    public Channel makeObject(RpcPoolKey key) {
        InetSocketAddress address = NetUtil.toInetSocketAddress(key.getAddress());
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("NettyPool create channel to " + key);
        }
        Channel tmpChannel = clientBootstrap.getNewChannel(address);
        long start = System.currentTimeMillis();
        Object response;
        Channel channelToServer = null;
        if (null == key.getMessage()) {
            throw new FrameworkException("register msg is null, role:" + key.getRpcRole().name());
        }
        try {
            response = rpcRemotingClient.sendAsyncRequestWithResponse(tmpChannel, key.getMessage());
            if (!isResponseSuccess(response, key.getRpcRole())) {
                rpcRemotingClient.onRegisterMsgFail(key.getAddress(), tmpChannel, response, key.getMessage());
            } else {
                channelToServer = tmpChannel;
                rpcRemotingClient.onRegisterMsgSuccess(key.getAddress(), tmpChannel, response, key.getMessage());
            }
        } catch (Exception exx) {
            if (tmpChannel != null) {
                tmpChannel.close();
            }
            throw new FrameworkException(
                "register error,role:" + key.getRpcRole().name() + ",err:" + exx.getMessage());
        }
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("register success, cost " + (System.currentTimeMillis() - start) + " ms, version:" + getVersion(
                response, key.getRpcRole()) + ",role:" + key.getRpcRole().name() + ",channel:"
                + channelToServer);
        }
        return channelToServer;
    }

    private boolean isResponseSuccess(Object response, RpcPoolKey.RpcRole rpcRole) {
        if (null == response) {
            return false;
        }
        if (rpcRole.equals(RpcPoolKey.RpcRole.SLAVE)) {
            if (!(response instanceof RegisterSlaveResponse)) {
                return false;
            }
            return ((RegisterSlaveResponse)response).isIdentified();
        } else if (rpcRole.equals(RpcPoolKey.RpcRole.CLIENT)) {
            if (!(response instanceof RegisterClientResponse)) {
                return false;
            }
            return ((RegisterClientResponse)response).isIdentified();
        }
        return false;
    }

    private String getVersion(Object response,RpcPoolKey.RpcRole rpcRole) {
        if (rpcRole.equals(RpcPoolKey.RpcRole.SLAVE)) {
            return ((RegisterSlaveResponse)response).getVersion();
        } else {
            return ((RegisterClientResponse)response).getVersion();
        }
    }

    @Override
    public void destroyObject(RpcPoolKey key, Channel channel) throws Exception {
        if (null != channel) {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("will destroy channel:" + channel);
            }
            channel.disconnect();
            channel.close();
        }
    }

    @Override
    public boolean validateObject(RpcPoolKey key, Channel obj) {
        if (null != obj && obj.isActive()) {
            return true;
        }
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("channel valid false,channel:" + obj);
        }
        return false;
    }

    @Override
    public void activateObject(RpcPoolKey key, Channel obj) throws Exception {

    }

    @Override
    public void passivateObject(RpcPoolKey key, Channel obj) throws Exception {

    }
}
