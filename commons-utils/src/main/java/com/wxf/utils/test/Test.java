package com.wxf.utils.test;


import java.util.Base64;

/*
 *
 * @author weixf
 * @date 2022-06-28
 */
public class Test {

    public static void main(String[] args) throws Exception {
        String key = CryptoUtils.generateSymmetricKey(CryptoUtils.Algorithm.AES_CBC_PKCS5);
        System.out.println("生成的key为：" + key);
        String cipherText = CryptoUtils.encryptSymmetrically(key, key, "Hello", CryptoUtils.Algorithm.AES_CBC_PKCS5);
        System.out.println("加密后的密文为：" + cipherText);
        //
        // String plainText = CryptoUtils.decryptSymmetrically(key, key, cipherText, CryptoUtils.Algorithm.AES_CBC_PKCS5);
        // System.out.println("解密后的明文为：" + plainText);

        // String s = Base64.getEncoder().encodeToString("hello".getBytes());
        // System.out.println(s);

        byte[] decode = Base64.getDecoder().decode("sQPoC/1do9BZMkg8I5c09A==");
        System.out.println(new String(decode));
    }
}
