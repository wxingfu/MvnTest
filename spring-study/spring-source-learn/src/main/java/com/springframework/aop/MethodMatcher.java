package com.springframework.aop;

import java.lang.reflect.Method;

/*
 *
 * @author weixf
 * @date 2022-06-24
 */
public interface MethodMatcher {

    boolean matches(Method method, Class<?> targetClass);
}

