package com.springframework.beans.factory;

/*
 *
 * @author weixf
 * @date 2022-06-23
 */
public interface InitializingBean {

    void afterPropertiesSet() throws Exception;
}
