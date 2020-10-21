package com.doudou.workflow.core.netty;

import com.doudou.workflow.core.ChannelUtil;
import com.doudou.workflow.data.register.CapabilityClass;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author niubq
 * @date 2020/7/1 16:29
 * @description
 */
public class RpcContext {
    private static final Logger LOGGER = LoggerFactory.getLogger(RpcContext.class);

    private RpcPoolKey.RpcRole clientRole;

    private String version;

    private String applicationId;

    private String clientId;

    private Channel channel;

    private List<CapabilityClass> capabilities;

    /**
     * id
     */
    private ConcurrentMap<Channel, RpcContext> clientIDHolderMap;

    /**
     * slave
     */
    private ConcurrentMap<String, RpcContext> slaveHolderMap;


    private RpcContext leaderHolder;

    /**
     * client
     */
    private ConcurrentMap<String, ConcurrentMap<String, RpcContext>> clientHodlerMap;

    /**
     * Release.
     */
    public void release() {
        String clientAddress = ChannelUtil.getAddressFromChannel(channel);
        if (clientIDHolderMap != null) {
            clientIDHolderMap = null;
        }
        if (clientRole == RpcPoolKey.RpcRole.SLAVE && slaveHolderMap != null) {
            slaveHolderMap.remove(clientAddress);
            slaveHolderMap = null;
        }
        if (clientRole == RpcPoolKey.RpcRole.CLIENT && clientHodlerMap != null) {
            for (Map<String, RpcContext> portMap : clientHodlerMap.values()) {
                portMap.remove(clientAddress);
            }
            clientHodlerMap = null;
            capabilities = null;
        }
    }

    /**
     * Hold in client channels.
     *
     * @param slaveHolderMap the slave holder map
     */
    public void holdInSlaveChannels(ConcurrentMap<String, RpcContext> slaveHolderMap) {
        if (this.slaveHolderMap != null) {
            throw new IllegalStateException();
        }
        this.slaveHolderMap = slaveHolderMap;
        String clientAddress = ChannelUtil.getAddressFromChannel(channel);
        this.slaveHolderMap.put(clientAddress, this);
    }

    /**
     * Hold in identified channels.
     *
     * @param clientIDHolderMap the client id holder map
     */
    public void holdInIdentifiedChannels(ConcurrentMap<Channel, RpcContext> clientIDHolderMap) {
        if (this.clientIDHolderMap != null) {
            throw new IllegalStateException();
        }
        this.clientIDHolderMap = clientIDHolderMap;
        this.clientIDHolderMap.put(channel, this);
    }

    /**
     * Hold in resource manager channels.
     *
     * @param serverAddress the resource id
     * @param addressMap    the client rm holder map
     */
    public void holdInClientChannels(String serverAddress, ConcurrentMap<String, RpcContext> addressMap) {
        if (null == this.clientHodlerMap) {
            this.clientHodlerMap = new ConcurrentHashMap<String, ConcurrentMap<String, RpcContext>>();
        }
        String clientAddress = ChannelUtil.getAddressFromChannel(channel);
        addressMap.put(clientAddress, this);
        this.clientHodlerMap.put(serverAddress, addressMap);
    }

    /**
     * Hold in resource manager channels.
     *
     * @param resourceId the resource id
     * @param clientAddr the client port
     */
    public void holdInClientChannels(String resourceId, String clientAddr) {
        if (null == this.clientHodlerMap) {
            this.clientHodlerMap = new ConcurrentHashMap<String, ConcurrentMap<String, RpcContext>>();
        }
        clientHodlerMap.putIfAbsent(resourceId, new ConcurrentHashMap<String, RpcContext>());
        ConcurrentMap<String, RpcContext> portMap = clientHodlerMap.get(resourceId);
        portMap.put(clientAddr, this);
    }

    public ConcurrentMap<String, ConcurrentMap<String, RpcContext>> getClientHodlerMap() {
        return clientHodlerMap;
    }

    public void setClientHodlerMap(ConcurrentMap<String, ConcurrentMap<String, RpcContext>> clientHodlerMap) {
        this.clientHodlerMap = clientHodlerMap;
    }


    /**
     * Gets port map.
     *
     * @param serverAddress the server address
     * @return the address map
     */
    public Map<String, RpcContext> getAddressMap(String serverAddress) {
        return clientHodlerMap.get(serverAddress);
    }

    /**
     * Gets get client id.
     *
     * @return the get client id
     */
    public String getClientId() {
        return clientId;
    }

    /**
     * Gets get channel.
     *
     * @return the get channel
     */
    public Channel getChannel() {
        return channel;
    }

    /**
     * Sets set channel.
     *
     * @param channel the channel
     */
    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    /**
     * Gets get application id.
     *
     * @return the get application id
     */
    public String getApplicationId() {
        return applicationId;
    }

    /**
     * Sets set application id.
     *
     * @param applicationId the application id
     */
    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    /**
     * Gets get client role.
     *
     * @return the get client role
     */
    public RpcPoolKey.RpcRole getClientRole() {
        return clientRole;
    }

    /**
     * Sets set client role.
     *
     * @param clientRole the client role
     */
    public void setClientRole(RpcPoolKey.RpcRole clientRole) {
        this.clientRole = clientRole;
    }

    /**
     * Gets get version.
     *
     * @return the get version
     */
    public String getVersion() {
        return version;
    }

    /**
     * Sets set version.
     *
     * @param version the version
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * Sets client id.
     *
     * @param clientId the client id
     */
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public List<CapabilityClass> getCapabilities() {
        return capabilities;
    }

    public void setCapabilities(List<CapabilityClass> capabilities) {
        this.capabilities = capabilities;
    }

    public RpcContext getLeaderHolder() {
        return leaderHolder;
    }

    public void setLeaderHolder(RpcContext leaderHolder) {
        this.leaderHolder = leaderHolder;
    }

    @Override
    public String toString() {
        return "RpcContext{" +
                "applicationId='" + applicationId + '\'' +
                ", clientId='" + clientId + '\'' +
                ", channel=" + channel +
                '}';
    }
}
