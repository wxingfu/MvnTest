package com.wxf.behavioral.observer;

import java.util.ArrayList;
import java.util.List;

/*
 *
 * @author weixf
 * @date 2022-08-23
 */
public interface NewSubject {

    List<Observer> list = new ArrayList<>();

    default void registerObserver(Observer o) {
        list.add(o);
    }

    default void notifyAllObserver(String orderNo) {
        list.forEach(c -> c.notify(orderNo));
    }
}
