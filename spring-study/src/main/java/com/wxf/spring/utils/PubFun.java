package com.wxf.spring.utils;

/**
 * @author weixf
 * @since 2022-03-07
 */
public class PubFun {

    /**
     * 将字符串补数,将sourString的用cChar补足cLen长度的字符串,如果字符串超长，则不做处理
     * <p><b>Example: </b> LCh("Minim", "0", 10) returns "00000Minim" <p>
     *
     * @param sourString 源字符串
     * @param cChar      补数用的字符
     * @param cLen       字符串的目标长度
     * @return 字符串
     */
    public static String LCh(String sourString, String cChar, int cLen) {
        int tLen = sourString.length();
        int i, iMax;
        StringBuilder tReturn = new StringBuilder();
        if (tLen >= cLen) {
            return sourString;
        }
        iMax = cLen - tLen;
        for (i = 0; i < iMax; i++) {
            tReturn.append(cChar);
        }
        tReturn = new StringBuilder(tReturn.toString().trim() + sourString.trim());
        return tReturn.toString();
    }
}
