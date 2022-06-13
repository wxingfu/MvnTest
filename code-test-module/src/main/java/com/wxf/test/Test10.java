package com.wxf.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 *
 * @author weixf
 * @date 2022-06-10
 */
public class Test10 {
    public static void main(String[] args) throws ParseException {

        // 入参 期数 202208

        // 期数转换 2022-08-01

        // 生效日 2022-06-11

        // 第几期  （入参期数-生效日）+1

        // 应交日 2022-08-11

        // (当前请求日期 - 应交日) >= 0  第几期+1，否则不加


        String dateString = "202208";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMM");
        Date date = simpleDateFormat.parse(dateString);

        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = simpleDateFormat.format(date);
        // System.out.println(dateStr);

        int m = PubFun.calInterval("2022-06-11", dateStr, "M") + 1;
        int D = PubFun.calInterval("2022-08-11", "2022-08-11", "D");

        if (D >= 0) {
            m = m + 1;
        }
        System.out.println(m);

    }
}
