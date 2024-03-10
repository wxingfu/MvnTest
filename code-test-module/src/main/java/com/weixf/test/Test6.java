package com.weixf.test;

import java.text.DecimalFormat;


public class Test6 {

    private static final String FORMATMODOL = "0.00";
    // 数字转换对象
    private static final DecimalFormat mDecimalFormat = new DecimalFormat(FORMATMODOL);

    public static void main(String[] args) {
        String format = mDecimalFormat.format("0.00");
        System.out.println(format);

    }
}
