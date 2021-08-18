package com.wxf.test;

import java.io.UnsupportedEncodingException;

public class Test13 {

    // public static void main(String[] args) {
    //     entityTest();
    // }

    public static void entityTest() {
        try {
            byte[] str = "{code=300, time=2021-08-03 11-07-02, message=Error!CreateId/??o????oo????-???μ???è????o??o!é???o|?????|è§?è??!}".getBytes("gbk");
            String gbkStr = new String(str, "gbk");
            System.out.println(new String(getUTF8BytesFromGBKString(gbkStr), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public static byte[] getUTF8BytesFromGBKString(String gbkStr) {
        int n = gbkStr.length();
        byte[] utfBytes = new byte[3 * n];
        int k = 0;
        for (int i = 0; i < n; i++) {
            int m = gbkStr.charAt(i);
            if (m < 128 && m >= 0) {
                utfBytes[k++] = (byte) m;
                continue;
            }
            utfBytes[k++] = (byte) (0xe0 | (m >> 12));
            utfBytes[k++] = (byte) (0x80 | ((m >> 6) & 0x3f));
            utfBytes[k++] = (byte) (0x80 | (m & 0x3f));
        }
        if (k < utfBytes.length) {
            byte[] tmp = new byte[k];
            System.arraycopy(utfBytes, 0, tmp, 0, k);
            return tmp;
        }
        return utfBytes;
    }


    public byte[] gbk2utf8(String chenese) {

        // Step 1: 得到GBK编码下的字符数组，一个中文字符对应这里的一个c[i]
        char c[] = chenese.toCharArray();

        // Step 2: UTF-8使用3个字节存放一个中文字符，所以长度必须为字符的3倍
        byte[] fullByte = new byte[3 * c.length];

        // Step 3: 循环将字符的GBK编码转换成UTF-8编码
        for (int i = 0; i < c.length; i++) {

            // Step 3-1：将字符的ASCII编码转换成2进制值
            int m = (int) c[i];
            String word = Integer.toBinaryString(m);
            System.out.println(word);

            // Step 3-2：将2进制值补足16位(2个字节的长度)
            StringBuffer sb = new StringBuffer();
            int len = 16 - word.length();
            for (int j = 0; j < len; j++) {
                sb.append("0");
            }
            // Step 3-3：得到该字符最终的2进制GBK编码
            // 形似：1000 0010 0111 1010
            sb.append(word);

            // Step 3-4：最关键的步骤，根据UTF-8的汉字编码规则，首字节
            // 以1110开头，次字节以10开头，第3字节以10开头。在原始的2进制
            // 字符串中插入标志位。最终的长度从16--->16+3+2+2=24。
            sb.insert(0, "1110");
            sb.insert(8, "10");
            sb.insert(16, "10");
            System.out.println(sb.toString());

            // Step 3-5：将新的字符串进行分段截取，截为3个字节
            String s1 = sb.substring(0, 8);
            String s2 = sb.substring(8, 16);
            String s3 = sb.substring(16);

            // Step 3-6：最后的步骤，把代表3个字节的字符串按2进制的方式
            // 进行转换，变成2进制的整数，再转换成16进制值
            byte b0 = Integer.valueOf(s1, 2).byteValue();
            byte b1 = Integer.valueOf(s2, 2).byteValue();
            byte b2 = Integer.valueOf(s3, 2).byteValue();

            // Step 3-7：把转换后的3个字节按顺序存放到字节数组的对应位置
            byte[] bf = new byte[3];
            bf[0] = b0;
            bf[1] = b1;
            bf[2] = b2;

            fullByte[i * 3] = bf[0];
            fullByte[i * 3 + 1] = bf[1];
            fullByte[i * 3 + 2] = bf[2];

            // Step 3-8：返回继续解析下一个中文字符
        }
        return fullByte;
    }

    public static String getEncoding(String str) {
        String encode;

        encode = "UTF-16";
        try {
            if (str.equals(new String(str.getBytes(), encode))) {
                return encode;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        encode = "ASCII";
        try {
            if (str.equals(new String(str.getBytes(), encode))) {
                return "字符串<< " + str + " >>中仅由数字和英文字母组成，无法识别其编码格式";
            }
        } catch (Exception ex) {
        }

        encode = "ISO-8859-1";
        try {
            if (str.equals(new String(str.getBytes(), encode))) {
                return encode;
            }
        } catch (Exception ex) {
        }

        encode = "GB2312";
        try {
            if (str.equals(new String(str.getBytes(), encode))) {
                return encode;
            }
        } catch (Exception ex) {
        }

        encode = "UTF-8";
        try {
            if (str.equals(new String(str.getBytes(), encode))) {
                return encode;
            }
        } catch (Exception ex) {
        }

        encode = "GBK";
        try {
            if (str.equals(new String(str.getBytes(), encode))) {
                return encode;
            }
        } catch (Exception ex) {
        }
        /*
         *......待完善
         */

        return "未识别编码格式";
    }

    public static void main(String[] args) {
        // //获取系统默认编码
        // System.out.println("系统默认编码：" + System.getProperty("file.encoding")); //查询结果GBK
        // //系统默认字符编码
        // System.out.println("系统默认字符编码：" + Charset.defaultCharset()); //查询结果GBK
        // //操作系统用户使用的语言
        // System.out.println("系统默认语言：" + System.getProperty("user.language")); //查询结果zh
        //
        // System.out.println();
        //
        // String s1 = "hi, nice to meet you!";
        // String s2 = "hi, 我来了！";
        //
        // System.out.println(getEncoding(s1));
        // System.out.println(getEncoding(s2));

        entityTest();
    }
}
