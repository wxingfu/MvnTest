package com.springframework.test.common.event;

import com.springframework.context.ApplicationListener;
import com.springframework.context.event.ContextRefreshedEvent;

/*
 *
 * @author weixf
 * @date 2022-06-24
 */
public class ContextRefreshedEventListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println(this.getClass().getName());
    }
}
