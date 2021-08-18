package com.wxf.test;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author wxf
 * @date 2021-03-10 18:53:58
 * @description
 */
public class Test7 {
    /**
     * @author wxf
     * @date 2021/3/10 18:54:07
     */
    public static void main(String[] args) {
        String s1 = "abc";
        String s2 = "bcd";

        char[] chars1 = s1.toCharArray();
        Arrays.sort(chars1);
        String ss1 = String.valueOf(chars1);

        char[] chars2 = s2.toCharArray();
        Arrays.sort(chars2);
        String ss2 = String.valueOf(chars2);

        // System.out.println(Objects.equals(new String(chars1), new String(chars2)));
        System.out.println(Objects.equals(ss1, ss2));
    }
}
