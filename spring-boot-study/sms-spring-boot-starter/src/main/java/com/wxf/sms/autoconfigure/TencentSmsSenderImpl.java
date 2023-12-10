package com.wxf.sms.autoconfigure;


import com.wxf.sms.autoconfigure.SmsProperties.SmsMessage;


public class TencentSmsSenderImpl implements SmsSender {

    private final SmsMessage smsMessage;

    public TencentSmsSenderImpl(SmsMessage smsMessage) {
        this.smsMessage = smsMessage;
    }

    @Override
    public boolean send(String message) {
        System.out.println(smsMessage.toString() + "开始发送短信==》短信内容：" + message);
        return true;
    }
}
