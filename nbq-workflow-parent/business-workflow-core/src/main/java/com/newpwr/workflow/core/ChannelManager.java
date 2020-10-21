package com.newpwr.workflow.core;

import com.newpwr.workflow.common.Constants;
import com.newpwr.workflow.common.util.StringUtils;
import com.newpwr.workflow.core.netty.RpcContext;
import com.newpwr.workflow.core.netty.RpcPoolKey;
import com.newpwr.workflow.core.protocol.RegisterClientRequest;
import com.newpwr.workflow.core.protocol.RegisterSlaveRequest;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author niubq
 * @date 2020/7/1 16:27
 * @description
 */
public class ChannelManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(ChannelManager.class);
    private static final ConcurrentMap<Channel, RpcContext> IDENTIFIED_CHANNELS
            = new ConcurrentHashMap<Channel, RpcContext>();

    private static final ConcurrentMap<String, ConcurrentMap<String, ConcurrentMap<String, ConcurrentMap<String, RpcContext>>>> CLIENT_CHANNELS
            = new ConcurrentHashMap<String, ConcurrentMap<String, ConcurrentMap<String, ConcurrentMap<String, RpcContext>>>>();

    /**
     * ip+appname,port
     */
    private static final ConcurrentMap<String, ConcurrentMap<String, RpcContext>> SLAVE_CHANNELS
            = new ConcurrentHashMap<String, ConcurrentMap<String, RpcContext>>();

    /**
     * Is registered boolean.
     *
     * @param channel the channel
     * @return the boolean
     */
    public static boolean isRegistered(Channel channel) {
        return IDENTIFIED_CHANNELS.containsKey(channel);
    }

    /**
     * Gets get role from channel.
     *
     * @param channel the channel
     * @return the get role from channel
     */
    public static RpcPoolKey.RpcRole getRoleFromChannel(Channel channel) {
        if (IDENTIFIED_CHANNELS.containsKey(channel)) {
            return IDENTIFIED_CHANNELS.get(channel).getClientRole();
        }
        return null;
    }

    /**
     * Gets get context from identified.
     *
     * @param channel the channel
     * @return the get context from identified
     */
    public static RpcContext getContextFromIdentified(Channel channel) {
        return IDENTIFIED_CHANNELS.get(channel);
    }

    private static String buildClientId(String applicationId, Channel channel) {
        return applicationId + Constants.CLIENT_ID_SPLIT_CHAR + ChannelUtil.getAddressFromChannel(channel);
    }

    private static String[] readClientId(String clientId) {
        return clientId.split(Constants.CLIENT_ID_SPLIT_CHAR);
    }

    private static RpcContext buildChannelHolder(RpcPoolKey.RpcRole clientRole, String applicationId, Channel channel) {
        RpcContext holder = new RpcContext();
        holder.setClientRole(clientRole);
        holder.setClientId(buildClientId(applicationId, channel));
        holder.setApplicationId(applicationId);
        holder.setChannel(channel);
        return holder;
    }

    public static void registerClientChannel(RegisterClientRequest registerClientRequest, Channel channel) {
        RpcContext rpcContext;
        if (!IDENTIFIED_CHANNELS.containsKey(channel)) {
            rpcContext = buildChannelHolder(RpcPoolKey.RpcRole.CLIENT,
                    registerClientRequest.getApplicationId(),
                    channel);
            rpcContext.setCapabilities(registerClientRequest.getCapabilities());
            rpcContext.holdInIdentifiedChannels(IDENTIFIED_CHANNELS);
        } else {
            rpcContext = IDENTIFIED_CHANNELS.get(channel);
        }
        String clientIp;
        ConcurrentMap<String, RpcContext> clientIdentifiedMap = CLIENT_CHANNELS.
                computeIfAbsent(registerClientRequest.getServerAddress(), cap -> new ConcurrentHashMap<>())
                .computeIfAbsent(registerClientRequest.getApplicationId(), applicationId -> new ConcurrentHashMap<>())
                .computeIfAbsent(clientIp = ChannelUtil.getClientIpFromChannel(channel), clientIpKey -> new ConcurrentHashMap<>());

        rpcContext.holdInClientChannels(registerClientRequest.getServerAddress(), clientIdentifiedMap);
        updateChannelsResource(registerClientRequest.getServerAddress(), clientIp, registerClientRequest.getApplicationId());
    }

    public static void registerSlaveChannel(RegisterSlaveRequest registerSlaveRequest, Channel channel) {
        RpcContext rpcContext = buildChannelHolder(RpcPoolKey.RpcRole.SLAVE,
                registerSlaveRequest.getApplicationId(),
                channel);
        rpcContext.holdInIdentifiedChannels(IDENTIFIED_CHANNELS);
        String clientIdentified = rpcContext.getApplicationId() + Constants.CLIENT_ID_SPLIT_CHAR
                + ChannelUtil.getClientIpFromChannel(channel);
        SLAVE_CHANNELS.putIfAbsent(clientIdentified, new ConcurrentHashMap<String, RpcContext>());
        ConcurrentMap<String, RpcContext> clientIdentifiedMap = SLAVE_CHANNELS.get(clientIdentified);
        rpcContext.holdInSlaveChannels(clientIdentifiedMap);
    }

    private static void updateChannelsResource(String resourceId, String clientIp, String applicationId) {
        ConcurrentMap<String, RpcContext> sourcePortMap = CLIENT_CHANNELS.get(resourceId).get(applicationId).get(clientIp);
        for (ConcurrentMap.Entry<String, ConcurrentMap<String, ConcurrentMap<String, ConcurrentMap<String,
                RpcContext>>>> rmChannelEntry : CLIENT_CHANNELS.entrySet()) {
            if (rmChannelEntry.getKey().equals(resourceId)) {
                continue;
            }
            ConcurrentMap<String, ConcurrentMap<String, ConcurrentMap<String,
                    RpcContext>>> applicationIdMap = rmChannelEntry.getValue();
            if (!applicationIdMap.containsKey(applicationId)) {
                continue;
            }
            ConcurrentMap<String, ConcurrentMap<String,
                    RpcContext>> clientIpMap = applicationIdMap.get(applicationId);
            if (!clientIpMap.containsKey(clientIp)) {
                continue;
            }
            ConcurrentMap<String, RpcContext> portMap = clientIpMap.get(clientIp);
            for (ConcurrentMap.Entry<String, RpcContext> portMapEntry : portMap.entrySet()) {
                String address = portMapEntry.getKey();
                if (!sourcePortMap.containsKey(address)) {
                    RpcContext rpcContext = portMapEntry.getValue();
                    sourcePortMap.put(address, rpcContext);
                    rpcContext.holdInClientChannels(resourceId, address);
                }
            }
        }
    }

    /**
     * Release rpc context.
     *
     * @param channel the channel
     */
    public static void releaseRpcContext(Channel channel) {
        if (IDENTIFIED_CHANNELS.containsKey(channel)) {
            RpcContext rpcContext = getContextFromIdentified(channel);
            rpcContext.release();
        }
    }

    /**
     * Gets get same income client channel.
     *
     * @param channel the channel
     * @return the get same income client channel
     */
    public static Channel getSameClientChannel(Channel channel) {
        if (channel.isActive()) {
            return channel;
        }
        RpcContext rpcContext = getContextFromIdentified(channel);
        if (null == rpcContext) {
            LOGGER.error("rpcContext is null,channel:{},active:{}", channel, channel.isActive());
            return null;
        }
        if (rpcContext.getChannel().isActive()) {
            // recheck
            return rpcContext.getChannel();
        }
        String clientAddress = ChannelUtil.getAddressFromChannel(channel);
        RpcPoolKey.RpcRole clientRole = rpcContext.getClientRole();
        if (clientRole == RpcPoolKey.RpcRole.SLAVE) {
            String clientIdentified = rpcContext.getApplicationId() + Constants.CLIENT_ID_SPLIT_CHAR
                    + ChannelUtil.getClientIpFromChannel(channel);
            if (!SLAVE_CHANNELS.containsKey(clientIdentified)) {
                return null;
            }
            ConcurrentMap<String, RpcContext> clientRpcMap = SLAVE_CHANNELS.get(clientIdentified);
            return getChannelFromSameClientMap(clientRpcMap, clientAddress);
        } else if (clientRole == RpcPoolKey.RpcRole.CLIENT) {
            for (Map<String, RpcContext> clientRmMap : rpcContext.getClientHodlerMap().values()) {
                Channel sameClientChannel = getChannelFromSameClientMap(clientRmMap, clientAddress);
                if (null != sameClientChannel) {
                    return sameClientChannel;
                }
            }
        }
        return null;

    }

    private static Channel getChannelFromSameClientMap(Map<String, RpcContext> clientChannelMap, String exclusiveAddr) {
        if (null != clientChannelMap && !clientChannelMap.isEmpty()) {
            for (ConcurrentMap.Entry<String, RpcContext> entry : clientChannelMap.entrySet()) {
                if (entry.getKey() == exclusiveAddr) {
                    clientChannelMap.remove(entry.getKey());
                    continue;
                }
                Channel channel = entry.getValue().getChannel();
                if (channel.isActive()) {
                    return channel;
                }
                clientChannelMap.remove(entry.getKey());
            }
        }
        return null;
    }

    /**
     * Gets get channel.
     *
     * @param resourceId Resource ID
     * @param clientId   Client ID - ApplicationId:IP:Port
     * @return Corresponding channel, NULL if not found.
     */
    public static Channel getChannel(String resourceId, String clientId) {
        Channel resultChannel = null;

        String[] clientIdInfo = readClientId(clientId);

        if (clientIdInfo == null || clientIdInfo.length != 3) {
            //throw new FrameworkException("Invalid Client ID: " + clientId);
        }

        String targetApplicationId = clientIdInfo[0];
        String targetIP = clientIdInfo[1];
        int targetPort = Integer.parseInt(clientIdInfo[2]);

        ConcurrentMap<String, ConcurrentMap<String, ConcurrentMap<String,
                RpcContext>>> applicationIdMap = CLIENT_CHANNELS.get(resourceId);

        if (targetApplicationId == null || applicationIdMap == null || applicationIdMap.isEmpty()) {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("No channel is available for resource[{}]", resourceId);
            }
            return null;
        }

        ConcurrentMap<String, ConcurrentMap<String, RpcContext>> ipMap = applicationIdMap.get(targetApplicationId);

        if (null != ipMap && !ipMap.isEmpty()) {

            // Firstly, try to find the original channel through which the branch was registered.
            ConcurrentMap<String, RpcContext> portMapOnTargetIP = ipMap.get(targetIP);
            if (portMapOnTargetIP != null && !portMapOnTargetIP.isEmpty()) {

                RpcContext exactRpcContext = portMapOnTargetIP.get(targetPort);
                if (exactRpcContext != null) {
                    Channel channel = exactRpcContext.getChannel();
                    if (channel.isActive()) {
                        resultChannel = channel;
                        if (LOGGER.isDebugEnabled()) {
                            LOGGER.debug("Just got exactly the one {} for {}", channel, clientId);
                        }
                    } else {
                        if (portMapOnTargetIP.remove(targetPort, exactRpcContext)) {
                            if (LOGGER.isInfoEnabled()) {
                                LOGGER.info("Removed inactive {}", channel);
                            }
                        }
                    }
                }

                // The original channel was broken, try another one.
                if (resultChannel == null) {
                    for (ConcurrentMap.Entry<String, RpcContext> portMapOnTargetIPEntry : portMapOnTargetIP
                            .entrySet()) {
                        Channel channel = portMapOnTargetIPEntry.getValue().getChannel();

                        if (channel.isActive()) {
                            resultChannel = channel;
                            if (LOGGER.isInfoEnabled()) {
                                LOGGER.info(
                                        "Choose {} on the same IP[{}] as alternative of {}", channel, targetIP, clientId);
                            }
                            break;
                        } else {
                            if (portMapOnTargetIP.remove(portMapOnTargetIPEntry.getKey(),
                                    portMapOnTargetIPEntry.getValue())) {
                                if (LOGGER.isInfoEnabled()) {
                                    LOGGER.info("Removed inactive {}", channel);
                                }
                            }
                        }
                    }
                }
            }

            // No channel on the this app node, try another one.
            if (resultChannel == null) {
                for (ConcurrentMap.Entry<String, ConcurrentMap<String, RpcContext>> ipMapEntry : ipMap
                        .entrySet()) {
                    if (ipMapEntry.getKey().equals(targetIP)) {
                        continue;
                    }

                    ConcurrentMap<String, RpcContext> portMapOnOtherIP = ipMapEntry.getValue();
                    if (portMapOnOtherIP == null || portMapOnOtherIP.isEmpty()) {
                        continue;
                    }

                    for (ConcurrentMap.Entry<String, RpcContext> portMapOnOtherIPEntry : portMapOnOtherIP.entrySet()) {
                        Channel channel = portMapOnOtherIPEntry.getValue().getChannel();

                        if (channel.isActive()) {
                            resultChannel = channel;
                            if (LOGGER.isInfoEnabled()) {
                                LOGGER.info("Choose {} on the same application[{}] as alternative of {}", channel, targetApplicationId, clientId);
                            }
                            break;
                        } else {
                            if (portMapOnOtherIP.remove(portMapOnOtherIPEntry.getKey(),
                                    portMapOnOtherIPEntry.getValue())) {
                                if (LOGGER.isInfoEnabled()) {
                                    LOGGER.info("Removed inactive {}", channel);
                                }
                            }
                        }
                    }
                    if (resultChannel != null) {
                        break;
                    }
                }
            }
        }

        if (resultChannel == null) {
            resultChannel = tryOtherApp(applicationIdMap, targetApplicationId);

            if (resultChannel == null) {
                if (LOGGER.isInfoEnabled()) {
                    LOGGER.info("No channel is available for resource[{}] as alternative of {}", resourceId, clientId);
                }
            } else {
                if (LOGGER.isInfoEnabled()) {
                    LOGGER.info("Choose {} on the same resource[{}] as alternative of {}", resultChannel, resourceId, clientId);
                }
            }
        }

        return resultChannel;

    }

    private static Channel tryOtherApp(ConcurrentMap<String, ConcurrentMap<String, ConcurrentMap<String,
            RpcContext>>> applicationIdMap, String myApplicationId) {
        Channel chosenChannel = null;
        for (ConcurrentMap.Entry<String, ConcurrentMap<String, ConcurrentMap<String, RpcContext>>> applicationIdMapEntry : applicationIdMap
                .entrySet()) {
            if (!StringUtils.isNullOrEmpty(myApplicationId) && applicationIdMapEntry.getKey().equals(myApplicationId)) {
                continue;
            }

            ConcurrentMap<String, ConcurrentMap<String, RpcContext>> targetIPMap = applicationIdMapEntry.getValue();
            if (targetIPMap == null || targetIPMap.isEmpty()) {
                continue;
            }

            for (ConcurrentMap.Entry<String, ConcurrentMap<String, RpcContext>> targetIPMapEntry : targetIPMap
                    .entrySet()) {
                ConcurrentMap<String, RpcContext> portMap = targetIPMapEntry.getValue();
                if (portMap == null || portMap.isEmpty()) {
                    continue;
                }

                for (ConcurrentMap.Entry<String, RpcContext> portMapEntry : portMap.entrySet()) {
                    Channel channel = portMapEntry.getValue().getChannel();
                    if (channel.isActive()) {
                        chosenChannel = channel;
                        break;
                    } else {
                        if (portMap.remove(portMapEntry.getKey(), portMapEntry.getValue())) {
                            if (LOGGER.isInfoEnabled()) {
                                LOGGER.info("Removed inactive {}", channel);
                            }
                        }
                    }
                }
                if (chosenChannel != null) {
                    break;
                }
            }
            if (chosenChannel != null) {
                break;
            }
        }
        return chosenChannel;

    }

    /**
     * get rm channels
     *
     * @return
     */
    public static Map<String, Channel> getClientChannels() {
        if (CLIENT_CHANNELS.isEmpty()) {
            return null;
        }
        Map<String, Channel> channels = new HashMap<>(CLIENT_CHANNELS.size());
        for (String resourceId : CLIENT_CHANNELS.keySet()) {
            Channel channel = tryOtherApp(CLIENT_CHANNELS.get(resourceId), null);
            if (channel == null) {
                continue;
            }
            channels.put(resourceId, channel);
        }
        return channels;
    }
}
