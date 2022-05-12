package com.wxf.utils;

import com.alibaba.fastjson.JSON;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
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
    public void test2() {
        String s = "[{\"queueId\":\"202107161539\",\"data\":{\"acctdate\":\"LGX\",\"activityid\":\"11BC\",\"alloyno\":\"JBQ355B-Ti(<6)\",\"appid\":\"上料入炉\",\"betransunit\":\"t\",\"betranswgt\":21233,\"coilstus\":\"1\",\"crntmill\":\"RZ1\",\"datatypeidx\":\"O\",\"execjobfunc\":\"N\",\"grade\":\"1\",\"instdisp\":\"01\",\"invid\":\"J11-00001A030\",\"invlength\":10500,\"invthick\":230,\"invwidth\":1120,\"lastmill\":\"LGX\",\"locno\":\"RZ1\",\"manageip\":\"热轧\",\"orderitem\":\"W322020123024\",\"prodclassno\":\"B\",\"prodname\":\"品名\",\"prodtypeno\":\"HZR\",\"refnoa\":\"J11-00001A030\",\"srcfac\":\"1\",\"sysid\":\"IA\"}},{\"queueId\":\"202107201539\",\"data\":{\"acctdate\":\"LGX\",\"activityid\":\"11BC\",\"alloyno\":\"JBQ355B-Ti(<6)\",\"appid\":\"上料入炉\",\"betransunit\":\"t\",\"betranswgt\":21233,\"coilstus\":\"1\",\"crntmill\":\"RZ1\",\"datatypeidx\":\"O\",\"execjobfunc\":\"N\",\"grade\":\"1\",\"instdisp\":\"01\",\"invid\":\"J11-00001A030\",\"invlength\":10500,\"invthick\":230,\"invwidth\":1120,\"lastmill\":\"LGX\",\"locno\":\"RZ1\",\"manageip\":\"热轧\",\"orderitem\":\"W322020123024\",\"prodclassno\":\"B\",\"prodname\":\"品名\",\"prodtypeno\":\"HZR\",\"refnoa\":\"J11-00001A030\",\"srcfac\":\"1\",\"sysid\":\"IA\"}},{\"queueId\":\"202107201613\",\"data\":{\"acctdate\":\"LGX\",\"activityid\":\"11BC\",\"alloyno\":\"JBQ355B-Ti(<6)\",\"appid\":\"上料入炉\",\"betransunit\":\"t\",\"betranswgt\":21233,\"coilstus\":\"1\",\"crntmill\":\"RZ1\",\"datatypeidx\":\"O\",\"execjobfunc\":\"N\",\"grade\":\"1\",\"instdisp\":\"01\",\"invid\":\"J11-00001A030\",\"invlength\":10500,\"invthick\":230,\"invwidth\":1120,\"lastmill\":\"LGX\",\"locno\":\"RZ1\",\"manageip\":\"热轧\",\"orderitem\":\"W322020123024\",\"prodclassno\":\"B\",\"prodname\":\"品名\",\"prodtypeno\":\"HZR\",\"refnoa\":\"J11-00001A030\",\"srcfac\":\"1\",\"sysid\":\"IA\"}}]";

        com.alibaba.fastjson.JSONArray jsonArray = JSON.parseArray(s);

        for (Object o : jsonArray) {

            com.alibaba.fastjson.JSONObject jsonObject = (com.alibaba.fastjson.JSONObject) o;
            System.out.println(jsonObject.get("queueId"));
        }

        String ss = "{\"code\":\"200\",\"time\":\"2021-06-29 11-46-01\",\"message\":\"Success\"}";
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

    @Test
    public void test4(){

        String data = "code = 300 message = Error!activityId/ç\u0094\u009Fäº§æ´»å\u008A¨ã\u0080\u0081AlloyNo/ç\u0089\u008Cå\u008F·/é\u0092¢ç§\u008Dã\u0080\u0081å\u00AD\u0097æ®µä¸\u008Dè\u0083½ä¸ºç©º!é\u0095¿åº¦ä¸\u008Dç¬¦è§\u0084è\u008C\u0083!";
        String s = new String(data.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        new String(s.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
        System.out.println(s);

    }
}
