package com.weixf.sms.autoconfigure;


public class AliyunSmsSenderImpl implements SmsSender {

    private final SmsProperties.SmsMessage smsMessage;

    public AliyunSmsSenderImpl(SmsProperties.SmsMessage smsProperties) {
        this.smsMessage = smsProperties;
    }

    @Override
    public boolean send(String message) {
        System.out.println(smsMessage.toString() + "开始发送短信==》短信内容：" + message);
        return true;
    }
}

