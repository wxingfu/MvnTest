package com.weixf.thread.test;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class CallableTask implements Callable {

    static int count;

    public CallableTask(int count) {
        CallableTask.count = count;
    }

    public static void main(String[] args) throws Exception {

        FutureTask<Integer> task = new FutureTask<>(() -> {
            for (int i = 0; i < 1000; i++) {
                count++;
            }
            return count;
        });
        Thread thread = new Thread(task);
        thread.start();

        Integer total = task.get();
        System.out.println("total = " + total);
    }

    @Override
    public Object call() {
        return count;
    }
}