package com.wxf.utils.test;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

/*
 *
 * @author weixf
 * @date 2022-06-22
 */
public class Md5Util {

    public static String digestByMd5(String data) {
        return DigestUtils.md5Hex(data.getBytes(StandardCharsets.UTF_8));
    }

    public static String digestByMd5(String data, String charset) throws UnsupportedEncodingException {
        if (charset == null || "".equals(charset)) {
            charset = "UTF-8";
        }
        return DigestUtils.md5Hex(data.getBytes(charset));
    }
}
