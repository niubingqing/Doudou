package com.newpwr.workflow.server.env;

import com.newpwr.workflow.common.util.NumberUtils;
import com.newpwr.workflow.core.constants.DefaultValues;


/**
 * @author niubq
 * @date 2020/6/30 9:34
 * @description
 */
public class PortHelper {

    public static int getPort(String[] args) {
        if (ContainerHelper.isRunningInContainer()) {
            return ContainerHelper.getPort();
        } else if (args != null && args.length >= 2) {
            for (int i = 0; i < args.length; ++i) {
                if ("-p".equalsIgnoreCase(args[i]) && i < args.length - 1) {
                    return NumberUtils.toInt(args[i + 1], DefaultValues.SERVER_DEFAULT_PORT);
                }
            }
        }

        return DefaultValues.SERVER_DEFAULT_PORT;
    }
}
