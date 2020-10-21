
package com.newpwr.workflow.common.util;


/**
 * @author niubq
 * @date 2020/7/1 15:07
 * @description
 */
public class NumberUtils {

    /**
     * <p>Convert a <code>String</code> to an <code>int</code>, returning a
     * default value if the conversion fails.</p>
     *
     * <p>If the string is <code>null</code>, the default value is returned.</p>
     *
     * @param str          the string to convert, may be null
     * @param defaultValue the default value
     * @return the int represented by the string, or the default if conversion fails
     */
    public static int toInt(final String str, final int defaultValue) {
        if (str == null) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException nfe) {
            return defaultValue;
        }
    }

    public static Long toLong(String str, final Long defaultValue) {
        if (str == null) {
            return defaultValue;
        }
        try {
            return Long.valueOf(str);
        } catch (NumberFormatException nfe) {
            return defaultValue;
        }
    }

    public static Long toLong(String str) {
        if (StringUtils.isNotBlank(str)) {
            return Long.valueOf(str);
        }
        return null;
    }
}
