package com.doudou.workflow.server.env;

import com.doudou.workflow.common.util.NumberUtils;
import com.doudou.workflow.common.util.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static com.doudou.workflow.core.constants.DefaultValues.SERVER_DEFAULT_PORT;


/**
 * @author niubq
 * @date 2020/6/30 9:35
 * @description
 */
public class ContainerHelper {
    private static final String C_GROUP_PATH = "/proc/1/cgroup";
    private static final String DOCKER_PATH = "/docker";
    private static final String KUBEPODS_PATH = "/kubepods";

    private static final String ENV_SYSTEM_KEY = "WF_ENV";
    private static final String ENV_SEATA_IP_KEY = "WF_IP";
    private static final String ENV_SERVER_NODE_KEY = "SERVER_NODE";
    private static final String ENV_SEATA_PORT_KEY = "SEATA_PORT";
    private static final String ENV_STORE_MODE_KEY = "STORE_MODE";

    /**
     * Judge if application is run in container.
     *
     * @return If application is run in container
     */
    public static boolean isRunningInContainer() {
        Path path = Paths.get(C_GROUP_PATH);
        if (Files.exists(path)) {
            try (Stream<String> stream = Files.lines(path)) {
                return stream.anyMatch(line -> line.contains(DOCKER_PATH) || line.contains(KUBEPODS_PATH));
            } catch (IOException e) {
                System.err.println("Judge if running in container failed: " + e.getMessage());
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * Gets env from container.
     *
     * @return the env
     */
    public static String getEnv() {
        return StringUtils.trimToNull(System.getenv(ENV_SYSTEM_KEY));
    }

    /**
     * Gets host from container.
     *
     * @return the env
     */
    public static String getHost() {
        return StringUtils.trimToNull(System.getenv(ENV_SEATA_IP_KEY));
    }

    /**
     * Gets port from container.
     *
     * @return the env
     */
    public static int getPort() {
        return NumberUtils.toInt(System.getenv(ENV_SEATA_PORT_KEY), SERVER_DEFAULT_PORT);
    }

    /**
     * Gets server node from container.
     *
     * @return the env
     */
    public static Long getServerNode() {
        return NumberUtils.toLong(System.getenv(ENV_SERVER_NODE_KEY));
    }

    /**
     * Gets store mode from container.
     *
     * @return the env
     */
    public static String getStoreMode() {
        return StringUtils.trimToNull(System.getenv(ENV_STORE_MODE_KEY));
    }
}
