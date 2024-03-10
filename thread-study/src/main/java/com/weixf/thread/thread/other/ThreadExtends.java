package com.weixf.thread.thread.other;

public class ThreadExtends extends Thread {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}
