package com.wxf.behavioral.observer;

/*
 *
 * @author weixf
 * @date 2022-08-23
 */
public interface Subject {
    void registerObserver(Observer o);

    void notifyAllObserver(String orderNo);
}
