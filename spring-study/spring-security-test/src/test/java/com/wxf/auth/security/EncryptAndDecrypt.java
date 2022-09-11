package com.wxf.auth.security;


import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncryptAndDecrypt {

    @Test
    public void test() {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String src = "123456";
        String encoded = encoder.encode(src);
        System.out.println(encoded);

        // $2a$10$e9zkNa9v1NJbHHmQ2DLTgeOn8jYpVOLwuVZyXnQWBvpTDSxSkc1tK
        // $2a$10$.u/u8fs8rMwjXNOnTqa8puKVAdhzJTRvmvOolFzKSCNx2xsxRPMOu
    }
}
