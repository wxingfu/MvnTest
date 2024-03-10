package com.weixf.thread.test;

import java.util.concurrent.TimeUnit;

public class TimeUnitTest {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("Sleeping for 4 minutes using Thread.sleep()");
        Thread.sleep(4 * 60 * 1000);
        System.out.println("Sleeping for 4 minutes using TimeUnit sleep()");

        TimeUnit.SECONDS.sleep(4);
        TimeUnit.MINUTES.sleep(4);
        TimeUnit.HOURS.sleep(1);
        TimeUnit.DAYS.sleep(1);
    }
}
