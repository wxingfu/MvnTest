package com.wxf.test;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;


public class Test6 {

    private static String FORMATMODOL = "0.00";
    // 数字转换对象
    private static DecimalFormat mDecimalFormat = new DecimalFormat(FORMATMODOL);

    public static void main(String[] args) {
        String format = mDecimalFormat.format("0.00");
        System.out.println(format);

    }
}
