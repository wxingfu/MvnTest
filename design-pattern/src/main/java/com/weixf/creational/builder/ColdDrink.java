package com.weixf.creational.builder;

/*
 *
 * @author weixf
 * @date 2022-08-25
 */
public abstract class ColdDrink implements Item {

    @Override
    public Packing packing() {
        return new Bottle();
    }

    @Override
    public abstract float price();

}
