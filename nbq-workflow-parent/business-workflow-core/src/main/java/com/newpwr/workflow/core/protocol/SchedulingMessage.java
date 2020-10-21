package com.newpwr.workflow.core.protocol;

import com.newpwr.workflow.common.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author niubq
 * @date 2020/7/1 13:37
 * @description
 */
public class SchedulingMessage {
    private int id;
    private byte messageType;
    private byte codec;
    private byte compressor;
    private Map<String, String> headMap = new HashMap<>();
    private Object body;

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets body.
     *
     * @return the body
     */
    public Object getBody() {
        return body;
    }

    /**
     * Sets body.
     *
     * @param body the body
     */
    public void setBody(Object body) {
        this.body = body;
    }

    /**
     * Gets codec.
     *
     * @return the codec
     */
    public byte getCodec() {
        return codec;
    }

    /**
     * Sets codec.
     *
     * @param codec the codec
     * @return the codec
     */
    public SchedulingMessage setCodec(byte codec) {
        this.codec = codec;
        return this;
    }

    /**
     * Gets compressor.
     *
     * @return the compressor
     */
    public byte getCompressor() {
        return compressor;
    }

    /**
     * Sets compressor.
     *
     * @param compressor the compressor
     * @return the compressor
     */
    public SchedulingMessage setCompressor(byte compressor) {
        this.compressor = compressor;
        return this;
    }

    /**
     * Gets head map.
     *
     * @return the head map
     */
    public Map<String, String> getHeadMap() {
        return headMap;
    }

    /**
     * Sets head map.
     *
     * @param headMap the head map
     * @return the head map
     */
    public SchedulingMessage setHeadMap(Map<String, String> headMap) {
        this.headMap = headMap;
        return this;
    }

    /**
     * Gets head.
     *
     * @param headKey the head key
     * @return the head
     */
    public String getHead(String headKey) {
        return headMap.get(headKey);
    }

    /**
     * Put head.
     *
     * @param headKey   the head key
     * @param headValue the head value
     */
    public void putHead(String headKey, String headValue) {
        headMap.put(headKey, headValue);
    }

    /**
     * Gets message type.
     *
     * @return the message type
     */
    public byte getMessageType() {
        return messageType;
    }

    /**
     * Sets message type.
     *
     * @param messageType the message type
     */
    public void setMessageType(byte messageType) {
        this.messageType = messageType;
    }

    @Override
    public String toString() {
        return StringUtils.toString(this);
    }
}
