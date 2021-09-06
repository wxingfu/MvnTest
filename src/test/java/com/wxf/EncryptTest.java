package com.wxf;

import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import org.junit.Test;

public class EncryptTest {

    @Test
    public void test() {
        RSA rsa = new RSA("MIIBVAIBADANBgkqhkiG9w0BAQEFAASCAT4wggE6AgEAAkEA3hQ6uQi4ukvSjtYi2AhSQ/9EUGy6sw9Dt9No4rBP5L21aXLTEyYnNKX0p2HwylZzLP7mdRJvtKAZbGITeogvawIDAQABAkBnojsRE//Yd/+nRkh2VdPGBX5kpYiufKYWR6K/fpWZ4QrASv5sIuD2Cqfp5e8K6fZ4DW/CSUMKGq6Vq6xZVeLJAiEA/BazblQTEeGFsQydEmaBA1CWupPOAFO2xg7c/5s1sI8CIQDhhlRtXfjqcUWhj4Um1t8pFBkFHiN8RC1hufaZs9OJZQIgEuLogoWOADLzPzaAthYz6DmrcUMNlfyvntsSN5w7Q4UCIQCu7raAWvsgRxqe1iePV+6j+33o1VbrJisZedkJok48bQIgWVX940QICkAUhYRJgX9uj7oWOAyE1V8ambte6SHBHhs=",
                "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAN4UOrkIuLpL0o7WItgIUkP/RFBsurMPQ7fTaOKwT+S9tWly0xMmJzSl9Kdh8MpWcyz+5nUSb7SgGWxiE3qIL2sCAwEAAQ==");
        String oldPass = new String(
                rsa.decrypt("bvSpZS+eT5DQs4YhGRG1uzAS/Zbr5MTvIkS0fCDUg8fghbL9H5HJlv38CJeYOklLkYfM7Z6NA18dc7EG6pUefA==", KeyType.PrivateKey));
        System.out.println(oldPass);
    }
}
