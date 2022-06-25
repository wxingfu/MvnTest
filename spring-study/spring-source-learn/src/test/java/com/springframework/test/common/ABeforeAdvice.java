package com.springframework.test.common;

import com.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/*
 *
 * @author weixf
 * @date 2022-06-24
 */
public class ABeforeAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {

    }
}

