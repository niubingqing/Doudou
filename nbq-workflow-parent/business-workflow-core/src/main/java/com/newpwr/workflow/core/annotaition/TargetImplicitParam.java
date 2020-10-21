package com.newpwr.workflow.core.annotaition;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author niubq
 * @date 2020/6/30 13:43
 * @description
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface TargetImplicitParam {
    String name() default "";

    String value() default "";

    String dataType() default "";

    Class<?> dataTypeClass() default Void.class;
}
