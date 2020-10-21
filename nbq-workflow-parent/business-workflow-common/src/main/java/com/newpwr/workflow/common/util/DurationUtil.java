
package com.newpwr.workflow.common.util;


import java.time.Duration;

/**
 * @author niubq
 * @date 2020/7/1 15:07
 * @description
 */
public class DurationUtil {

    public static final Duration DEFAULT_DURATION = Duration.ofMillis(-1);

    public static final String DAY_UNIT = "d";
    public static final String HOUR_UNIT = "h";
    public static final String MINUTE_UNIT = "m";
    public static final String SECOND_UNIT = "s";
    public static final String MILLIS_SECOND_UNIT = "ms";

    public static Duration parse(String str) {
        if (StringUtils.isBlank(str)) {
            return DEFAULT_DURATION;
        }

        if (str.contains(MILLIS_SECOND_UNIT)) {
            Long value = doParse(MILLIS_SECOND_UNIT, str);
            return value == null ? null : Duration.ofMillis(value);
        } else if (str.contains(DAY_UNIT)) {
            Long value = doParse(DAY_UNIT, str);
            return value == null ? null : Duration.ofDays(value);
        } else if (str.contains(HOUR_UNIT)) {
            Long value = doParse(HOUR_UNIT, str);
            return value == null ? null : Duration.ofHours(value);
        } else if (str.contains(MINUTE_UNIT)) {
            Long value = doParse(MINUTE_UNIT, str);
            return value == null ? null : Duration.ofMinutes(value);
        } else if (str.contains(SECOND_UNIT)) {
            Long value = doParse(SECOND_UNIT, str);
            return value == null ? null : Duration.ofSeconds(value);
        }
        try {
            int millis = Integer.parseInt(str);
            return Duration.ofMillis(millis);
        } catch (Exception e) {
            throw new UnsupportedOperationException(str + " can't parse to duration", e);
        }
    }

    private static Long doParse(String unit, String str) {
        str = str.replace(unit, "");
        if ("".equals(str)) {
            return null;
        }
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException e) {
            throw new UnsupportedOperationException("\"" + str + "\" can't parse to Duration", e);
        }
    }

}
