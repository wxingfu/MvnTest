package com.springframework.beans.factory;

import com.springframework.beans.BeansException;

/*
 *
 * @author weixf
 * @date 2022-06-23
 */
public interface ObjectFactory<T> {

    T getObject() throws BeansException;
}
