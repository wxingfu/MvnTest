package com.springframework.context;

import java.util.EventListener;

/*
 *
 * @author weixf
 * @date 2022-06-23
 */
public interface ApplicationListener<E extends ApplicationEvent> extends EventListener {

    void onApplicationEvent(E event);
}
