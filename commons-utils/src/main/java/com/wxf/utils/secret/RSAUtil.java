package com.wxf.utils.secret;

import javax.crypto.Cipher;
import javax.xml.bind.DatatypeConverter;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class RSAUtil {

    public static final String RSA = "RSA";

    public static final String PUBLIC_KEY = "publicKey";

    public static final String PRIVATE_KEY = "privateKey";

    private static final int MAX_ENCRYPT_BLOCK = 117;

    private static final int MAX_DECRYPT_BLOCK = 128;

    /**
     * 生成RAS公钥与私钥字符串，直接返回
     */
    public static Map<String, String> getKeys() throws NoSuchAlgorithmException {
        Map<String, String> map = new HashMap<>();
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(RSA);
        // 初始化密钥对生成器，密钥大小为96-1024位
        keyPairGen.initialize(1024, new SecureRandom());
        // 生成一个密钥对，保存在keyPair中
        KeyPair keyPair = keyPairGen.generateKeyPair();
        // 得到公钥字符串
        String publicKey = base64ToStr(keyPair.getPublic().getEncoded());
        // 得到私钥字符串
        String privateKey = base64ToStr(keyPair.getPrivate().getEncoded());
        map.put(PUBLIC_KEY, publicKey);
        map.put(PRIVATE_KEY, privateKey);
        return map;
    }

    public static String base64ToStr(byte[] b) {
        return DatatypeConverter.printBase64Binary(b);
    }

    public static byte[] strToBase64(String str) {
        return DatatypeConverter.parseBase64Binary(str);
    }


    public static String decrypt(String publicKeyText, String cipherText) throws Exception {
        byte[] publicKeyBytes = Base64.getDecoder().decode(publicKeyText);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(publicKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA, "SunRsaSign");
        PublicKey publicKey = keyFactory.generatePublic(x509KeySpec);
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        try {
            cipher.init(Cipher.DECRYPT_MODE, publicKey);
        } catch (InvalidKeyException e) {
            // 因为 IBM JDK 不支持私钥加密, 公钥解密, 所以要反转公私钥
            // 也就是说对于解密, 可以通过公钥的参数伪造一个私钥对象欺骗 IBM JDK
            RSAPublicKey rsaPublicKey = (RSAPublicKey) publicKey;
            RSAPrivateKeySpec spec = new RSAPrivateKeySpec(rsaPublicKey.getModulus(), rsaPublicKey.getPublicExponent());
            Key fakePrivateKey = KeyFactory.getInstance(RSA).generatePrivate(spec);
            cipher = Cipher.getInstance(RSA); //It is a stateful object. so we need to get new one.
            cipher.init(Cipher.DECRYPT_MODE, fakePrivateKey);
        }
        if (cipherText == null || cipherText.length() == 0) {
            return cipherText;
        }
        // 开始分段解密
        byte[] encryptedData = Base64.getDecoder().decode(cipherText);
        int inputLen = encryptedData.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段解密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_DECRYPT_BLOCK;
        }
        out.close();
        // 结束分段加密
        // return new String(plainBytes);
        return out.toString();
    }

    public static String encrypt(String privatekey, String plainText) throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(privatekey);
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory factory = KeyFactory.getInstance(RSA, "SunRsaSign");
        PrivateKey privateKey = factory.generatePrivate(spec);
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        try {
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        } catch (InvalidKeyException e) {
            //For IBM JDK, 原因请看解密方法中的说明
            RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) privateKey;
            RSAPublicKeySpec publicKeySpec = new RSAPublicKeySpec(rsaPrivateKey.getModulus(),
                    rsaPrivateKey.getPrivateExponent());
            Key fakePublicKey = KeyFactory.getInstance(RSA).generatePublic(publicKeySpec);
            cipher = Cipher.getInstance(RSA);
            cipher.init(Cipher.ENCRYPT_MODE, fakePublicKey);
        }
        // 开始分段处理
        byte[] data = plainText.getBytes(StandardCharsets.UTF_8);
        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        // 结束分段处理
        return Base64.getEncoder().encodeToString(encryptedData);
    }
}
