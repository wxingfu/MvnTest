package com.wxf.test;

import com.alibaba.fastjson.JSONObject;

/*
 *
 * @author weixf
 * @date 2022-07-13
 */
public class Test13 {

    public static void main(String[] args) throws Exception {
        // SAXBuilder sax = new SAXBuilder();
        // Document tDocument = sax.build(Files.newInputStream(Paths.get("D:\\WorkSpaces\\ideaProjects\\maven-test\\code-test-module\\src\\main\\resources\\JD77续期申请响应报文.xml")));
        // Element root = tDocument.getRootElement();
        // List<Element> list = root.getChild("Body").getChild("RenewContReturns").getChildren();
        // for (Element element : list) {
        //     String status = element.getChildText("Status");
        //     System.out.println(status);
        // }

        String ss = "{\"body\":{\"policyId\":\"16582842763560300460000\",\"policyNo\":\"120077000000299\",\"businessType\":1},\"header\":{\"sendTime\":1658284297444,\"comId\":\"470002\",\"requestType\":\"P142\",\"uuid\":\"1658284276356030046\",\"version\":\"1.2.1.0\"}}";

        JSONObject parseObject = JSONObject.parseObject("");
        System.out.println(parseObject + "");

    }
}
