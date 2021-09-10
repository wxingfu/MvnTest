package com.wxf.thread.thread.other;

public class ThreadExtends extends Thread {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}
