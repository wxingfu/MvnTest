package com.wxf.factory;

/*
 *
 * @author weixf
 * @date 2022-06-21
 */
public class Factory {

    public static Sample creator(int which) {
        if (which == 1) {
            return new SampleA();
        } else if (which == 2) {
            return new SampleB();
        }
        return null;
    }
}
