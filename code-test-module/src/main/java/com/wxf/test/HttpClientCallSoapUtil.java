package com.wxf.test;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HttpClientCallSoapUtil {
    static int socketTimeout = 300000;// 请求超时时间
    static int connectTimeout = 300000;// 传输超时时间

    /**
     * 使用SOAP1.1发送消息
     *
     * @param postUrl
     * @param soapXml
     * @param soapAction
     * @return
     */
    public static String doPostSoap(String postUrl, String soapXml,
                                    String soapAction) {
        long start = System.currentTimeMillis();
        String retStr = "";
        // 创建HttpClientBuilder
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        // HttpClient
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
        HttpPost httpPost = new HttpPost(postUrl);
        // 设置请求和传输超时时间
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(socketTimeout)
                .setConnectTimeout(connectTimeout).build();
        httpPost.setConfig(requestConfig);
        try {
            httpPost.setHeader("Content-Type", "text/xml;charset=UTF-8");
			// httpPost.setHeader("SOAPAction", soapAction);
            StringEntity data = new StringEntity(soapXml, Charset.forName("UTF-8"));
            httpPost.setEntity(data);
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            CloseableHttpResponse response = closeableHttpClient.execute(httpPost);
            HttpEntity httpEntity = response.getEntity();
            if (httpEntity != null) {
                // 打印响应内容
                retStr = EntityUtils.toString(httpEntity, "UTF-8");

                // logger.info("response:" + retStr);
            }
            long end = System.currentTimeMillis();
            // 释放资源
            closeableHttpClient.close();
        } catch (Exception e) {
        }
        return retStr;
    }

    public static String sendXml(String postUrl, String content) {
        String orderSoapXml = "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"http://services.webservice.sinosoft.com\">"
                + "<soap:Header/>"
                + "<soap:Body>"
                + "<ser:submitData>"
                + "<ser:cData1><![CDATA[" + content + "]]></ser:cData1>"
                + "</ser:submitData>"
                + "</soap:Body>"
                + "</soap:Envelope>";
        String response = doPostSoap(postUrl, orderSoapXml, "");
        String responseXml = response.substring(response.indexOf("<ns:return>") + 11, response.indexOf("</ns:return>"));
        responseXml = responseXml.replace("&lt;", "<");
        responseXml = responseXml.replace("&gt;", ">");
        return responseXml;
    }

	public static void main(String[] args) {
		String s = HttpClientCallSoapUtil.sendXml("http://10.2.0.88:8001/ui/services/Online?wsdl",
				"<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
						"<TranData><Head><TranDate>20230808</TranDate>" +
                        "<FuncFlag>7</FuncFlag><TranNo>20230808kui4zu53</TranNo>" +
                        "<TranCom>86520101</TranCom><TranTime>003009</TranTime>" +
                        "<TranOperator>xrikhlw</TranOperator></Head>" +
                        "<Body>" +
                        "<ContNo></ContNo></Body></TranData>");

		System.out.println(s);
	}






}