
package com.doudou.workflow.core.serializer;

import com.doudou.workflow.common.loader.EnhancedServiceLoader;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author niubq
 * @date 2020/6/30 9:36
 * @description
 */
public class SerializerFactory {

    /**
     * The constant CODEC_MAP.
     */
    protected static final Map<SerializerType, Serializer> CODEC_MAP = new ConcurrentHashMap<SerializerType, Serializer>();

    /**
     * Get serializeCode serializeCode.
     *
     * @param serializeCode the code
     * @return the serializeCode
     */
    public static Serializer getSerializer(byte serializeCode) {
        SerializerType serializerType = SerializerType.getByCode(serializeCode);
        if (CODEC_MAP.get(serializerType) != null) {
            return CODEC_MAP.get(serializerType);
        }
        Serializer codecImpl = EnhancedServiceLoader.load(Serializer.class, serializerType.name());
        CODEC_MAP.putIfAbsent(serializerType, codecImpl);
        return codecImpl;
    }

    /**
     * Encode byte [ ].
     *
     * @param <T>   the type parameter
     * @param serializeCode the serializeCode
     * @param t     the t
     * @return the byte [ ]
     */
    public static <T> byte[] encode(byte serializeCode, T t) {
        return getSerializer(serializeCode).serialize(t);
    }

    /**
     * Decode t.
     *
     * @param <T>   the type parameter
     * @param codec the code
     * @param bytes the bytes
     * @return the t
     */
    public static <T> T decode(byte codec, byte[] bytes) {
        return getSerializer(codec).deserialize(bytes);
    }

}
