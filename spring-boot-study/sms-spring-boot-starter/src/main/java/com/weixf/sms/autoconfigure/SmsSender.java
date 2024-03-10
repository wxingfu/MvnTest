package com.weixf.sms.autoconfigure;

public interface SmsSender {

    boolean send(String message);
}
