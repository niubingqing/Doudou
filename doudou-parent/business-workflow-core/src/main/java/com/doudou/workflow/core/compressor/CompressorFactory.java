
package com.doudou.workflow.core.compressor;

import com.doudou.workflow.common.loader.EnhancedServiceLoader;
import com.doudou.workflow.common.loader.LoadLevel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author niubq
 * @date 2020/6/30 9:36
 * @description
 */
public class CompressorFactory {

    /**
     * The constant COMPRESSOR_MAP.
     */
    protected static final Map<CompressorType, Compressor> COMPRESSOR_MAP = new ConcurrentHashMap<CompressorType, Compressor>();

    static {
        COMPRESSOR_MAP.put(CompressorType.NONE, new NoneCompressor());
    }

    /**
     * Get compressor by code.
     *
     * @param code the code
     * @return the compressor
     */
    public static Compressor getCompressor(byte code) {
        CompressorType type = CompressorType.getByCode(code);
        if (COMPRESSOR_MAP.get(type) != null) {
            return COMPRESSOR_MAP.get(type);
        }
        Compressor impl = EnhancedServiceLoader.load(Compressor.class, type.name());
        COMPRESSOR_MAP.putIfAbsent(type, impl);
        return impl;
    }

    /**
     * None compressor
     */
    @LoadLevel(name = "NONE")
    public static class NoneCompressor implements Compressor {
        @Override
        public byte[] compress(byte[] bytes) {
            return bytes;
        }

        @Override
        public byte[] decompress(byte[] bytes) {
            return bytes;
        }
    }

    @LoadLevel(name="SEVENZ")
    public static class SevenZCompressor implements Compressor{
        //TODO 实现7z格式的压缩算法
        @Override
        public byte[] compress(byte[] bytes) {
            return new byte[0];
        }

        @Override
        public byte[] decompress(byte[] bytes) {
            return new byte[0];
        }
    }

}
