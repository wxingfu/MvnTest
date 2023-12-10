package com.wxf.thread.test;

public class TSynchronized implements Runnable {
    static int i = 0;

    public static void main(String[] args) {
        TSynchronized tSynchronized = new TSynchronized();
        Thread aThread = new Thread(tSynchronized);
        Thread bThread = new Thread(tSynchronized);
        aThread.start();
        bThread.start();
        System.out.println("i = " + i);
    }

    public void increase() {
        i++;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            increase();
        }
    }
}
