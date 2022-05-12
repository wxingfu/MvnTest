package com.weixf.annotation;

import java.lang.annotation.*;

/*
 *
 * @author weixf
 * @date 2022-05-03
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface B {

    String value() default "";

}