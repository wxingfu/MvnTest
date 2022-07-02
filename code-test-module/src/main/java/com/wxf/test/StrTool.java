package com.wxf.test;

public class StrTool {

    public static String cTrim(String tStr) {
        String ttStr = "";
        if (tStr == null) {
            ttStr = "";
        } else {
            ttStr = tStr.trim();
        }
        return ttStr;
    }

}

