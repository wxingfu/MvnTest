package com.wxf.creational.abstract_factory;

/*
 *
 * @author weixf
 * @date 2022-08-24
 */
public class Green implements Color {
    @Override
    public void fill() {
        System.out.println("Inside Green::fill() method.");
    }
}
