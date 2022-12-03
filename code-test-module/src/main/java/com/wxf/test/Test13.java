package com.wxf.test;

import com.alibaba.fastjson.JSONObject;

import java.math.BigDecimal;

/*
 *
 * @author weixf
 * @date 2022-07-13
 */
public class Test13 {

    public static void main(String[] args) throws Exception {

        BigDecimal SumPrem = new BigDecimal("71");

        if (SumPrem.compareTo(new BigDecimal("70")) > 0) {
            System.out.println("true");
        }

    }
}
