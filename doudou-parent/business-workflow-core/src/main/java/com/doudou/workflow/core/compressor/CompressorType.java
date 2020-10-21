
package com.doudou.workflow.core.compressor;

/**
 * @author niubq
 * @date 2020/6/30 9:36
 * @description
 */
public enum CompressorType {

    /**
     * Not compress
     */
    NONE((byte) 0),

    /**
     * The gzip.
     */
    GZIP((byte) 1),

    /**
     * The zip.
     */
    ZIP((byte) 2),

    /**
     * The sevenz.
     */
    SEVENZ((byte) 3),

    /**
     * The bzip2.
     */
    BZIP2((byte) 4),

    /**
     * The lz4.
     */
    LZ4((byte) 5);

    private final byte code;

    CompressorType(final byte code) {
        this.code = code;
    }

    /**
     * Gets result code.
     *
     * @param code the code
     * @return the result code
     */
    public static CompressorType getByCode(int code) {
        for (CompressorType b : CompressorType.values()) {
            if (code == b.code) {
                return b;
            }
        }
        throw new IllegalArgumentException("unknown codec:" + code);
    }

    /**
     * Gets result code.
     *
     * @param name the code
     * @return the result code
     */
    public static CompressorType getByName(String name) {
        for (CompressorType b : CompressorType.values()) {
            if (b.name().equalsIgnoreCase(name)) {
                return b;
            }
        }
        throw new IllegalArgumentException("unknown codec:" + name);
    }

    /**
     * Gets code.
     *
     * @return the code
     */
    public byte getCode() {
        return code;
    }
}
