package com.wxf.test;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * @author wxingfu
 * @since 2021/5/25 12:16
 */
public class Test8 {

    public static void main(String[] args) {
        while(true){
            // LocalDateTime now = LocalDateTime.now();
            // DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            // String format = dateTimeFormatter.format(now);
            // if (format.equals("2021-05-25 12:25:00")) {
            //     System.out.println(format);
            //     break;
            // }else {
            //     System.out.println(false);
            // }
            LocalTime now = LocalTime.now();
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            String format = dateTimeFormatter.format(now);
            if (format.equals("12:27:00")) {
                System.out.println(format);
                break;
            }else {
                System.out.println(false);
            }
        }
    }
}
