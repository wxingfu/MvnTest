package com.wxf.creational.prototype;

/*
 *
 * @author weixf
 * @date 2022-08-25
 */
public class Rectangle extends Shape {

    public Rectangle(){
        type = "Rectangle";
    }

    @Override
    public void draw() {
        System.out.println("Inside Rectangle::draw() method.");
    }

}
