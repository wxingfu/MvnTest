package com.wxf.creational.builder;

/*
 *
 * @author weixf
 * @date 2022-08-25
 */
public interface Item {

    String name();

    Packing packing();

    float price();
}
