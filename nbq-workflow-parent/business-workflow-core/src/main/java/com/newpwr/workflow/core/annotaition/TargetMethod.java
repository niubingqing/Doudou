package com.newpwr.workflow.core.annotaition;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author niubq
 * @date 2020/6/30 13:39
 * @description
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface TargetMethod {
    String value();

    String desc() default "";

    TargetImplicitParam[] params();
}
