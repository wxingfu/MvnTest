package com.wxf.singleton;

/**
 * 2.饿汉式（静态代码块）【可用】
 * 这种方式和1的方式其实类似，只不过将类实例化的过程放在了静态代码块中，
 * 也是在类装载的时候，就执行静态代码块中的代码，初始化类的实例。
 * 优缺点和1一样。
 */
public class Singleton2 {

    private Singleton2() {
    }

    private static Singleton2 instance;

    static {
        instance = new Singleton2();
    }

    public static Singleton2 getInstance() {
        return instance;
    }

}
