package com.wxf.test;

import com.alibaba.fastjson.JSONObject;
import com.wxf.func.CommonUtil;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.CharsetUtils;
import org.apache.http.util.EntityUtils;
import sun.misc.BASE64Decoder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wxf
 */
public class Test7 {

    private static String getToKenUrl, getObject;
    private static String channel, pwd;
    private final static String result_Suc_Code = "0000";


    static {
        try {
            channel = "wc"; //用户编码
            pwd = "$2a$10$uMAhF9YXakfMc6OC62SOBOMn1fUaBvDo5qPSGcoGQ1yT.StzCqEey";//用户密码
            getToKenUrl = "http://172.16.0.2:8771/file/api/getToken";//获取token
            getObject = "http://172.16.0.2:8771/file/api/common/getObject";//文件下载接口
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        // boolean b = getObject("109011090117378648431668737901668.jpg", "D:\\WorkSpaces\\ideaProjects\\maven-test\\code-test-module\\src\\main\\resources\\2222.jpg");
        boolean b = getObject("APPQIANDUO2B4D0CE0CA7840E396BB40F03CF4E8A6.jpeg",
                "D:\\WorkSpaces\\ideaProjects\\maven-test\\code-test-module\\src\\main\\resources\\APPQIANDUO6ACF98A21970470FA65F569037556B86.jpeg");
    }

    public static boolean getObject(String ossFileName, String filePathName) {
        boolean resultCode = false;
        FileOutputStream out = null;
        try {
            String token = getToken();//获取token
            if (CommonUtil.isEmpty(token)) {
                throw new Exception("文件上传失败，获取token失败");
            }
            File temp = new File(filePathName);
            if (!temp.getParentFile().exists()) {
                temp.getParentFile().mkdirs();
            }
            if (temp.exists()) {
                System.out.println((String.format("文件[%s]已存在，将被覆盖", filePathName)));
            }
            Map<String, Object> parameters = new HashMap<String, Object>();
            parameters.put("token", token);
            parameters.put("objectName", ossFileName);
            parameters.put("channel", channel);
            String resultgetObject = postFormWithHttp(parameters, getObject);
            JSONObject resultGetObjectJson = JSONObject.parseObject(resultgetObject);
            System.out.println("请求:" + getObject + "，返回：" + resultGetObjectJson);
            if (!result_Suc_Code.equals(resultGetObjectJson.getString("code"))) {
                throw new Exception(resultGetObjectJson.getString("msg"));
            }
            JSONObject getObjectData = resultGetObjectJson.getJSONObject("data");
            String ossobject = getObjectData.getString("ossobject");

            byte[] buffer = new BASE64Decoder().decodeBuffer(ossobject);
            out = new FileOutputStream(filePathName);
            out.write(buffer);
            resultCode = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultCode;
    }

    public static String getToken() throws Exception {
        String token = null;
        Map<String, Object> p = new HashMap<String, Object>();
        p.put("channel", channel);
        p.put("password", pwd);
        String tokenResult = postFormWithHttp(p, getToKenUrl);
        JSONObject json = JSONObject.parseObject(tokenResult);
        System.out.println("请求:" + getToKenUrl + "，返回：" + json);
        if (result_Suc_Code.equals(json.getString("code"))) {
            JSONObject response = json.getJSONObject("data");
            token = response.getString("token");//token
        }
        return token;
    }

    public static String postFormWithHttp(Map<String, Object> map, String url) {
        System.out.println("start OSSHttpUtil>>>url:[" + url + "]>>>values[" + map.values() + "]");
        CloseableHttpClient client = null;
        HttpEntity httpEntity = null;
        HttpPost httpPost = null;
        try {
            client = HttpClients.createDefault();
            httpPost = new HttpPost(url);
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(5000).setConnectionRequestTimeout(5000).setSocketTimeout(60 * 1000).build();
            httpPost.setConfig(requestConfig);
            ContentType contentType = ContentType.create("text/plain", Consts.UTF_8);
            MultipartEntityBuilder builder = MultipartEntityBuilder.create().setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                if ("File".equals(getType(entry.getValue()))) {
                    builder.addBinaryBody(entry.getKey(), (File) entry.getValue());
                } else {
                    builder.addPart(entry.getKey(), new StringBody(entry.getValue().toString(), contentType));
                }
            }
            httpEntity = builder.setCharset(CharsetUtils.get("UTF-8")).build();
            httpPost.setEntity(httpEntity);
        } catch (Exception e) {
            System.out.println("postFormWithHttp>Exception=>" + e);
        }
        return execute(client, httpPost);
    }

    private static String execute(CloseableHttpClient client, HttpRequestBase httpPost) {
        if (client == null || httpPost == null) {
            return "";
        }
        CloseableHttpResponse response = null;
        try {
            response = client.execute(httpPost);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity resEntity = response.getEntity();
                return EntityUtils.toString(resEntity);
            }
        } catch (Exception e) {
            System.out.println("execute>Exception=>" + e);
        } finally {
            if (null != response) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "";
    }

    private static String getType(Object object) {
        String typeName = object.getClass().getName();
        int length = typeName.lastIndexOf(".");
        String type = typeName.substring(length + 1);
        return type;
    }
}
