package com.weixf.jdbc.senior.util;

public class StrUtils {

    public static String underlineToLowerCamel(String word) {
        if (word == null) {
            return null;
        }
        String[] split = word.split("_");
        StringBuilder sb = new StringBuilder(word.length());
        for (String s : split) {
            char[] chars = s.toCharArray();
            if (chars[0] < 'A' || chars[0] > 'Z') {
                if (chars[0] >= 'a' && chars[0] <= 'z') {
                    chars[0] -= 32;
                }
            }
            sb.append(chars);
        }
        return sb.toString();
    }

    public static String underlineToUpperCamel(String word) {
        if (word == null) {
            return null;
        }
        String[] split = word.split("_");
        StringBuilder sb = new StringBuilder(word.length());
        sb.append(split[0]);
        for (int i = 1; i < split.length; i++) {
            char[] chars = split[i].toCharArray();
            if (chars[0] < 'A' || chars[0] > 'Z') {
                if (chars[0] >= 'a' && chars[0] <= 'z') {
                    chars[0] -= 32;
                }
            }
            sb.append(chars);
        }
        return sb.toString();
    }


    public static String camelToUnderline(String word) {
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < word.length(); ++i) {
            char ch = word.charAt(i);
            if (ch >= 'A' && ch <= 'Z') {
                char ch_ucase = (char) (ch + 32);
                if (i > 0) {
                    buf.append('_');
                }
                buf.append(ch_ucase);
            } else {
                buf.append(ch);
            }
        }
        return buf.toString();
    }
}
