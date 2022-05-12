package com.wxf.thread.thread.other;

public class ThreadInterface implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}
