package com.weixf.creational.builder;

/*
 *
 * @author weixf
 * @date 2022-08-25
 */
public class ChickenBurger extends Burger {

    @Override
    public float price() {
        return 50.5f;
    }

    @Override
    public String name() {
        return "Chicken Burger";
    }

}
