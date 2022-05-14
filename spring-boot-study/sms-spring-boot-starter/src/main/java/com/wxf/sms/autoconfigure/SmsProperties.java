package com.wxf.sms.autoconfigure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Data
@Component
@ConfigurationProperties(prefix = "sms")
public class SmsProperties {

    private SmsMessage aliyun = new SmsMessage();

    private SmsMessage tencent = new SmsMessage();

    @Data
    public static class SmsMessage {

        /**
         * 用户名
         */
        private String userName;

        /**
         * 密码
         */
        private String passWord;

        /**
         * 秘钥
         */
        private String sign;

        /**
         *
         */
        private String url;

        @Override
        public String toString() {
            return "SmsMessage{" +
                    "userName='" + userName + '\'' +
                    ", passWord='" + passWord + '\'' +
                    ", sign='" + sign + '\'' +
                    ", url='" + url + '\'' +
                    '}';
        }
    }
}

