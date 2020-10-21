
package com.doudou.workflow.common.util;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
/**
 * @author niubq
 * @date 2020/7/1 15:07
 * @description
 */
public class CompressUtil {

    /**
     * compress bytes
     * @param src
     * @return
     * @throws IOException
     */
    public static byte[] compress(final byte[] src) throws IOException {
        byte[] result;
        ByteArrayOutputStream bos = new ByteArrayOutputStream(src.length);
        GZIPOutputStream gos = new GZIPOutputStream(bos);
        try {
            gos.write(src);
            gos.finish();
            result = bos.toByteArray();
        } finally {
            IOUtil.close(bos, gos);
        }
        return result;
    }

    /**
     * uncompress bytes
     * @param src
     * @return
     * @throws IOException
     */
    public static byte[] uncompress(final byte[] src) throws IOException {
        byte[] result;
        byte[] uncompressData = new byte[src.length];
        ByteArrayInputStream bis = new ByteArrayInputStream(src);
        GZIPInputStream iis = new GZIPInputStream(bis);
        ByteArrayOutputStream bos = new ByteArrayOutputStream(src.length);

        try {
            while (true) {
                int len = iis.read(uncompressData, 0, uncompressData.length);
                if (len <= 0) {
                    break;
                }
                bos.write(uncompressData, 0, len);
            }
            bos.flush();
            result = bos.toByteArray();
        } finally {
            IOUtil.close(bis, iis, bos);
        }
        return result;
    }

    /**
     * check magic
     * @param bytes
     * @return
     */
    public static boolean isCompressData(byte[] bytes) {
        if (bytes != null && bytes.length > 2) {
            int header = ((bytes[0] & 0xff)) | (bytes[1] & 0xff) << 8;
            return GZIPInputStream.GZIP_MAGIC == header;
        }
        return false;
    }
}