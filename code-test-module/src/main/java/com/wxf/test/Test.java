package com.wxf.test;


import com.alibaba.fastjson.JSONObject;
import com.wxf.func.CommonUtil;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.CharsetUtils;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        // Date date = new Date(1656662395554L);
        // String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        // System.out.println(format);

        String ossFileName = "001_EdorAEEle_41658477122932.pdf";
        String filePathName = "C:\\Users\\weixf\\Desktop\\EdorAEEle_41658477122932.pdf";
        boolean object = getObject(ossFileName, filePathName);
    }

    public static boolean getObject(String ossFileName, String filePathName) {
        String channel = "wc";
        // String pwd = "$2a$10$uMAhF9YXakfMc6OC62SOBOMn1fUaBvDo5qPSGcoGQ1yT.StzCqEey";
        String pwd = "$2a$10$uMAhF9YXakfMc6OC62SOBOMn1fUaBvDo5qPSGcoGQ1yT.StzCqEey";
        // String getToKenUrl = "http://172.16.0.2:8771/file/api/getToken";
        String getToKenUrl = "http://172.16.0.17:8774/file/api/getToken";
        // String getObject = "http://172.16.0.2:8771/file/api/common/getObject";
        String getObject = "http://172.16.0.17:8774/file/api/common/getObject";
        boolean resultCode = false;
        FileOutputStream out = null;
        try {
            String token = getToken(channel, pwd, getToKenUrl);
            if (CommonUtil.isEmpty(token)) {
                throw new Exception("文件上传失败，获取token失败");
            }
            File temp = new File(filePathName);
            if (!temp.getParentFile().exists()) {
                System.out.println("文件夹不存在将被创建");
                boolean mkdirs = temp.getParentFile().mkdirs();
            }
            if (temp.exists()) {
                System.out.printf("文件[%s]已存在，将被覆盖%n", filePathName);
            }
            Map<String, Object> parameters = new HashMap<String, Object>();
            parameters.put("token", token);
            parameters.put("objectName", ossFileName);
            parameters.put("channel", channel);
            String resultgetObject = postHttp(parameters, getObject);
            JSONObject resultGetObjectJson = JSONObject.parseObject(resultgetObject);
            //cLogger.info("请求:"+getObject+"，返回："+ resultGetObjectJson);
            if (!"0000".equals(resultGetObjectJson.getString("code"))) {
                throw new Exception(resultGetObjectJson.getString("msg"));
            }
            JSONObject getObjectData = resultGetObjectJson.getJSONObject("data");
            String ossobject = getObjectData.getString("ossobject");//文件码

            // byte[] buffer = new BASE64Decoder().decodeBuffer(ossobject);
            byte[] buffer = Base64.getDecoder().decode(ossobject);
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

    private static String getToken(String channel, String pwd, String getToKenUrl) {
        String token = null;
        Map<String, Object> p = new HashMap<String, Object>();
        p.put("channel", channel);
        p.put("password", pwd);
        String tokenResult = postHttp(p, getToKenUrl);
        JSONObject json = JSONObject.parseObject(tokenResult);
        System.out.println("请求:" + getToKenUrl + "，返回：" + json);
        if ("0000".equals(json.getString("code"))) {
            JSONObject response = json.getJSONObject("data");
            token = response.getString("token");//token
        }
        return token;
    }

    public static String postHttp(Map<String, Object> map, String url) {
        CloseableHttpClient client = null;
        HttpEntity httpEntity = null;
        HttpPost httpPost = null;
        try {
            client = HttpClients.createDefault();
            // client = HttpClients.createSystem();
            httpPost = new HttpPost(url);
            // 设置超时时间
            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectTimeout(5000).setConnectionRequestTimeout(5000)
                    .setSocketTimeout(60 * 1000).build();
            httpPost.setConfig(requestConfig);

            ContentType contentType = ContentType.create("text/plain",
                    Consts.UTF_8);

            MultipartEntityBuilder builder = MultipartEntityBuilder.create()
                    .setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                if ("File".equals(getType(entry.getValue()))) {
                    builder.addBinaryBody(entry.getKey(),
                            (File) entry.getValue());
                } else {
                    builder.addPart(entry.getKey(), new StringBody(entry
                            .getValue().toString(), contentType));
                }
            }
            httpEntity = builder.setCharset(CharsetUtils.get("UTF-8")).build();
            httpPost.setEntity(httpEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return execute(client, httpPost);
    }

    private static String getType(Object object) {
        String typeName = object.getClass().getName();
        int length = typeName.lastIndexOf(".");
        return typeName.substring(length + 1);
    }

    private static String execute(CloseableHttpClient client, HttpPost httpPost) {
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
            e.printStackTrace();
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
}
