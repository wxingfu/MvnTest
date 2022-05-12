package com.weixf.annotation;

/*
 *
 * @author weixf
 * @date 2022-05-03
 */

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface A {
    String value() default "";
}

