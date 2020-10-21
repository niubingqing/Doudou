package com.newpwr.workflow.core.annotaition;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author niubq
 * @date 2020/6/30 15:44
 * @description
 */
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface TargetField {
    String value() default "";

    String name() default "";

    String dataType() default "";

    Class<?> dataTypeClass() default Void.class;
}
