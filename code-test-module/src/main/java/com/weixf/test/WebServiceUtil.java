package com.weixf.test;

import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;
import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.log4j.Logger;

import javax.xml.namespace.QName;

/*
 * Webservice请求接口客户端
 * 可能不通用，有能者可改之
 */
public class WebServiceUtil {

    private final static Logger cLogger = Logger.getLogger(WebServiceUtil.class);


    public WebServiceUtil() {

    }


    public static void main(String[] args) throws Exception {
        String result = WebServiceUtil.call("http://localhost:8001/ui/services/Online?wsdl",
                "http://services.webservice.sinosoft.com",
                "submitData", "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                        "<TranData><Head><TranDate>20230808</TranDate><FuncFlag>7</FuncFlag><TranNo>20230808kui4zu53</TranNo><TranCom>86520101</TranCom><TranTime>003009</TranTime><TranOperator>xrikhlw</TranOperator></Head><Body><ContNo></ContNo></Body></TranData>");
        System.out.println(result);
    }


    public static String call(String soapUrl, String soapNameSpace, String soapMethod, String reqXml) throws Exception {
        long mStartMillis = System.currentTimeMillis();
        String mOutStr = "";
        try {
            cLogger.info("请求报文: " + reqXml);
            cLogger.info("请求地址: " + soapUrl + "#" + soapMethod);

            RPCServiceClient serviceClient = new RPCServiceClient();
            Options options = serviceClient.getOptions();
            // 设置超时时间
            options.setTimeOutInMilliSeconds(60 * 1000);
            // fix Timeout waiting for connection
            options.setManageSession(true);
            options.setProperty(HTTPConstants.REUSE_HTTP_CLIENT, true);
            options.setProperty(HTTPConstants.SO_TIMEOUT, 60 * 1000);
            options.setProperty(HTTPConstants.CONNECTION_TIMEOUT, 60 * 1000);
            // 指定调用WebService的URL
            EndpointReference targetEPR = new EndpointReference(soapUrl);
            options.setTo(targetEPR);
            // 指定方法的参数值
            Object[] opAddEntryArgs = new Object[]{reqXml};
            // 指定方法返回值的数据类型的Class对象
            Class<?>[] classes = new Class[]{String.class};
            // 指定要调用的方法及WSDL文件的命名空间
            QName opAddEntry = new QName(soapNameSpace, soapMethod);
            // 调用方法并输出该方法的返回值
            mOutStr = (String) serviceClient.invokeBlocking(opAddEntry, opAddEntryArgs, classes)[0];
            // fix Timeout waiting for connection
            serviceClient.cleanupTransport();
        } catch (Exception e) {
            cLogger.error("请求接口异常: " + e);
            throw new Exception("请求接口异常: " + e.getMessage());
        }

        cLogger.info("返回报文: " + mOutStr);

        long mEndMillis = System.currentTimeMillis();
        cLogger.info("请求耗时: " + (mEndMillis - mStartMillis) / 1000 + "ms");

        return mOutStr;
    }

}
