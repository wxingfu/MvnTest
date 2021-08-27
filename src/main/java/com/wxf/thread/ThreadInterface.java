package com.wxf.thread;

public class ThreadInterface implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}
