package com.wxf.test;

/*
 *
 * @author weixf
 * @date 2022-09-21
 */
public class AbstractHandler implements HandlerStrategy {

    public void test() {
        System.out.println("AbstractHandler 父类方法执行了。。。。。。");
    }

    @Override
    public void commonMethod() {
        test();
    }
}
