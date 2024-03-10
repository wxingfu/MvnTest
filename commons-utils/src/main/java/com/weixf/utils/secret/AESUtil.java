package com.weixf.utils.secret;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import sun.security.provider.Sun;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.security.Security;


public class AESUtil {


    private static final String DEFAULT_CHARSET = "UTF-8";
    private static final String KEY_AES = "AES";

    /**
     * 加密
     *
     * @param data 需要加密的内容
     * @param key  加密密码
     * @return
     */
    public static String encryptForOpen(String data, String key) {

        return doAESForOpen(data, key, Cipher.ENCRYPT_MODE);
    }

    /**
     * 解密
     *
     * @param data 待解密内容
     * @param key  解密密钥
     * @return
     */
    public static String decryptForOpen(String data, String key) {

        return doAESForOpen(data, key, Cipher.DECRYPT_MODE);
    }

    /**
     * 加解密
     *
     * @param data
     * @param key
     * @param mode
     * @return
     */

    private static String doAESForOpen(String data, String key, int mode) {

        try {
            if (StringUtils.isBlank(data) || StringUtils.isBlank(key)) {
                return null;
            }

            boolean encrypt = mode == Cipher.ENCRYPT_MODE;
            byte[] content;
            if (encrypt) {
                content = data.getBytes(DEFAULT_CHARSET);

            } else {
                content = Base64.decodeBase64(data.getBytes(DEFAULT_CHARSET));

            }
            SecretKey secretKey = getSecretKey(key);
            // 创建密钥
            SecretKeySpec keySpec = new SecretKeySpec(secretKey.getEncoded(), KEY_AES);
            // 创建密码器
            Cipher cipher = Cipher.getInstance(KEY_AES);
            // 初始化
            cipher.init(mode, keySpec);

            byte[] result = cipher.doFinal(content);

            if (encrypt) {
                return new String(Base64.encodeBase64(result));

            } else {
                return new String(result, DEFAULT_CHARSET);

            }
        } catch (Exception e) {
            System.err.println("AES密文处理异常" + e);
            e.printStackTrace();

        }
        return null;
    }

    public static SecretKey getSecretKey(String key) {
        try {
            KeyGenerator gen = KeyGenerator.getInstance(KEY_AES);
            if (null == Security.getProvider("SUN")) {
                Security.addProvider(new Sun());
            }
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG", "SUN");
            secureRandom.setSeed(key.getBytes());
            gen.init(128, secureRandom);
            return gen.generateKey();
        } catch (Exception e) {
            throw new RuntimeException("初始化秘钥出现异常 ");
        }
    }
}
