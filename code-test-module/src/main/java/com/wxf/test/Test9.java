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
        if (jsonArray != null) {
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
