package com.doudou.workflow.core.annotaition;

import java.lang.annotation.*;

/**
 * @author niubq
 * @date 2020/6/30 13:38
 * @description
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface TargetClass {
    String value() default "";
}
