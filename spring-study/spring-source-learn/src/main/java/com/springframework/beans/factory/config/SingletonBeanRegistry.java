package com.springframework.beans.factory.config;

/*
 *
 * @author weixf
 * @date 2022-06-23
 */
public interface SingletonBeanRegistry {

    Object getSingleton(String beanName);

    void addSingleton(String beanName, Object singletonObject);
}
