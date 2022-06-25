package com.wxf.test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test9 {
    public static void main(String[] args) throws ParseException {
        // String cp = "20000000";
        // BigDecimal mCountPrem = new BigDecimal(cp);
        // System.out.println(mCountPrem.toBigInteger());

        // Instant now = Instant.now();
        // System.out.println(now.toEpochMilli());
        String date = "2022-06-21 16:15:56";
        Date datetime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);
        Long timestamp = datetime.getTime();
        System.out.println(timestamp);

        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(timestamp));
        System.out.println(time);
        //
        //
        // time = "12:00:00";
        // Date timeFormat = new SimpleDateFormat("HH:mm:ss").parse(time);
        // Long timestamp2 = timeFormat.getTime();
        // System.out.println(timestamp2);
        //
        // time = new SimpleDateFormat("HH:mm:ss").format(new Date(timestamp2));
        // System.out.println(time);


        // time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(1640426071066L));
        // time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(1655799356374L));
        // System.out.println(time);


        long l = System.currentTimeMillis();
        System.out.println(String.valueOf(l));

        time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(l));
        System.out.println(time);

    }



    public static StringBuffer getAllKey(JSONObject jsonObject) {
        StringBuffer stringBuffer = new StringBuffer();
        for (String key : jsonObject.keySet()) {
            stringBuffer.append(key).append(" , ");
            if (jsonObject.get(key) instanceof JSONObject) {
                JSONObject innerObject = (JSONObject) jsonObject.get(key);
                stringBuffer.append(getAllKey(innerObject));
            } else if (jsonObject.get(key) instanceof JSONArray) {
                JSONArray innerObject = (JSONArray) jsonObject.get(key);
                stringBuffer.append(getAllKey(innerObject));
            }
        }
        return stringBuffer;
    }

    public static StringBuffer getAllKey(JSONArray jsonArray) {
        StringBuffer stringBuffer = new StringBuffer();
        if (jsonArray != null ) {
            for (Object key : jsonArray) {
                if (key instanceof JSONObject) {
                    JSONObject innerObject = (JSONObject) key;
                    stringBuffer.append(getAllKey(innerObject));
                } else if (key instanceof JSONArray) {
                    JSONArray innerObject = (JSONArray) key;
                    stringBuffer.append(getAllKey(innerObject));
                }
            }
        }
        return stringBuffer;
    }


    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("-?[0-9]+.?[0-9]+");
        Matcher isNum = pattern.matcher(str);
        return isNum.matches();
    }
}
