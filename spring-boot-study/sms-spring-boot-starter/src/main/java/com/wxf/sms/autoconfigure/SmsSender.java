package com.wxf.sms.autoconfigure;

public interface SmsSender {

    boolean send(String message);
}
