package com.weixf.creational.abstract_factory;

/*
 *
 * @author weixf
 * @date 2022-08-24
 */
public class ShapeFactoryProducer {

    public static ShapeAbstractFactory getFactory(String choice) {
        if ("SHAPE".equalsIgnoreCase(choice)) {
            return new ShapeFactory();
        } else if ("COLOR".equalsIgnoreCase(choice)) {
            return new ColorFactory();
        }
        return null;
    }
}
