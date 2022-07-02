package com.wxf.utils;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class JettisonTest {

    @Test
    public void test() throws JSONException {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "jack");
        jsonObject.put("age", 20);
        //
        // System.out.println(jsonObject);
        //
        // JSONArray address = new JSONArray();
        // address.put("address1");
        // address.put("address2");
        //
        // jsonObject.put("address", address);
        //
        // System.out.println(jsonObject);


        JSONArray friends = new JSONArray();

        for (int i = 0; i < 10; i++) {
            Map<String, Object> map = new HashMap<String, Object>();

            map.put("key" + i, "value" + i);
            map.put("aaa" + i, jsonObject);

            friends.put(map);
        }
        System.out.println(friends);
    }

    @Test
    public void test1() throws JSONException {
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("name", "jack");
        jsonObject1.put("age", 20);


        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("name", "lucy");
        jsonObject2.put("length", 100);

        JSONArray jsonArray = new JSONArray();

        jsonArray.put(jsonObject1);
        jsonArray.put(jsonObject2);

        System.out.println(jsonArray);
    }

    @Test
    public void test3() throws JSONException {
        String aaa = "{code=300, time=2021-08-03 17-01-03, message=Error!CreateId/建立人、字段不能为空!长度不符规范!}";
        // String aaa = "{code=200, time=2021-08-03 17-05-35, message=Success}";
        Map<String, String> stringStringMap = mapStringToMap(aaa);
        JSONObject jsonObject = new JSONObject(stringStringMap);
        System.out.println(jsonObject);
    }

    public static Map<String, String> mapStringToMap(String str) {
        str = str.substring(1, str.length() - 1);
        String[] strs = str.split(",");
        Map<String, String> map = new HashMap<String, String>();
        for (String string : strs) {
            String key = string.split("=")[0].trim();
            String value = string.split("=")[1].trim();
            map.put(key, value);
        }
        return map;
    }
}
