package com.springframework.beans;

/*
 *
 * @author weixf
 * @date 2022-06-23
 */
public class BeansException extends RuntimeException {

    public BeansException(String msg) {
        super(msg);
    }

    public BeansException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
