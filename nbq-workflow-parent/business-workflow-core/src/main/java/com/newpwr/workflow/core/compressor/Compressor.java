
package com.newpwr.workflow.core.compressor;

/**
 * @author niubq
 * @date 2020/6/30 9:36
 * @description
 */
public interface Compressor {

    /**
     * compress byte[] to byte[].
     * @param bytes the bytes
     * @return the byte[]
     */
    byte[] compress(byte[] bytes);

    /**
     * decompress byte[] to byte[].
     * @param bytes the bytes
     * @return the byte[]
     */
    byte[] decompress(byte[] bytes);

}
