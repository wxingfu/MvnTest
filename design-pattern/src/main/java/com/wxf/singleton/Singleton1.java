package com.wxf.singleton;

/*
 *
 * @author weixf
 * @date 2022-06-21
 */
public class Singleton1 {

    private Singleton1(){}

    private static final Singleton1 instance = new Singleton1();

    public Singleton1 getInstance(){
        return instance;
    }

}
