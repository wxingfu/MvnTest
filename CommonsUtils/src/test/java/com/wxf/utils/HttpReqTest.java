package com.wxf.utils;

import com.alibaba.fastjson.JSONObject;
import com.wxf.utils.http.HttpUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HttpReqTest {

    @Test
    public void test() {
        HashMap<String, Object> reqMap = new HashMap<String, Object>();
        List<String> tIDNolist = new ArrayList<String>();
        tIDNolist.add("110101198205080731");
        reqMap.put("supplierNo", "hgrs");
        HashMap<String, Object> bodyMap = new HashMap<String, Object>();
        bodyMap.put("orderNo", "SD164923024056581579912");
        bodyMap.put("certificateNoList", tIDNolist);
        bodyMap.put("certificateType", 10);
        reqMap.put("body", bodyMap);
        String jsonString = JSONObject.toJSONString(reqMap);

        String url = "http://idscapitest.huaguilife.cn/dsc/o/UserCertificateInfo/5023";
        String postFromJson = HttpUtils.postFromJson(url, jsonString);
        System.out.println(postFromJson);
    }
}
