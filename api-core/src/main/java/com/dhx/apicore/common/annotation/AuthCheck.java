package com.dhx.apicore.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author <a href="https://blog.dhx.icu/"> adorabled4 </a>
 * @className AuthCheck
 * @date : 2023/01/07/ 14:57
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthCheck {

    /**
     *
     * @return
     */
    String mustRole() default "";

}
