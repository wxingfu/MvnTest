package com.weixf.creational.builder;

/*
 *
 * @author weixf
 * @date 2022-08-25
 */
public class Coke extends ColdDrink {

    @Override
    public float price() {
        return 30.0f;
    }

    @Override
    public String name() {
        return "Coke";
    }

}
