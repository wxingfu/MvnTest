package com.wxf.spring.freemarker;

import com.alibaba.fastjson.JSONObject;
import com.wxf.spring.pdm.PDM;
import com.wxf.spring.pdm.Parser;

/**
 * @author weixf
 * @since 2022-01-07
 */
public class Main {

    public static void main(String[] args) {
        try {
            Parser parser = new Parser();
            PDM pdm = parser.pdmParser("D:\\pdmtest.pdm");
            System.out.println(pdm.toString());
            String s = JSONObject.toJSONString(pdm);
            System.out.println(s);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
