package com.wxf.test;

import java.math.BigDecimal;

public class Test9 {
    public static void main(String[] args) {
        String cp = "20000000";
        BigDecimal mCountPrem = new BigDecimal(cp);
        System.out.println(mCountPrem.toBigInteger());
    }
}
