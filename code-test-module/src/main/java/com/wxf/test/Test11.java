package com.wxf.test;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;

import javax.xml.transform.Source;

/*
 *
 * @author weixf
 * @date 2022-06-13
 */
public class Test11 {
    public static void main(String[] args) {

        // String date = "2022-06-13";
        // String replace = date.replaceAll("-", "").substring(0,6);
        // System.out.println(replace);

        String str = "";

        JSONObject jsonObject = JSONObject.parseObject(str);
        String responseCode = (String) JSONPath.read(jsonObject.toJSONString(), "$header.responseCode");
        System.out.println(responseCode);
    }
}
