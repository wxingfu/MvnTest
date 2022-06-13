package com.wxf.test;

import javax.xml.transform.Source;

/*
 *
 * @author weixf
 * @date 2022-06-13
 */
public class Test11 {
    public static void main(String[] args) {

        String date = "2022-06-13";
        String replace = date.replaceAll("-", "").substring(0,6);
        System.out.println(replace);

    }
}
