package com.wxf.rpc.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dtflys.forest.config.ForestConfiguration;
import com.wxf.rpc.client.MyClient3;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Test11 {
    public static void main(String[] args) {


        // 实例化Forest配置对象
        ForestConfiguration configuration = ForestConfiguration.configuration();
        // 通过Forest配置对象实例化Forest请求接口
        MyClient3 client = configuration.createInstance(MyClient3.class);

        String param = "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                "  <soap:Body>\n" +
                "    <GetMaterialForDate xmlns=\"http://tempuri.org/\">\n" +
                "      <strDispatchStartTime></strDispatchStartTime>\n" +
                "      <strDispatchEndTime></strDispatchEndTime>\n" +
                "      <strApproachStartTime>2021-06-17 00:00:00</strApproachStartTime>\n" +
                "      <strApproachEndTime>2021-06-18 00:00:00</strApproachEndTime>\n" +
                "      <strCategoryId></strCategoryId>\n" +
                "      <strMaterielId>9999</strMaterielId>\n" +
                "      <strType>2</strType>\n" +
                "    </GetMaterialForDate>\n" +
                "  </soap:Body>\n" +
                "</soap:Envelope>";
        String s = client.GetMaterialForDate(param);
        System.out.println(s);
        Document document = Jsoup.parse(s);
        Elements result = document.getElementsByTag("newdataset");

        if (result.size() != 0) {
            String text = result.get(0).text();
            JSONObject jsonObject = JSON.parseObject(text);

            System.out.println(jsonObject);
        }

    }
}
