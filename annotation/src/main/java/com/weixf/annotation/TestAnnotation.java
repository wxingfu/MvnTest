package com.weixf.annotation;

/*
 *
 * @author weixf
 * @date 2022-05-03
 */
@A("testClass")
public class TestAnnotation {

    @B("testMethod")
    public void sayHello() {
        System.out.println("hello word");
    }
}
