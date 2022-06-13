package com.wxf.test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/*
 *
 * @author weixf
 * @date 2022-06-10
 */
public class PubFun {

    /**
     * 通过起始日期和终止日期计算以时间间隔单位为计量标准的时间间隔，舍弃法 author: HST
     * 起始日期，(String,格式："YYYY-MM-DD")
     *
     * @param cstartDate
     *            String 终止日期，(String,格式："YYYY-MM-DD")
     * @param cendDate
     *            String 时间间隔单位，可用值("Y"--年 "M"--月 "D"--日)
     * @param unit
     *            String 时间间隔,整形变量int
     * @return int
     */
    public static int calInterval(String cstartDate, String cendDate,
                                  String unit) {
        FDate fDate = new FDate();
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

        if (StrTool.cTrim(unit).equals("Y")) {
            interval = eYears - sYears;

            if (eMonths < sMonths) {
                interval--;
            } else {
                if (eMonths == sMonths && eDays < sDays) {
                    interval--;
                    if (eMonths == 1) { // 如果同是2月，校验润年问题
                        if ((sYears % 4) == 0 && (eYears % 4) != 0) { // 如果起始年是润年，终止年不是润年
                            if (eDays == 28) { // 如果终止年不是润年，且2月的最后一天28日，那么补一
                                //modify by ljw 2021.9.10 start
                                interval++;//yangguojun add 2018年2月28日 闰年2月29日出生，平年2月28日不满周岁
                                //end
                            }
                        }
                    }
                }
            }
        }
        if (StrTool.cTrim(unit).equals("M")) {
            interval = eYears - sYears;
            // interval = interval * 12;
            interval *= 12;
            // interval = eMonths - sMonths + interval;
            interval += eMonths - sMonths;

            if (eDays < sDays) {
                interval--;
                // eDays如果是月末，则认为是满一个月
                int maxDate = eCalendar.getActualMaximum(Calendar.DATE);
                if (eDays == maxDate) {
                    interval++;
                }
            }
        }
        if (StrTool.cTrim(unit).equals("D")) {
            // ====del===liuxs===2006-09-09=====修改日期间隔计算漏洞================BGN=========
            // interval = eYears - sYears;
            // // interval = interval * 365;
            // interval *= 365;
            // // interval = eDaysOfYear - sDaysOfYear + interval;
            // interval += eDaysOfYear - sDaysOfYear;
            //
            // // 处理润年
            // int n = 0;
            // eYears--;
            // if (eYears > sYears)
            // {
            // int i = sYears % 4;
            // if (i == 0)
            // {
            // sYears++;
            // n++;
            // }
            // int j = (eYears) % 4;
            // if (j == 0)
            // {
            // eYears--;
            // n++;
            // }
            // n += (eYears - sYears) / 4;
            // }
            // if (eYears == sYears)
            // {
            // int i = sYears % 4;
            // if (i == 0)
            // {
            // n++;
            // }
            // }
            // interval += n;
            // ====del===liuxs===2006-09-09=====修改日期间隔计算漏洞================END=========
            // ====add===liuxs===2006-09-09=====修改日期间隔计算漏洞================BGN=========
            sCalendar.set(sYears, sMonths, sDays);
            eCalendar.set(eYears, eMonths, eDays);
            long lInterval = (eCalendar.getTime().getTime() - sCalendar
                    .getTime().getTime()) / 86400000;
            interval = (int) lInterval;
            // ====add===liuxs===2006-09-09=====修改日期间隔计算漏洞================END=========

        }
        return interval;
    }
}
