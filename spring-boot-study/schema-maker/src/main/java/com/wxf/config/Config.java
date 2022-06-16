package com.wxf.config;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

/*
 *
 * @author weixf
 * @date 2022-06-16
 */
public class Config {

    // 实体类输出路径
    public static String schemaOutputPATH = "C:/output/dbschema/src/";
    // 单表操作输出路径
    public static String oneTableOprOutputPATH = "C:/output/SimpleOpr/";
    // 输出实体类包名称
    public static String packageName = "com.sinosoft.lis";
    // 输出实体类存放路径
    public static String outputPackagePATH = "com/sinosoft/lis/";
    // 数据库用户名
    public static String DBUserName = "lis";
    // 标示生成的文件类型 DB -只生成表实体 OPR -只生成单表操作文件 ALL -生成表实体和单表操作文件
    public static String OPFlag = "DB";
    // PDM文件名(含全路径)
    public static String PDMFileName = "E:/makePDM";
    // 表名，如果指定了表名，则指生成指定表的相关文件
    public static String tableName;

    public static String getTimestamp() {
        String pattern = "yyyy-MM-dd HH:mm:ss SSS";
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        Date today = new Date();
        return df.format(today);
    }

    public static void setSchemaOutputPATH(String tschemaOutputPATH) {
        schemaOutputPATH = tschemaOutputPATH;
    }

    public static void setOneTableOprOutputPATH(String toneTableOprOutputPATH) {
        oneTableOprOutputPATH = toneTableOprOutputPATH;
    }

    public static void setPackageName(String tpackageName) {
        packageName = tpackageName;
        if (packageName != null && Pattern.matches("[a-zA-Z\\.]+", packageName))
            outputPackagePATH = packageName.replace(".", "/") + "/";
    }

    public void setOutputPackagePATH(String toutputPackagePATH) {
        outputPackagePATH = toutputPackagePATH;
    }

    public static void setDBUserName(String dBUserName) {
        DBUserName = dBUserName;
    }

    public static void setOPFlag(String oPFlag) {
        OPFlag = oPFlag;
    }

    public static void setPDMFileName(String pDMFileName) {
        PDMFileName = pDMFileName;
    }

    public static void setTableName(String tableName) {
        Config.tableName = tableName;
    }
}
