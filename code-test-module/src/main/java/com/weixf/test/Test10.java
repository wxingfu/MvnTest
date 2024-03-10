package com.weixf.test;

import java.text.ParseException;

/*
 *
 * @author weixf
 * @date 2022-06-10
 */
public class Test10 {
    public static void main(String[] args) throws ParseException {
        String key = PropertiesConfig.getString("key1");
        System.out.println(key);

        // double a = 11.00;
        // System.out.println(a % 3);


    }
}
