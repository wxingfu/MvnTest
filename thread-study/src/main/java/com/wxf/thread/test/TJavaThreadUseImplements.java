package com.wxf.thread.test;

public class TJavaThreadUseImplements implements Runnable{

    static int count;

    @Override
    public synchronized void run() {
        for(int i = 0;i < 10000;i++){
            count++;
        }
        System.out.println(count);
    }

    public static void main(String[] args) throws InterruptedException {

        new Thread(new TJavaThreadUseImplements()).start();
        System.out.println("count = " + count);
    }

}
