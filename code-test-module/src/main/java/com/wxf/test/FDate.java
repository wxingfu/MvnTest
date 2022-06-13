package com.wxf.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

/*
 *
 * @author weixf
 * @date 2022-06-10
 */
public class FDate implements Cloneable, java.io.Serializable {

    private static final long serialVersionUID = -4834488486777133288L;
    private final static String pattern1 = "yyyy-MM-dd";
    private final static String pattern2 = "MM-dd-yyyy";
    private final static String pattern3 = "yyyy/MM/dd";
    private final static String pattern4 = "MM/dd/yyyy";
    private final static String pattern5 = "yyyyMMdd";


    public FDate() {
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    /**
     * 输入符合格式要求的日期字符串，返回日期类型变量
     * <p>
     * <b>支持的格式: </b>yyyy-MM-dd MM-dd-yyyy yyyy/MM/dd MM/dd/yyyy yyyyMMdd
     * <p>
     * <b>Example: </b>
     * <p>
     * getDate("2002-10-8") returns "Tue Oct 08 00:00:00 CST 2002"
     *
     * @param dateString 日期字符串
     * @return 日期类型变量
     */
    public Date getDate(String dateString) {
        Date tDate = null;
        String[] tsDate;
        try {
            if (dateString.indexOf("-") != -1 && dateString.indexOf("-") == 4) {
                //tDate = df1.parse(dateString);
                if (dateString.length() > 10)
                    dateString = dateString.substring(0, 10);
                tsDate = dateString.split("-");
                if (tsDate.length != 3)
                    return null;
                GregorianCalendar tGCalendar = new GregorianCalendar(Integer.parseInt(tsDate[0]), Integer.parseInt(tsDate[1]) - 1, Integer.parseInt(tsDate[2]));
                tDate = new Date(tGCalendar.getTimeInMillis());
            } else if (dateString.indexOf("-") != -1 && dateString.indexOf("-") == 2) {
                //tDate = df2.parse(dateString);
                if (dateString.length() > 10)
                    dateString = dateString.substring(0, 10);
                tsDate = dateString.split("-");
                if (tsDate.length != 3)
                    return null;
                GregorianCalendar tGCalendar = new GregorianCalendar(Integer.parseInt(tsDate[2]), Integer.parseInt(tsDate[1]) - 1, Integer.parseInt(tsDate[0]));
                tDate = new Date(tGCalendar.getTimeInMillis());
            } else if (dateString.indexOf("/") != -1 && dateString.indexOf("/") == 4) {
                //tDate = df3.parse(dateString);
                if (dateString.length() > 10)
                    dateString = dateString.substring(0, 10);
                tsDate = dateString.split("/");
                if (tsDate.length != 3)
                    return null;
                GregorianCalendar tGCalendar = new GregorianCalendar(Integer.parseInt(tsDate[0]), Integer.parseInt(tsDate[1]) - 1, Integer.parseInt(tsDate[2]));
                tDate = new Date(tGCalendar.getTimeInMillis());
            } else if (dateString.indexOf("/") != -1 && dateString.indexOf("/") == 2) {
                //tDate = df4.parse(dateString);
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

    /**
     * 输入日期类型变量，返回日期字符串
     * <p>
     * <b>Example: </b>
     * <p>
     * <p>
     * getString("Tue Oct 08 00:00:00 CST 2002") returns "2002-10-8"
     * <p>
     *
     * @param mDate 日期类型变量
     * @return 日期字符串
     */
    public String getString(Date mDate) {
        SimpleDateFormat df1 = new SimpleDateFormat(pattern1);
        String tString = null;
        if (mDate != null) {
            tString = df1.format(mDate);
        }
        return tString;
    }
}
