package com.wxf.test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class PubFun {

    public static int calInterval(String cstartDate, String cendDate, String unit) {
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
        if (StrTool.cTrim(unit).equals("M")) {
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
        if (StrTool.cTrim(unit).equals("D")) {
            sCalendar.set(sYears, sMonths, sDays);
            eCalendar.set(eYears, eMonths, eDays);
            long lInterval = (eCalendar.getTime().getTime() - sCalendar.getTime().getTime()) / 86400000;
            interval = (int) lInterval;
        }
        return interval;
    }
}
