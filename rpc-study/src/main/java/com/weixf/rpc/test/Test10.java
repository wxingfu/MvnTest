package com.weixf.rpc.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dtflys.forest.config.ForestConfiguration;
import com.weixf.rpc.client.MyClient2;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Test10 {
    public static void main(String[] args) {
        // 实例化Forest配置对象
        ForestConfiguration configuration = ForestConfiguration.configuration();
        // 通过Forest配置对象实例化Forest请求接口
        MyClient2 client = configuration.createInstance(MyClient2.class);

        String param = "<soap:Envelope\n" +
                "    xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                "    xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\"\n" +
                "    xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                "    <soap:Body></soap:Body>\n" +
                "</soap:Envelope>";
        String s = client.GetStockAcidRolling(param);
        System.out.println(s);
        Document document = Jsoup.parse(s);
        Elements result = document.getElementsByTag("GetStockAcidRollingResult");

        if (result.size() == 1) {
            String text = result.get(0).text();
            JSONObject jsonObject = JSON.parseObject(text);

            System.out.println(jsonObject);
        }
    }
}
