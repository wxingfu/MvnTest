package com.weixf.utils.secret;

public class IpLongUtils {

    /**
     * 把字符串IP转换成long
     *
     * @param ipStr 字符串IP
     * @return IP对应的long值
     */
    public static long ip2Long(String ipStr) {
        String[] ip = ipStr.split("\\.");
        return (Long.parseLong(ip[0]) << 24)
                + (Long.parseLong(ip[1]) << 16)
                + (Long.parseLong(ip[2]) << 8)
                + Long.parseLong(ip[3]);
    }

    /**
     * 把IP的long值转换成字符串
     *
     * @param ipLong IP的long值
     * @return long值对应的字符串
     */
    public static String long2Ip(long ipLong) {
        return (ipLong >>> 24) + "." +
                ((ipLong >>> 16) & 0xFF) + "." +
                ((ipLong >>> 8) & 0xFF) + "." +
                (ipLong & 0xFF);
    }

}