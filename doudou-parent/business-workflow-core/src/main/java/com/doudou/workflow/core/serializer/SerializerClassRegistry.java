
package com.doudou.workflow.core.serializer;

import com.doudou.workflow.core.protocol.*;
import com.newpwr.workflow.core.protocol.*;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author niubq
 * @date 2020/6/30 9:36
 * @description
 */
public class SerializerClassRegistry {

    private static final Map<Class<?>, Object> REGISTRATIONS = new LinkedHashMap<>();

    static {

        // register commonly class
        registerClass(HashMap.class);
        registerClass(ArrayList.class);
        registerClass(LinkedList.class);
        registerClass(HashSet.class);
        registerClass(TreeSet.class);
        registerClass(Hashtable.class);
        registerClass(Date.class);
        registerClass(Calendar.class);
        registerClass(ConcurrentHashMap.class);
        registerClass(SimpleDateFormat.class);
        registerClass(GregorianCalendar.class);
        registerClass(Vector.class);
        registerClass(BitSet.class);
        registerClass(StringBuffer.class);
        registerClass(StringBuilder.class);
        registerClass(Object.class);
        registerClass(Object[].class);
        registerClass(String[].class);
        registerClass(byte[].class);
        registerClass(char[].class);
        registerClass(int[].class);
        registerClass(float[].class);
        registerClass(double[].class);

        registerClass(MergedWarpMessage.class);
        registerClass(MergeResultMessage.class);
        registerClass(RegisterSlaveRequest.class);
        registerClass(RegisterSlaveResponse.class);
        registerClass(RegisterClientRequest.class);
        registerClass(RegisterClientResponse.class);

    }
    
    /**
     * only supposed to be called at startup time
     *
     * @param clazz object type
     */
    public static void registerClass(Class<?> clazz) {
        registerClass(clazz, null);
    }

    /**
     * only supposed to be called at startup time
     *
     * @param clazz object type
     * @param serializer object serializer
     */
    public static void registerClass(Class<?> clazz, Object serializer) {
        if (clazz == null) {
            throw new IllegalArgumentException("Class registered cannot be null!");
        }
        REGISTRATIONS.put(clazz, serializer);
    }

    /**
     * get registered classes
     *
     * @return class serializer
     * */
    public static Map<Class<?>, Object> getRegisteredClasses() {
        return REGISTRATIONS;
    }
}
