package com.weixf.thread.thread.other;

public class ThreadInterface implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}
