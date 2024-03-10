package com.weixf.func;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;


public class DateUtil implements Cloneable, Serializable {

    private final static String pattern1 = "yyyy-MM-dd";

    public DateUtil() {
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }


    public Date getDate(String dateString) {
        Date tDate = null;
        String[] tsDate;
        try {
            if (dateString.indexOf("-") != -1 && dateString.indexOf("-") == 4) {
                if (dateString.length() > 10)
                    dateString = dateString.substring(0, 10);
                tsDate = dateString.split("-");
                if (tsDate.length != 3)
                    return null;
                GregorianCalendar tGCalendar = new GregorianCalendar(Integer.parseInt(tsDate[0]), Integer.parseInt(tsDate[1]) - 1, Integer.parseInt(tsDate[2]));
                tDate = new Date(tGCalendar.getTimeInMillis());
            } else if (dateString.indexOf("-") != -1 && dateString.indexOf("-") == 2) {
                // tDate = df2.parse(dateString);
                if (dateString.length() > 10)
                    dateString = dateString.substring(0, 10);
                tsDate = dateString.split("-");
                if (tsDate.length != 3)
                    return null;
                GregorianCalendar tGCalendar = new GregorianCalendar(Integer.parseInt(tsDate[2]), Integer.parseInt(tsDate[1]) - 1, Integer.parseInt(tsDate[0]));
                tDate = new Date(tGCalendar.getTimeInMillis());
            } else if (dateString.indexOf("/") != -1 && dateString.indexOf("/") == 4) {
                // tDate = df3.parse(dateString);
                if (dateString.length() > 10)
                    dateString = dateString.substring(0, 10);
                tsDate = dateString.split("/");
                if (tsDate.length != 3)
                    return null;
                GregorianCalendar tGCalendar = new GregorianCalendar(Integer.parseInt(tsDate[0]), Integer.parseInt(tsDate[1]) - 1, Integer.parseInt(tsDate[2]));
                tDate = new Date(tGCalendar.getTimeInMillis());
            } else if (dateString.indexOf("/") != -1 && dateString.indexOf("/") == 2) {
                // tDate = df4.parse(dateString);
                if (dateString.length() > 10)
                    dateString = dateString.substring(0, 10);
                tsDate = dateString.split("/");
                if (tsDate.length != 3)
                    return null;
                GregorianCalendar tGCalendar = new GregorianCalendar(Integer.parseInt(tsDate[2]), Integer.parseInt(tsDate[1]) - 1, Integer.parseInt(tsDate[0]));
                tDate = new Date(tGCalendar.getTimeInMillis());
            } else if (dateString.length() == 8) {
                String[] ttDate = new String[3];
                ttDate[0] = dateString.substring(0, 4);
                ttDate[1] = dateString.substring(4, 6);
                ttDate[2] = dateString.substring(6, 8);

                GregorianCalendar tGCalendar = new GregorianCalendar(Integer.parseInt(ttDate[0]), Integer.parseInt(ttDate[1]) - 1, Integer.parseInt(ttDate[2]));
                tDate = new Date(tGCalendar.getTimeInMillis());
            } else
                tDate = null;
        } catch (Exception e) {
            System.out.println("Eception:" + e.getMessage());
        }
        return tDate;
    }

    public String getString(Date mDate) {
        SimpleDateFormat df1 = new SimpleDateFormat(pattern1);
        String tString = null;
        if (mDate != null) {
            tString = df1.format(mDate);
        }
        return tString;
    }
}
