package com.springframework.context;

import java.util.EventObject;

/*
 *
 * @author weixf
 * @date 2022-06-23
 */
public abstract class ApplicationEvent extends EventObject {

    public ApplicationEvent(Object source) {
        super(source);
    }
}
