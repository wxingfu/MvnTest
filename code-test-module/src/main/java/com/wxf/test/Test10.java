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
        String dateString = "202208";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMM");
        Date date = simpleDateFormat.parse(dateString);

        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = simpleDateFormat.format(date);

        int m = PubFun.calInterval("2022-06-11", dateStr, "M") + 1;
        int D = PubFun.calInterval("2022-08-11", "2022-08-11", "D");
        if (D >= 0) {
            m = m + 1;
        }
        System.out.println(m);

    }
}
