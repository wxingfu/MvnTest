package com.weixf.creational.abstract_factory;

/*
 *
 * @author weixf
 * @date 2022-08-24
 */
public class Blue implements Color {
    @Override
    public void fill() {
        System.out.println("Inside Blue::fill() method.");
    }
}
