package com.wxf.utility;


import com.wxf.table.Column;
import com.wxf.table.Key;
import com.wxf.table.Schema;
import com.wxf.table.Table;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author weixf
 * @since 2022-01-21
 */
@Slf4j
@Component
public class ComparePDM {

    private Connection con = null;
    private int DBMSType = DBConst.DB_UnSupported;

    public ComparePDM() {
        // JdbcUrl JUrl = new JdbcUrl();
        // DBMSType = JUrl.getDBType();
        DBMSType = DBConst.DB_Oracle;
    }

    @Resource
    private JdbcTemplate jdbcTemplate;

    public boolean compareSchema(Schema schema) {
        DataSource dataSource = jdbcTemplate.getDataSource();
        if (dataSource==null) {
            log.error("获取数据源出错");
            return false;
        }
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // con = DBConnPool.getConnection().getCon();
        con = connection;
        if (con == null) {
            log.error("Connect failed!");
            System.exit(0);
        }
        for (int i = 0; i < schema.getTabNum(); i++) {
            compareTable(schema.getTable(i));
        }
        compareTableFromDB(schema);
        return true;
    }

    private boolean compareTable(Table table) {
        if (tableExist(table)) //测试表在数据库中是否存在
        {
            //比较列是否一致
            compareColumn(table);
            //比较主键信息是否一致
            comparePrimaryKey(table);
            //比较唯一键信息是否一致
            compareUniqueKey(table);
            //比较外键信息是否一致
            compareForeignKey(table);
        }
        return true;
    }


    private boolean compareTableFromDB(Schema schema) {
        if (DBMSType == DBConst.DB_SQLServer) {
            String str = "select table_name from information_schema.tables where table_type = 'BASE TABLE'";
            ExeSQL exesql = new ExeSQL(con);
            SSRS ssrs = exesql.execSQL(str);
            for (int i = 1; i <= ssrs.getMaxRow(); i++) {
                String table_name = ssrs.GetText(i, 1);
                boolean have = false;
                for (int j = 0; j < schema.getTabNum(); j++) {
                    String code = schema.getTable(j).getCode();
                    if (code.equalsIgnoreCase(table_name)) {
                        have = true;
                        break;
                    }
                }
                if (have == false) {
                    log.info("表" + table_name + "在PDM中不存在");
                }
            }
        }
        if (DBMSType == DBConst.DB_Oracle) {
            String str = "select TNAME from tab where tabtype = 'TABLE'";
            ExeSQL exesql = new ExeSQL(con);
            SSRS ssrs = exesql.execSQL(str);
            for (int i = 1; i <= ssrs.getMaxRow(); i++) {
                String table_name = ssrs.GetText(i, 1);
                boolean have = false;
                for (int j = 0; j < schema.getTabNum(); j++) {
                    String code = schema.getTable(j).getCode();
                    if (code.equalsIgnoreCase(table_name)) {
                        have = true;
                        break;
                    }
                }
                if (have == false) {
                    log.info("表" + table_name + "在PDM中不存在");
                }
            }
        }
        return true;
    }

    private boolean tableExist(Table table) {
        String sql = new String();
        if (DBMSType == DBConst.DB_Oracle) {
            sql = "select count(*) from tab where tname = upper('"
                    + table.getCode() + "') and tabtype = 'TABLE'";
        }
        if (DBMSType == DBConst.DB_SQLServer) {
            sql = "select COUNT(*) from INFORMATION_SCHEMA.TABLES where TABLE_TYPE = 'BASE TABLE' and upper(TABLE_NAME) = upper('"
                    + table.getCode() + "')";
        }
        if (DBMSType == DBConst.DB_DB2) {
            sql = "select count(*) from SYSCAT.TABLES where TYPE = 'T' and upper(TABNAME) = upper('" +
                    table.getCode() + "')";
        }
        int tname = Integer.parseInt(new ExeSQL(con).getOneValue(sql));
        if (tname != 1) {
            log.info("表" + table.getCode() + "在数据库中不存在");
            return false;
        }
        return true;
    }

    private void compareColumn(Table table) {
        try {
            ExeSQL tExeSQL = new ExeSQL(con);
            String queryStr = "";
            if (DBMSType == DBConst.DB_Oracle) {
                queryStr = "select cname,coltype,width,nvl(scale,0),nvl(precision,0),decode(nulls,'NULL',1,0) end from col where tname = upper('"
                        + table.getCode() + "')  order by colno";
            }
            if (DBMSType == DBConst.DB_SQLServer) {
                queryStr = "select COLUMN_NAME,DATA_TYPE ,isnull(CHARACTER_MAXIMUM_LENGTH,0),isnull(NUMERIC_SCALE,0),isnull(NUMERIC_PRECISION,0),case IS_NULLABLE when 'YES' then 1 else 0 end from INFORMATION_SCHEMA.COLUMNS where  upper(TABLE_NAME) = upper('"
                        + table.getCode() + "') order by ORDINAL_POSITION";
            }
            if (DBMSType == DBConst.DB_DB2) {
                queryStr = "select COLNAME,TYPENAME,LENGTH,SCALE,LENGTH,case when NULLS = 'Y' then 1 else 0 end from SYSCAT.COLUMNS where  upper(TABNAME) = upper('" +
                        table.getCode() + "') order by COLNO";
            }
            SSRS tssrs = tExeSQL.execSQL(queryStr);
            if (tssrs.getMaxRow() != table.getColumnNum()) {
                log.info("表" + table.getCode()
                        + "在PDM和数据库中字段个数不一致(数据库字段个数:"
                        + tssrs.getMaxRow()
                        + ",PDM中字段个数:" + table.getColumnNum() + ")");
            }
            if (table.getColumnNum() >= tssrs.getMaxRow()) {
                for (int i = 1; i <= tssrs.getMaxRow(); i++) {
                    if (!tssrs.GetText(i,
                            1).equalsIgnoreCase(table.getColumn(i - 1).
                            getCode())) {
                        log.info("表" + table.getCode() + "的字段"
                                + tssrs.GetText(i, 1)
                                + "在数据库中的'顺序'和PDM中不一致(数据库为第" + i
                                + "个字段),PDM中此位置为:" +
                                table.getColumn(i - 1).
                                        getCode());
                    }
                }
            } else {
                for (int i = 0; i < table.getColumnNum(); i++) {
                    if (!table.getColumn(i).getCode().equalsIgnoreCase(tssrs.
                            GetText(i + 1, 1))) {
                        log.info("表" + table.getCode() + "的字段"
                                + table.getColumn(i).getCode()
                                + "在数据库中的'顺序'和PDM中不一致(PDM为第" + (i + 1)
                                + "个字段),数据库中此位置为:" +
                                tssrs.GetText(i + 1, 1));
                    }
                }
            }
            for (int i = 1; i <= tssrs.getMaxRow(); i++) {
                Column tcolumn = null;
                String cname = tssrs.GetText(i, 1);
                String coltype = tssrs.GetText(i, 2);
                int width = Integer.parseInt(tssrs.GetText(i, 3));
                int scale = Integer.parseInt(tssrs.GetText(i, 4));
                int precision = Integer.parseInt(tssrs.GetText(i, 5));
                int nulls = Integer.parseInt(tssrs.GetText(i, 6));
                for (int j = 0; j < table.getColumnNum(); j++) {
                    if (cname.equalsIgnoreCase(table.getColumn(j).getCode())) {
                        tcolumn = table.getColumn(j);
                        break;
                    }
                }
                if (tcolumn == null) {
                    log.info("表" + table.getCode() + "在数据库中的字段" + cname +
                            "在PDM中没有出现");
                } else {
                    if (nulls != tcolumn.getNullable()) {
                        log.info("表" + table.getCode() + "的字段" + cname +
                                "在数据库中的'是否可空'属性和PDM中不一致");
                    }
                    //类型比较支持
                    if (DBMSType == DBConst.DB_DB2) {
                        if (coltype.equalsIgnoreCase("CHARACTER") ||
                                coltype.equalsIgnoreCase("VARCHAR")) {
                            //DB2中CHAR在数据库中为CHARACTER
                            if ((coltype.equalsIgnoreCase("CHARACTER") &&
                                    (tcolumn.getPDMDataType().toLowerCase().
                                            indexOf("char") < 0 ||
                                            tcolumn.getPDMDataType().toLowerCase().
                                                    indexOf("var") > 0)) ||
                                    (coltype.equalsIgnoreCase("VARCHAR") &&
                                            tcolumn.getPDMDataType().toLowerCase().
                                                    indexOf("varchar") < 0)) {
                                AddError("DataType", table, tcolumn, coltype);
                            }
                            if (!tcolumn.getPDMDataType().equalsIgnoreCase(
                                    "char") &&
                                    !tcolumn.getPDMDataType().equalsIgnoreCase(
                                            "CHARACTER")) {
                                if (width != tcolumn.getLength()) {
                                    AddError("Length", table, tcolumn,
                                            String.valueOf(width));
                                }
                            }
                        } else if (coltype.equalsIgnoreCase("INTEGER")) {
                            if (tcolumn.getPDMDataType().toLowerCase().
                                    indexOf("int") < 0) {
                                AddError("DataType", table, tcolumn, coltype);
                            }
                        } else if (coltype.equalsIgnoreCase("DECIMAL")) {
                            if (tcolumn.getPDMDataType().toLowerCase().
                                    indexOf("num") < 0 &&
                                    tcolumn.getPDMDataType().toLowerCase().
                                            indexOf("dec") < 0) {
                                AddError("DataType", table, tcolumn, coltype);
                            }
                            if (precision != tcolumn.getLength()) {
                                AddError("Length", table, tcolumn,
                                        String.valueOf(precision));
                            }
                            if (scale != tcolumn.getPrecision()) {
                                AddError("Precision", table, tcolumn,
                                        String.valueOf(scale));
                            }
                        } else if (coltype.equalsIgnoreCase("REAL") ||
                                coltype.equalsIgnoreCase("DOUBLE")) {
                            if (tcolumn.getPDMDataType().toLowerCase().
                                    indexOf("float") < 0) {
                                AddError("DataType", table, tcolumn, coltype);
                            }
                        } else if (!coltype.equalsIgnoreCase(tcolumn.
                                getPDMDataType())) {
                            AddError("DataType", table, tcolumn, coltype);
                        }
                    }
                    if (DBMSType == DBConst.DB_Oracle) {
                        if (coltype.equalsIgnoreCase("char") ||
                                coltype.equalsIgnoreCase("VARCHAR") ||
                                coltype.equalsIgnoreCase("VARCHAR2")) {
                            if ((coltype.equalsIgnoreCase("char") &&
                                    (tcolumn.getPDMDataType().toLowerCase().
                                            indexOf("char") < 0 ||
                                            tcolumn.getPDMDataType().toLowerCase().
                                                    indexOf("var") > 0)) ||
                                    (coltype.equalsIgnoreCase("VARCHAR2") &&
                                            tcolumn.getPDMDataType().toLowerCase().
                                                    indexOf("varchar2") < 0) ||
                                    (coltype.equalsIgnoreCase("VARCHAR") &&
                                            tcolumn.getPDMDataType().toLowerCase().
                                                    indexOf("varchar") < 0)) {
                                AddError("DataType", table, tcolumn, coltype);
                            }
                            if (!tcolumn.getPDMDataType().equalsIgnoreCase(
                                    "char")) {
                                if (width != tcolumn.getLength()) {
                                    AddError("Length", table, tcolumn,
                                            String.valueOf(width));
                                }
                            }
                        } else if (coltype.equalsIgnoreCase("NUMBER")) {
                            if (tcolumn.getPDMDataType().toLowerCase().
                                    indexOf("int") < 0 &&
                                    tcolumn.getPDMDataType().toLowerCase().
                                            indexOf("dec") < 0 &&
                                    tcolumn.getPDMDataType().toLowerCase().
                                            indexOf("num") < 0) {
                                AddError("DataType", table, tcolumn, coltype);
                            }
                            if (precision != tcolumn.getLength()) {
                                AddError("Length", table, tcolumn,
                                        String.valueOf(precision));
                            }
                            if (scale != tcolumn.getPrecision()) {
                                AddError("Precision", table, tcolumn,
                                        String.valueOf(scale));
                            }
                        } else if (coltype.equalsIgnoreCase("INTEGER")) {
                            if (tcolumn.getPDMDataType().toLowerCase().
                                    indexOf("int") < 0) {
                                AddError("DataType", table, tcolumn, coltype);
                            }
                        } else {
                            if (!coltype.equalsIgnoreCase(tcolumn.getPDMDataType())) {
                                AddError("DataType", table, tcolumn, coltype);
                            }
                        }
                    }
                    if (DBMSType == DBConst.DB_SQLServer) {
                        if (coltype.equalsIgnoreCase("CHAR") ||
                                coltype.equalsIgnoreCase("VARCHAR")) {
                            if ((coltype.equalsIgnoreCase("CHAR") &&
                                    (tcolumn.getPDMDataType().toLowerCase().
                                            indexOf("char") < 0 ||
                                            tcolumn.getPDMDataType().toLowerCase().
                                                    indexOf("var") > 0)) ||
                                    (coltype.equalsIgnoreCase("VARCHAR") &&
                                            tcolumn.getPDMDataType().toLowerCase().
                                                    indexOf("varchar") < 0)) {
                                AddError("DataType", table, tcolumn, coltype);
                            }
                            if (!tcolumn.getPDMDataType().equalsIgnoreCase(
                                    "char")) {
                                if (width != tcolumn.getLength()) {
                                    AddError("Length", table, tcolumn,
                                            String.valueOf(width));
                                }
                            }
                        } else if (coltype.equalsIgnoreCase("DECIMAL") ||
                                coltype.equalsIgnoreCase("numeric")) {
                            if ((coltype.equalsIgnoreCase("DECIMAL") &&
                                    tcolumn.getPDMDataType().toLowerCase().
                                            indexOf("dec") < 0) ||
                                    (coltype.equalsIgnoreCase("numeric") &&
                                            tcolumn.getPDMDataType().toLowerCase().
                                                    indexOf("numeric") < 0)) {
                                AddError("DataType", table, tcolumn, coltype);
                            }
                            if (precision != tcolumn.getLength()) {
                                AddError("Length", table, tcolumn,
                                        String.valueOf(precision));
                            }
                            if (scale != tcolumn.getPrecision()) {
                                AddError("Precision", table, tcolumn,
                                        String.valueOf(scale));
                            }
                        } else if (coltype.equalsIgnoreCase("INTEGER") ||
                                coltype.equalsIgnoreCase("INT")) {
                            if (tcolumn.getPDMDataType().toLowerCase().
                                    indexOf("int") < 0) {
                                AddError("DataType", table, tcolumn, coltype);
                            }
                        } else if (coltype.equalsIgnoreCase("REAL")) {
                            if (tcolumn.getPDMDataType().toLowerCase().
                                    indexOf("float") < 0) {
                                AddError("DataType", table, tcolumn, coltype);
                            }
                        } else if (!coltype.equalsIgnoreCase(tcolumn.
                                getPDMDataType())) {
                            AddError("DataType", table, tcolumn, coltype);
                        }
                    }
                }
            }
            for (int i = 0; i < table.getColumnNum(); i++) {
                boolean haveCol = false;
                for (int j = 1; j <= tssrs.getMaxRow(); j++) {
                    if (table.getColumn(i).getCode().equalsIgnoreCase(tssrs.
                            GetText(j, 1))) {
                        haveCol = true;
                        break;
                    }
                }
                if (haveCol == false) {
                    log.info("表" + table.getCode() + "在PDM中字段"
                            + table.getColumn(i).getCode()
                            + "在数据库中没有出现");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void AddError(String errorKind, Table table, Column PDMCol,
                          String dbv) {
        if (errorKind.equals("DataType")) {
            log.info("表" + table.getCode() + "的字段" + PDMCol.getCode() +
                    "在数据库中的'数据类型'属性和PDM中不一致(数据库类型为:" + dbv +
                    ",PDM中类型为:" + PDMCol.getPDMDataType() + ")");
        }
        if (errorKind.equals("Precision")) {
            log.info("表" + table.getCode() + "的字段" + PDMCol.getCode() +
                    "在数据库中的'数据精度'属性和PDM中不一致(数据库数据精度为:" + dbv +
                    ",PDM中数据长度为:" + PDMCol.getPrecision() + ")");
        }
        if (errorKind.equals("Length")) {
            log.info("表" + table.getCode() + "的字段" + PDMCol.getCode() +
                    "在数据库中的'数据长度'属性和PDM中不一致(数据库数据长度为:" + dbv +
                    ",PDM中数据长度为:" + PDMCol.getLength() + ")");
        }
    }

    private void comparePrimaryKey(Table table) {
        try {
            ExeSQL tExeSQL = new ExeSQL(con);
            SSRS tssrs = null;
            if (DBMSType == DBConst.DB_Oracle) {
                tssrs = tExeSQL.execSQL(
                        "select constraint_name from user_constraints where table_name = upper('"
                                + table.getCode() + "') and constraint_type = 'P'");

            }
            if (DBMSType == DBConst.DB_SQLServer) {
                tssrs = tExeSQL.execSQL(
                        "select CONSTRAINT_NAME from INFORMATION_SCHEMA.TABLE_CONSTRAINTS where upper(TABLE_NAME) = upper('" +
                                table.getCode() +
                                "') and CONSTRAINT_TYPE = 'PRIMARY KEY'");
            }
            if (DBMSType == DBConst.DB_DB2) {
                tssrs = tExeSQL.execSQL(
                        "select CONSTNAME from SYSCAT.TABCONST where  upper(TABNAME) = upper('" +
                                table.getCode() + "') and TYPE = 'P'");
            }
            if (tssrs.getMaxRow() != 1) {
                log.info("表" + table.getCode() + "在数据库中'主键'不存在");
            }
            Key tKey = table.getPrimaryKey();
            if (tKey == null) {
                log.info("表" + table.getCode() + "在PDM中'主键'不存在");
            }
            if (tssrs.getMaxRow() != 1 || tKey == null) {
                return;
            }
            String constraint_name = tssrs.GetText(1, 1);
            if (!constraint_name.equalsIgnoreCase(tKey.getConstraintName())) {
                log.info("表" + table.getCode()
                        + "在数据库和PDM的主键的Constraint名称不同(数据库为:"
                        + constraint_name + ",PDM为:"
                        + tKey.getConstraintName() + ")");
            }
            ExeSQL texeSQL = new ExeSQL(con);
            SSRS jssrs = null;
            if (DBMSType == DBConst.DB_Oracle) {
                jssrs = texeSQL.execSQL(
                        "select column_name from user_cons_columns where table_name = upper('"
                                + table.getCode() + "') and constraint_name = upper('"
                                + constraint_name + "') order by position");
            }
            if (DBMSType == DBConst.DB_SQLServer) {
                jssrs = texeSQL.execSQL(
                        "select COLUMN_NAME from INFORMATION_SCHEMA.KEY_COLUMN_USAGE where  upper(TABLE_NAME) = upper('"
                                + table.getCode() +
                                "') and upper(CONSTRAINT_NAME ) = upper('" +
                                constraint_name + "') order by ORDINAL_POSITION");
            }
            if (DBMSType == DBConst.DB_DB2) {
                jssrs = texeSQL.execSQL(
                        "select COLNAME from SYSCAT.KEYCOLUSE where  upper(TABNAME) = upper('" +
                                table.getCode() + "') and upper(CONSTNAME) = upper('" +
                                constraint_name + "') order by COLSEQ");
            }
            if (jssrs.getMaxRow() != tKey.getColumnNum()) {
                log.info("表" + table.getCode()
                        + "在PDM和数据库中'主键字段个数'不一致(数据库主键字段个数:"
                        + jssrs.getMaxRow() + ",PDM中主键字段个数:"
                        + tKey.getColumnNum() + ")");
            }
            for (int i = 1; i <= jssrs.getMaxRow(); i++) {
                boolean haveCol = false;
                for (int j = 0; j < tKey.getColumnNum(); j++) {
                    if (tKey.getColumn(j).equalsIgnoreCase(jssrs.GetText(i, 1))) {
                        haveCol = true;
                        break;
                    }
                }
                if (haveCol == false) {
                    log.info("表" + table.getCode() + "在数据库中主键字段"
                            + jssrs.GetText(i, 1) + "在PDM中没有出现");
                }
            }
            for (int i = 0; i < tKey.getColumnNum(); i++) {
                boolean haveCol = false;
                for (int j = 1; j <= jssrs.getMaxRow(); j++) {
                    if (tKey.getColumn(i).equalsIgnoreCase(jssrs.GetText(j, 1))) {
                        haveCol = true;
                        break;
                    }
                }
                if (haveCol == false) {
                    log.info("表" + table.getCode() + "在PDM中主键字段"
                            + tKey.getColumn(i) + "在数据库中没有出现");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void compareUniqueKey(Table table) {
        try {
            ExeSQL tExeSQL = new ExeSQL(con);
            SSRS tssrs = null;
            if (DBMSType == DBConst.DB_Oracle) {
                tssrs = tExeSQL.execSQL(
                        "select constraint_name from user_constraints where table_name = upper('"
                                + table.getCode() + "') and constraint_type = 'U'");

            }
            if (DBMSType == DBConst.DB_SQLServer) {
                tssrs = tExeSQL.execSQL(
                        "select CONSTRAINT_NAME from INFORMATION_SCHEMA.TABLE_CONSTRAINTS where upper(TABLE_NAME) = upper('"
                                + table.getCode() + "') and CONSTRAINT_TYPE = 'UNIQUE'");
            }
            if (DBMSType == DBConst.DB_DB2) {
                tssrs = tExeSQL.execSQL(
                        "select CONSTNAME from SYSCAT.TABCONST where  upper(TABNAME) = upper('" +
                                table.getCode() + "') and TYPE = 'U'");
            }
            if (table.getUniqueKeyNum() != tssrs.getMaxRow()) {
                log.info("表" + table.getCode()
                        + "在数据库中唯一键的个数和在PDM中不一致(数据库为:"
                        + tssrs.getMaxRow()
                        + ",PDM为:" + table.getUniqueKeyNum() + ")");
            }
            for (int i = 1; i <= tssrs.getMaxRow(); i++) {
                String constraint_name = tssrs.GetText(i, 1);
                Key tKey = table.getKey(constraint_name);
                if (tKey == null) {
                    log.info("表" + table.getCode() + "在数据库中唯一键"
                            + constraint_name + "在PDM中没有出现");
                } else {
                    ExeSQL texeSQL = new ExeSQL(con);
                    SSRS jssrs = null;
                    if (DBMSType == DBConst.DB_Oracle) {
                        jssrs = texeSQL.execSQL(
                                "select column_name from user_cons_columns where table_name = upper('"
                                        + table.getCode()
                                        + "') and constraint_name = upper('"
                                        + constraint_name + "') order by position");

                    }
                    if (DBMSType == DBConst.DB_SQLServer) {
                        jssrs = texeSQL.execSQL(
                                "select COLUMN_NAME from INFORMATION_SCHEMA.KEY_COLUMN_USAGE where  upper(TABLE_NAME) = upper('"
                                        + table.getCode() +
                                        "') and upper(CONSTRAINT_NAME ) = upper('" +
                                        constraint_name +
                                        "') order by ORDINAL_POSITION");
                    }
                    if (DBMSType == DBConst.DB_DB2) {
                        jssrs = texeSQL.execSQL(
                                "select COLNAME from SYSCAT.KEYCOLUSE where  upper(TABNAME) = upper('" +
                                        table.getCode() +
                                        "') and upper(CONSTNAME) = upper('" +
                                        constraint_name + "') order by COLSEQ");
                    }
                    for (int j = 1; j <= jssrs.getMaxRow(); j++) {
                        boolean haveCol = false;
                        for (int k = 0; k < tKey.getColumnNum(); k++) {
                            if (tKey.getColumn(k).equalsIgnoreCase(jssrs.GetText(
                                    j, 1))) {
                                haveCol = true;
                                break;
                            }
                        }
                        if (haveCol == false) {
                            log.info("表" + table.getCode()
                                    + "在数据库中唯一键"
                                    + constraint_name + "中字段"
                                    + jssrs.GetText(i, 1)
                                    + "在PDM中没有出现");
                        }
                    }
                }
            }
            for (int i = 0; i < table.getKeyNum(); i++) {
                Key tkey = table.getKey(i);
                if (tkey.getKeyType() == DBTypes.UniqueKey) {
                    boolean DB_have_AK = false;
                    for (int j = 1; j <= tssrs.getMaxRow(); j++) {
                        if (tssrs.GetText(i,
                                1).equalsIgnoreCase(tkey.
                                getConstraintName())) {
                            DB_have_AK = true;
                            break;
                        }
                    }
                    if (DB_have_AK == false) {
                        log.info("表" + table.getCode() + "在PDM中唯一键"
                                + tkey.getConstraintName()
                                + "在数据库中没有出现");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void compareForeignKey(Table table) {
        try {
            ExeSQL tExeSQL = new ExeSQL(con);
            SSRS tssrs = null;
            if (DBMSType == DBConst.DB_Oracle) {
                tssrs = tExeSQL.execSQL(
                        "select constraint_name,r_constraint_name,delete_rule from user_constraints where table_name = upper('" +
                                table.getCode() + "') and constraint_type = 'R'");

            }
            if (DBMSType == DBConst.DB_SQLServer) {
                tssrs = tExeSQL.execSQL(
                        "select INFORMATION_SCHEMA.TABLE_CONSTRAINTS.CONSTRAINT_NAME,INFORMATION_SCHEMA.REFERENTIAL_CONSTRAINTS.UNIQUE_CONSTRAINT_NAME,INFORMATION_SCHEMA.REFERENTIAL_CONSTRAINTS.DELETE_RULE,INFORMATION_SCHEMA.REFERENTIAL_CONSTRAINTS.UPDATE_RULE from INFORMATION_SCHEMA.TABLE_CONSTRAINTS,INFORMATION_SCHEMA.REFERENTIAL_CONSTRAINTS where INFORMATION_SCHEMA.TABLE_CONSTRAINTS.CONSTRAINT_NAME = INFORMATION_SCHEMA.REFERENTIAL_CONSTRAINTS.CONSTRAINT_NAME and INFORMATION_SCHEMA.TABLE_CONSTRAINTS.CONSTRAINT_TYPE = 'FOREIGN KEY' and upper(INFORMATION_SCHEMA.TABLE_CONSTRAINTS.TABLE_NAME) = upper('" +
                                table.getCode() + "')");
            }
            if (DBMSType == DBConst.DB_DB2) {
                tssrs = tExeSQL.execSQL(
                        "select CONSTNAME,REFKEYNAME,DELETERULE,UPDATERULE from SYSCAT.REFERENCES where  upper(TABNAME) = upper('" +
                                table.getCode() + "') ");
            }
            if (table.getForeignKeyNum() != tssrs.getMaxRow()) {
                log.info("表" + table.getCode()
                        + "在数据库中外键的个数和在PDM中不一致(数据库为:"
                        + tssrs.getMaxRow()
                        + ",PDM为:" + table.getForeignKeyNum() + ")");
            }
            for (int i = 1; i <= tssrs.getMaxRow(); i++) {
                String constraint_name = tssrs.GetText(i, 1);
                Key tKey = table.getKey(constraint_name);
                if (tKey == null) {
                    log.info("表" + table.getCode() + "在数据库中外键"
                            + constraint_name + "在PDM中没有出现");
                } else {
                    String delete_rule = tssrs.GetText(i, 3);
                    int deletetype = DBTypes.getDeleteType(delete_rule);
                    //PDM中Oracle和SQLServer不区分Restrict和No Action
                    if ((DBMSType == DBConst.DB_Oracle ||
                            DBMSType == DBConst.DB_SQLServer) &&
                            (deletetype == DBTypes.Delete_Restrict ||
                                    deletetype == DBTypes.Delete_None)) {
                        if (tKey.getDeleteType() != DBTypes.Delete_Restrict &&
                                tKey.getDeleteType() != DBTypes.Delete_None) {
                            log.info("表" + table.getCode()
                                    + "在数据库中外键"
                                    + constraint_name
                                    + "的删除规则和PDM中不同(数据库为:"
                                    + delete_rule
                                    + ",PDM为:"
                                    + DBTypes.getDeleteTypeStr(tKey.
                                    getDeleteType()) + ")");
                        }
                    } else {
                        if (deletetype != tKey.getDeleteType()) {
                            log.info("表" + table.getCode()
                                    + "在数据库中外键"
                                    + constraint_name
                                    + "的删除规则和PDM中不同(数据库为:'"
                                    + delete_rule
                                    + "',PDM为:'"
                                    + DBTypes.getDeleteTypeStr(tKey.
                                    getDeleteType()) + "')");
                        }
                    }
                    if (DBMSType == DBConst.DB_SQLServer ||
                            DBMSType == DBConst.DB_DB2) {
                        String update_rule = tssrs.GetText(i, 4);
                        int updatetype = DBTypes.getUpdateType(update_rule);
                        if (DBMSType == DBConst.DB_SQLServer &&
                                (updatetype == DBTypes.Update_Restrict ||
                                        updatetype == DBTypes.Update_None)) {
                            if (tKey.getUpdateType() != DBTypes.Update_Restrict &&
                                    tKey.getUpdateType() != DBTypes.Update_None) {
                                log.info("表" + table.getCode()
                                        + "在数据库中外键"
                                        + constraint_name
                                        + "的更新规则和PDM中不同(数据库为:'"
                                        + update_rule + "',PDM为:'"
                                        + DBTypes.
                                        getUpdateTypeStr(tKey.
                                                getDeleteType())
                                        + "')");
                            }
                        } else {
                            if (updatetype != tKey.getUpdateType()) {
                                log.info("表" + table.getCode()
                                        + "在数据库中外键"
                                        + constraint_name
                                        + "的更新规则和PDM中不同(数据库为:'"
                                        + update_rule + "',PDM为:'"
                                        + DBTypes.
                                        getUpdateTypeStr(tKey.
                                                getDeleteType())
                                        + "')");
                            }
                        }
                    }
                    String r_constraint_name = tssrs.GetText(i, 2);
                    ExeSQL texeSQL = new ExeSQL(con);
                    String r_table = null;
                    if (DBMSType == DBConst.DB_Oracle) {
                        r_table = texeSQL.getOneValue(
                                "select table_name from user_constraints where constraint_name = upper('"
                                        + r_constraint_name + "')");
                    }
                    if (DBMSType == DBConst.DB_SQLServer) {
                        r_table = texeSQL.getOneValue(
                                "select TABLE_NAME from INFORMATION_SCHEMA.TABLE_CONSTRAINTS where upper(CONSTRAINT_NAME) = upper('"
                                        + r_constraint_name + "')");
                    }
                    if (DBMSType == DBConst.DB_DB2) {
                        r_table = texeSQL.getOneValue(
                                "select TABNAME from SYSCAT.TABCONST where upper(CONSTNAME) = upper('" +
                                        r_constraint_name + "')");
                    }
                    if (!tKey.getRefTable().equalsIgnoreCase(r_table)) {
                        log.info("表" + table.getCode() + "在数据库中外键"
                                + constraint_name + "引用的表"
                                + r_table
                                + "和PDM中不同(数据库为:'" + r_table
                                + "',PDM为:'"
                                + tKey.getRefTable() + "')");
                    }
                    ExeSQL texesql = new ExeSQL(con);
                    SSRS jssrs = null;
                    if (DBMSType == DBConst.DB_Oracle) {
                        jssrs = texesql.execSQL(
                                "select a.column_name,b.column_name from user_cons_columns a,user_cons_columns b where a.position = b.position(+) and a.constraint_name = upper('"
                                        + constraint_name +
                                        "') and b.constraint_name = upper('" +
                                        r_constraint_name + "')");

                    }
                    if (DBMSType == DBConst.DB_SQLServer) {
                        jssrs = texeSQL.execSQL(
                                "select a.COLUMN_NAME,b.COLUMN_NAME from INFORMATION_SCHEMA.KEY_COLUMN_USAGE a RIGHT JOIN INFORMATION_SCHEMA.KEY_COLUMN_USAGE b ON a. ORDINAL_POSITION = b.ORDINAL_POSITION where  upper(a.CONSTRAINT_NAME) = upper('"
                                        + constraint_name +
                                        "') and upper(b.CONSTRAINT_NAME) = upper('" +
                                        r_constraint_name + "') ");
                    }
                    if (DBMSType == DBConst.DB_DB2) {
                        jssrs = texeSQL.execSQL(
                                "select a.COLNAME,b.COLNAME from SYSCAT.KEYCOLUSE a right outer join SYSCAT.KEYCOLUSE b on a.COLSEQ = b.COLSEQ where  upper(a.CONSTNAME) = upper('" +
                                        constraint_name +
                                        "') and upper(b.CONSTNAME) = upper('" +
                                        r_constraint_name + "')");
                    }
                    for (int j = 1; j <= jssrs.getMaxRow(); j++) {
                        String col = jssrs.GetText(j, 1);
                        String ref_col = jssrs.GetText(j, 2);
                        boolean haveCol = false;
                        for (int k = 0; k < tKey.getColumnNum(); k++) {
                            String[] a = tKey.getRefJoin(k);
                            if (a[0].equalsIgnoreCase(col)
                                    && a[1].equalsIgnoreCase(ref_col)) {
                                haveCol = true;
                                break;
                            }
                        }
                        if (haveCol == false) {
                            log.info("表" + table.getCode()
                                    + "在数据库中外键"
                                    + constraint_name + "字段对("
                                    + col + ","
                                    + ref_col + ")在PDM中没有出现");
                        }
                    }
                }
            }
            for (int i = 0; i < table.getKeyNum(); i++) {
                Key tkey = table.getKey(i);
                if (tkey.getKeyType() == DBTypes.ForeignKey) {
                    boolean DB_have_FK = false;
                    for (int j = 1; j <= tssrs.getMaxRow(); j++) {
                        if (tssrs.GetText(j,
                                1).equalsIgnoreCase(tkey.
                                getConstraintName())) {
                            DB_have_FK = true;
                            break;
                        }
                    }
                    if (DB_have_FK == false) {
                        log.info("表" + table.getCode() + "在PDM中外键"
                                + tkey.getConstraintName()
                                + "在数据库中没有出现");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
