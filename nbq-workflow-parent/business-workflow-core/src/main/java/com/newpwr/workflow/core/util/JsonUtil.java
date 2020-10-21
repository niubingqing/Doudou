package com.newpwr.workflow.core.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.newpwr.workflow.core.annotaition.TargetField;
import com.newpwr.workflow.core.annotaition.TargetImplicitParam;
import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Field;

/**
 * @author niubq
 * @date 2020/6/30 15:04
 * @description
 */
public class JsonUtil {
    private final static String PROPERTIES_FIELD_KEY = "properties";
    private final static String DESC_FIELD_KEY = "desc";
    private final static String TYPE_FIELD_KEY = "type";
    private final static String ITEMS_FIELD_KEY = "items";

    public static String parse(TargetImplicitParam[] params) {
        if (params.length == 1) {
            JSONObject jsonObject = new JSONObject();
            TargetImplicitParam param = params[0];
            jsonObject.put(param.name(), parse(param));

            return JSON.toJSONString(jsonObject);
        } else if (params.length > 1) {
            JSONObject jsonObject = new JSONObject();
            for (TargetImplicitParam param : params) {
                jsonObject.put(param.name(), parse(param));
            }

            return JSON.toJSONString(jsonObject);
        }

        return StringUtils.EMPTY;
    }

    private static JSONObject parse(TargetImplicitParam param) {
        return parse(param.dataType(), param.value(), param.dataTypeClass());
    }

    private static JSONObject parse(String dataType, String desc, Class dataTypeClass) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(TYPE_FIELD_KEY, dataType);
        jsonObject.put(DESC_FIELD_KEY, desc);

        //数组
        if (dataType.equals(FieldTypeEnum.array.name())) {
            jsonObject.put(ITEMS_FIELD_KEY, parse(dataTypeClass));
        }
        //引用类型
        if (dataType.equals(FieldTypeEnum.object.name())) {
            jsonObject.put(PROPERTIES_FIELD_KEY, parse(dataTypeClass));
        }

        return jsonObject;
    }

    private static JSONObject parse(Class clazz) {
        JSONObject jsonObject = new JSONObject();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (!field.isAnnotationPresent(TargetField.class)) {
                continue;
            }

            TargetField targetField = field.getAnnotation(TargetField.class);
            jsonObject.put(TYPE_FIELD_KEY, targetField.dataType());
            jsonObject.put(DESC_FIELD_KEY, targetField.value());

            //数组
            if (targetField.dataType().equals(FieldTypeEnum.array.name())) {
                jsonObject.put(ITEMS_FIELD_KEY, parse(targetField.dataTypeClass()));
            }
            //引用类型
            if (targetField.dataType().equals(FieldTypeEnum.object.name())) {
                jsonObject.put(PROPERTIES_FIELD_KEY, parse(targetField.dataTypeClass()));
            }
        }

        return jsonObject;
    }

    public enum FieldTypeEnum {
        integer,
        number,
        string,
        object,
        array,
        bool;
    }
}
