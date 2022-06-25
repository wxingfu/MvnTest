package com.wxf.singleton;

/*
 *
 * @author weixf
 * @date 2022-06-21
 */
public class Singleton2 {

    private static Singleton2 instance = null;

    public static synchronized Singleton2 getInstance() {
        if (instance == null) {
            instance = new Singleton2();
        }
        return instance;
    }

}
