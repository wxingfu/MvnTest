package com.wxf.test;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;

/**
 * @author wxf
 */
public class Test3 {

    public static void main(String[] args) {
        // BigDecimal sumAmount = new BigDecimal("2000000").add(new BigDecimal(""));
        // System.out.println(sumAmount);
        // String y = PubFun.newCalDate("2022-04-08", "D", 365 * 2);
        // System.out.println(y);

        String jsonString = "{\n" +
                "\t\"body\": {\n" +
                "\t\t\"body\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"code\": 1,\n" +
                "\t\t\t\t\"errorMsg\": \"保司应缴金额和水滴应缴金额必须一致：不一致\",\n" +
                "\t\t\t\t\"orderNo\": \"SD164940059648421171803\",\n" +
                "\t\t\t\t\"periodNum\": 2,\n" +
                "\t\t\t\t\"policyNo\": \"888050001636501\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"supplierNo\": \"hgrs\"\n" +
                "\t},\n" +
                "\t\"code\": 0,\n" +
                "\t\"supplierNo\": \"hgrs\"\n" +
                "}";

        JSONObject jsonObject = JSONObject.parseObject(jsonString);
        Integer code = (Integer) JSONPath.read(jsonObject.toJSONString(), "$body.body[0].code");
        String errorMsg = (String) JSONPath.read(jsonObject.toJSONString(), "$body.body[0].errorMsg");
        System.out.println(code);
        System.out.println(errorMsg);

    }
}
