package com.wxf.rpc.test;

import com.dtflys.forest.config.ForestConfiguration;
import com.wxf.rpc.client.MyClient4;

/**
 * @author weixf
 * @since 2022-01-05
 */
public class Test4 {

    public static void main(String[] args) throws Exception {
        // 实例化Forest配置对象
        ForestConfiguration configuration = ForestConfiguration.configuration();
        configuration.setBackendName("httpclient");
        // 通过Forest配置对象实例化Forest请求接口
        MyClient4 client = configuration.createInstance(MyClient4.class);

        String param = "<?xml version=\"1.0\" encoding=\"GBK\"?><TranData><Head><TranDate>20211227</TranDate><TranTime>213600</TranTime><TranCom>07</TranCom><BankCode>0112</BankCode><TellerNo>sys</TellerNo><NodeNo>3401010101</NodeNo><TranNo>1787600</TranNo><FuncFlag>782</FuncFlag><ServiceId>115</ServiceId></Head><Body/></TranData>";

        String reqParam = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ker=\"http://kernel.ablinkbank.sinosoft.com\">" +
                "   <soapenv:Header/>" +
                "   <soapenv:Body>" +
                "      <ker:service>" +
                "         <ker:cData><![CDATA[" + param + "]]</ker:cData>" +
                "      </ker:service>" +
                "   </soapenv:Body>" +
                "</soapenv:Envelope>";

        System.out.println(reqParam);
        String result = client.service(reqParam);
        System.out.println(result);
    }
}
