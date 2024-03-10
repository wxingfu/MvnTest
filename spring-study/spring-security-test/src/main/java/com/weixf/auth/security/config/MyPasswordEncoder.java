package com.weixf.auth.security.config;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

@Component
public class MyPasswordEncoder implements PasswordEncoder {

    private String getEncoded(String charSequence) {
        String encoded = null;
        String algorithm = "MD5";
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            byte[] input = charSequence.getBytes();
            byte[] output = messageDigest.digest(input);
            encoded = new BigInteger(1, output).toString(16).toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return encoded;
    }

    @Override
    public String encode(CharSequence charSequence) {
        return getEncoded((String) charSequence);
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        String formPwd = getEncoded((String) charSequence);
        String dbPwd = s.toUpperCase();
        return Objects.equals(formPwd, dbPwd);
    }
}
