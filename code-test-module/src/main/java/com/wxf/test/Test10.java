package com.wxf.test;

import com.wxf.func.CommonUtil;

import java.text.ParseException;

/*
 *
 * @author weixf
 * @date 2022-06-10
 */
public class Test10 {
    public static void main(String[] args) throws ParseException {
        // String dateString = "20211012120121";
        // SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        // Date date = simpleDateFormat.parse(dateString);
        // System.out.println(date);
        //
        // simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        // String dateStr = simpleDateFormat.format(date);
        // System.out.println(dateStr);
        //
        // simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        // String timeStr = simpleDateFormat.format(date);
        // System.out.println(timeStr);


        // int m = PubFun.calInterval("2022-07-20", "2022-09-19", "M");
        // System.out.println(m);

        // int Y = PubFun.calInterval("2022-07-20", "2024-07-20", "Y");
        // System.out.println(Y);

        int D = CommonUtil.calInterval("2024-07-29", "2024-09-27", "D");
        System.out.println(D);
        // if (D >= 0) {
        //     m = m + 1;
        // }
        // System.out.println(m);

        // String dateString = "20211000";
        // SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        // Date date = simpleDateFormat.parse(dateString);
        // System.out.println(date);
        // simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        // String dateStr = simpleDateFormat.format(date);
        // System.out.println(dateStr);

        // String day = dateStr.substring(dateStr.lastIndexOf("-"));
        // System.out.println(day);
        //
        // String yearAndMonth = dateStr.substring(0, 7);
        // System.out.println(yearAndMonth);
        //
        // String monthAndDay = dateStr.substring(4);
        // System.out.println(monthAndDay);
        //
        // String year = dateStr.substring(0, 4);
        // System.out.println(year);

        // String renewalDueDate = "2022-07-15 17:06:00";
        // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // Date date = sdf.parse(renewalDueDate);
        // sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        // String nextPayTime = sdf.format(date);
        // System.out.println(nextPayTime);

        // String m = PubFun.newCalDate("2025-07-27", "M", -1);
        // System.out.println(m);
        // String y = PubFun.newCalDate("2025-07-27", "Y", -1);
        // System.out.println(y);
    }
}
