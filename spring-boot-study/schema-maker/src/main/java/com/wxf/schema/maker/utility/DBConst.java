package com.wxf.schema.maker.utility;

import org.springframework.stereotype.Component;

/**
 * @author weixf
 * @since 2022-01-21
 */
@Component
public class DBConst {

    // Prevent instantiation
    private DBConst() {
    }

    //在这里只列出支持的数据库
    public final static int DB_UnSupported = -1;
    public final static int DB_Oracle = 1;
    public final static int DB_DB2 = 2;
    public final static int DB_SQLServer = 3;

    public static String getDBName(int db) {
        switch (db) {
            case DB_Oracle:
                return "Oracle";
            case DB_DB2:
                return "DB2";
            case DB_SQLServer:
                return "SQLServer";
            default:
                return "UnSupported";
        }
    }

    // 这里只列出支持的连接池
    public final static int ConnPool_UnSupported = -1;
    public final static int ConnPool_Self = 1;
    public final static int ConnPool_Weblogic = 2;
    public final static int ConnPool_Websphere = 3;
    public final static int ConnPool_ApacheDBCP = 4;

    public final static int Vendor_UnSupported = -1;
    public final static int Vendor_Oracle = 1;
    public final static int Vendor_IBM = 2;
    public final static int Vendor_Microsoft = 3;
    public final static int Vendor_Bea = 4;
    public final static int Vendor_SF_jDTS = 5;
    public final static int Vendor_inet = 6;
}
