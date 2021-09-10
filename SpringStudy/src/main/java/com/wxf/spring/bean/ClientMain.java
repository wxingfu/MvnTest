package com.wxf.spring.bean;

public class ClientMain {

    public static void main(String[] args) {
        Fruit fruit = Factory.getInstance("com.wxf.spring.bean.Apple");
        if (fruit != null) {
            fruit.eat();
        }
    }

}
