package com.wxf.creational.abstract_factory;

/*
 *
 * @author weixf
 * @date 2022-08-24
 */
public abstract class ShapeAbstractFactory {

    public abstract Shape getShape(String shape);

    public abstract Color getColor(String color);

}
