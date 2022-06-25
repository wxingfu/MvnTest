package com.springframework.beans.factory.config;

/*
 *
 * @author weixf
 * @date 2022-06-23
 */
public class BeanReference {

    private final String beanName;

    public BeanReference(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }
}
