package com.springframework.aop;

import java.lang.reflect.Method;

/*
 *
 * @author weixf
 * @date 2022-06-24
 */
public interface MethodBeforeAdvice extends BeforeAdvice {

    void before(Method method, Object[] args, Object target) throws Throwable;
}
