package com.springframework.context.annotation;

import java.lang.annotation.*;

/*
 *
 * @author weixf
 * @date 2022-06-23
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Scope {

    String value() default "singleton";
}
