package com.wxf.collection;

import java.math.BigDecimal;

public class MyDataType {
    public static void main(String[] args) {

        // 默认是double型，会丢失精度
        BigDecimal decimal = new BigDecimal(0.01D);

        BigDecimal decimal1 = new BigDecimal("0.01");
        BigDecimal decimal2 = BigDecimal.valueOf(0.01D);

        System.out.println(decimal.equals(decimal1));
        System.out.println(decimal2.equals(decimal1));
    }
}
