package com.springframework.aop;

/*
 *
 * @author weixf
 * @date 2022-06-24
 */
public interface Pointcut {

    ClassFilter getClassFilter();

    MethodMatcher getMethodMatcher();
}
