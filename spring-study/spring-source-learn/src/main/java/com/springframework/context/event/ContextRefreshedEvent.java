package com.springframework.context.event;

import com.springframework.context.ApplicationContext;

/*
 *
 * @author weixf
 * @date 2022-06-23
 */
public class ContextRefreshedEvent extends ApplicationContextEvent {

    public ContextRefreshedEvent(ApplicationContext source) {
        super(source);
    }
}
