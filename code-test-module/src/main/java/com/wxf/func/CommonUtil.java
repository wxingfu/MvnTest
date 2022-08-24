package com.wxf.func;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class CommonUtil {

    public static int calInterval(String cstartDate, String cendDate, String unit) {
        DateUtil fDate = new DateUtil();
        Date startDate = fDate.getDate(cstartDate);
        Date endDate = fDate.getDate(cendDate);

        int interval = 0;

        GregorianCalendar sCalendar = new GregorianCalendar();
        sCalendar.setTime(startDate);
        int sYears = sCalendar.get(Calendar.YEAR);
        int sMonths = sCalendar.get(Calendar.MONTH);
        int sDays = sCalendar.get(Calendar.DAY_OF_MONTH);

        GregorianCalendar eCalendar = new GregorianCalendar();
        eCalendar.setTime(endDate);
        int eYears = eCalendar.get(Calendar.YEAR);
        int eMonths = eCalendar.get(Calendar.MONTH);
        int eDays = eCalendar.get(Calendar.DAY_OF_MONTH);

        if (StringTool.cTrim(unit).equals("Y")) {
            interval = eYears - sYears;

            if (eMonths < sMonths) {
                interval--;
            } else {
                if (eMonths == sMonths && eDays < sDays) {
                    interval--;
                    if (eMonths == 1) {
                        if ((sYears % 4) == 0 && (eYears % 4) != 0) {
                            if (eDays == 28) {
                                interval++;
                            }
                        }
                    }
                }
            }
        }
        if (StringTool.cTrim(unit).equals("M")) {
            interval = eYears - sYears;
            interval *= 12;
            interval += eMonths - sMonths;
            if (eDays < sDays) {
                interval--;
                int maxDate = eCalendar.getActualMaximum(Calendar.DATE);
                if (eDays == maxDate) {
                    interval++;
                }
            }
        }
        if (StringTool.cTrim(unit).equals("D")) {
            sCalendar.set(sYears, sMonths, sDays);
            eCalendar.set(eYears, eMonths, eDays);
            long lInterval = (eCalendar.getTime().getTime() - sCalendar.getTime().getTime()) / 86400000;
            interval = (int) lInterval;
        }
        return interval;
    }

    public static boolean isEmpty(String str) {
        return null == str || "".equals(str.trim()) || "null".equals(str) || "undefined".equals(str);
    }

    public static String newCalDate(String baseDate, String unit, int aconunt) {
        Calendar c = Calendar.getInstance();// 获得一个日历的实例
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(baseDate);// 初始日期
        } catch (Exception e) {
            System.out.println("得到指定日期后推多少天出现异常,异常信息:" + e.toString());
        }
        c.setTime(date);// 设置日历时间
        // 在日历的日期按照给定的日期单位增加一个间隔;
        switch (unit) {
            case "Y":
                c.add(Calendar.YEAR, aconunt);
                break;
            case "M":
                c.add(Calendar.MONTH, aconunt);
                break;
            case "D":
                c.add(Calendar.DATE, aconunt);
                break;
            default:
                c.add(Calendar.DATE, aconunt);// 如果传错单位,默认日期单位是天
                break;
        }
        return sdf.format(c.getTime());
    }
}
