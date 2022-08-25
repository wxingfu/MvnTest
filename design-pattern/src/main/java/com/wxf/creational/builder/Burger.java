package com.wxf.creational.builder;

/*
 *
 * @author weixf
 * @date 2022-08-25
 */
public abstract class Burger implements Item {

    @Override
    public Packing packing() {
        return new Wrapper();
    }

    @Override
    public abstract float price();

}
