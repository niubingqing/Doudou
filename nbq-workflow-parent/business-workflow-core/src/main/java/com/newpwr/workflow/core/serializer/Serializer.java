
package com.newpwr.workflow.core.serializer;

/**
 * @author niubq
 * @date 2020/6/30 9:36
 * @description
 */
public interface Serializer {

    /**
     * Encode object to byte[].
     *
     * @param <T> the type parameter
     * @param t   the t
     * @return the byte [ ]
     */
    <T> byte[] serialize(T t);

    /**
     * Decode t from byte[].
     *
     * @param <T>   the type parameter
     * @param bytes the bytes
     * @return the t
     */
    <T> T deserialize(byte[] bytes);
}
