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

        String str = "{\n" +
                "\t\"header\": {\n" +
                "\t\t\"version\": \"1.2.1.4\",\n" +
                "\t\t\"requestType\": \"P142\",\n" +
                "\t\t\"uuid\": \"1234567890123456789\",\n" +
                "\t\t\"comId\": \"001\",\n" +
                "\t\t\"responseTime\": 1615859768427,\n" +
                "\t\t\"responseCode\": \"000000\",\n" +
                "\t\t\"responseInfo\": \"成功\"\n" +
                "\t},\n" +
                "\t\"body\": {\n" +
                "\t\t\"policyNo\": \"\",\n" +
                "\t\t\"businessType\": \"\",\n" +
                "\t\t\"businessCode\": \"000000\",\n" +
                "\t\t\"businessMsg\": \"成功\",\n" +
                "\t\t\"customerInfoList\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"identityList\": [\n" +
                "\t\t\t\t\t1,\n" +
                "\t\t\t\t\t2\n" +
                "\t\t\t\t],\n" +
                "\t\t\t\t\"name\": \"张三\",\n" +
                "\t\t\t\t\"sex\": 1,\n" +
                "\t\t\t\t\"nationality\": 1,\n" +
                "\t\t\t\t\"certificateType\": 1,\n" +
                "\t\t\t\t\"certificateNo\": \"513436200010226917\",\n" +
                "\t\t\t\t\"certificateValidityType\": 1,\n" +
                "\t\t\t\t\"certificateBeginDate\": \"YYYY-MM-dd\",\n" +
                "\t\t\t\t\"certificateEndDate\": \"YYYY-MM-dd\",\n" +
                "\t\t\t\t\"provinceId\": \"110000\",\n" +
                "\t\t\t\t\"cityId\": \"110100\",\n" +
                "\t\t\t\t\"areaId\": \"110112\",\n" +
                "\t\t\t\t\"address\": \"北京市通州区科创十一街\",\n" +
                "\t\t\t\t\"mobile\": \"13888888888\",\n" +
                "\t\t\t\t\"jobCode\": \"A010402\",\n" +
                "\t\t\t\t\"jobName\": \"共青团负责人\",\n" +
                "\t\t\t\t\"certificateFrontUrl\": \"https://www.baidu.com\",\n" +
                "\t\t\t\t\"certificateBackUrl\": \"https://www.baidu.com\",\n" +
                "\t\t\t\t\"relationMaterialList\": [\n" +
                "\t\t\t\t\t{\n" +
                "\t\t\t\t\t\t\"relationMaterialName\": \"\",\n" +
                "\t\t\t\t\t\t\"relationMaterialUrl\": \"\"\n" +
                "\t\t\t\t\t}\n" +
                "\t\t\t\t],\n" +
                "\t\t\t\t\"companyInfo\": {\n" +
                "\t\t\t\t\t\"businessScope\": \"\",\n" +
                "\t\t\t\t\t\"legalPersonName\": \"\",\n" +
                "\t\t\t\t\t\"legalPersonCardType\": 1,\n" +
                "\t\t\t\t\t\"legalPersonCardNo\": \"\",\n" +
                "\t\t\t\t\t\"legalPersonCardTremType\": 0,\n" +
                "\t\t\t\t\t\"legalPersonCardBeginDate\": \"\",\n" +
                "\t\t\t\t\t\"legalPersonCardEndDate\": \"\",\n" +
                "\t\t\t\t\t\"certigierName\": \"\",\n" +
                "\t\t\t\t\t\"certigierCardType\": \"\",\n" +
                "\t\t\t\t\t\"certigierCardNo\": \"\",\n" +
                "\t\t\t\t\t\"certigierCardTremType\": 0,\n" +
                "\t\t\t\t\t\"certigierCardBeginDate\": \"\",\n" +
                "\t\t\t\t\t\"certigierCardEndDate\": \"\",\n" +
                "\t\t\t\t\t\"benefitOwnerName\": \"\",\n" +
                "\t\t\t\t\t\"benefitOwnerAddress\": \"\",\n" +
                "\t\t\t\t\t\"benefitOwnerCardType\": 0,\n" +
                "\t\t\t\t\t\"benefitOwnerCardNo\": \"\",\n" +
                "\t\t\t\t\t\"benefitOwnerCardTremType\": 0,\n" +
                "\t\t\t\t\t\"benefitOwnerCardBeginDate\": \"\",\n" +
                "\t\t\t\t\t\"benefitOwnerCardEndDate\": \"\"\n" +
                "\t\t\t\t}\n" +
                "\t\t\t}\n" +
                "\t\t]\n" +
                "\t}\n" +
                "}";

        JSONObject jsonObject = JSONObject.parseObject(str);
        String responseCode = (String) JSONPath.read(jsonObject.toJSONString(), "$header.responseCode");
        System.out.println(responseCode);
    }
}
