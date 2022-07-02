package com.wxf.singleton;

public class Main {

    public static void main(String[] args) {
        Singleton1 instance1 = Singleton1.getInstance();
        System.out.println(instance1);

        Singleton2 instance2 = Singleton2.getInstance();
        Singleton3 instance3 = Singleton3.getInstance();
        Singleton4 instance4 = Singleton4.getInstance();
        Singleton5 instance5 = Singleton5.getInstance();
        Singleton6 instance6 = Singleton6.getInstance();
        Singleton7 instance7 = Singleton7.getInstance();
        Singleton8 instance8 = Singleton8.INSTANCE;


        Singleton instance = Singleton.getInstance("llll");
        System.out.println(instance.value);
    }
}
