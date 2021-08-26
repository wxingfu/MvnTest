package com.wxf.spring;

public class Client {
    public static void main(String[] args) {
        Fruit fruit = Factory.getInstance("com.wxf.spring.Apple");
        if (fruit != null) {
            fruit.eat();
        }
    }
}
