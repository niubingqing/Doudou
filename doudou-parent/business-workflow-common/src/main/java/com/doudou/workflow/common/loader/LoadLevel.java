
package com.doudou.workflow.common.loader;


import java.lang.annotation.*;

/**
 * @author niubq
 * @date 2020/7/1 15:07
 * @description
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface LoadLevel {
    /**
     * Name string.
     *
     * @return the string
     */
    String name();

    /**
     * Order int.
     *
     * @return the int
     */
    int order() default 0;

    /**
     * Scope enum.
     * @return
     */
    Scope scope() default Scope.SINGLETON;
}
