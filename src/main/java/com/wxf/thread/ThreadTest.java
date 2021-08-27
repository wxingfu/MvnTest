package com.wxf.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadTest {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());

        ThreadExtends threadExtends = new ThreadExtends();
        threadExtends.start();

        ThreadInterface threadInterface = new ThreadInterface();
        Thread thread = new Thread(threadInterface);
        thread.start();


        ExecutorService threadPool = Executors.newCachedThreadPool();
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        });
    }
}
