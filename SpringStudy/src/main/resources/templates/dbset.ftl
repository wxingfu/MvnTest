/**
 * Copyright (c) ${currentYear} Sinosoft Co.,LTD.
 * All right reserved.
 */

package com.sinosoft.lis.vdb;

import java.io.StringReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.Date;

import com.sinosoft.lis.vschema.${tableName}Set;
import com.sinosoft.utility.DBOper;
import com.sinosoft.utility.DBConnPool;
import com.sinosoft.utility.SQLString;
import com.sinosoft.utility.StrTool;
import com.sinosoft.utility.CError;
import com.sinosoft.utility.CErrors;

/**
 * <p>自动生成的文件，不可手工修改！</p>
 * <p>ClassName: ${tableName}DBSet </p>
 * <p>Description: DB层多记录数据库操作类文件 </p>
 * <p>Company: Sinosoft Co.,LTD </p>
 * @Database: ${dataBase}
 * @author: Makerx
 * @CreateDatetime: ${createDateTime}
 * @vm: ${javaVmName}(build ${javaVmVersion}, ${javaVmVendor})
 * @os: ${osName}(${osArch})
 * @creator: ${userName}(${userCountry})
 */
public class ${tableName}DBSet extends ${tableName}Set {

    // @Field
    private Connection con;

    private DBOper db;

    private static final int BATCH_SIZE = 300;

    /**
     * flag = true: 传入Connection
     * flag = false: 不传入Connection
     */
    private boolean mflag = false;

    // @Constructor
    public ${tableName}DBSet(Connection cConnection) {
        con = cConnection;
        db = new DBOper(con, "${tableName}");
        mflag = true;
    }

    public ${tableName}DBSet() {
        db = new DBOper("${tableName}");
    }

    // @Method
    public boolean deleteSQL() {
        if (db.deleteSQL(this)) {
            return true;
        } else {
            // @@错误处理
            this.mErrors.copyAllErrors(db.mErrors);
            CError tError = new CError();
            tError.moduleName = "${tableName}DBSet";
            tError.functionName = "deleteSQL";
            tError.errorMessage = "操作失败!";
            this.mErrors.addOneError(tError);
            return false;
        }
    }

    /**
     * 新增操作
     * @return boolean
     */
    public boolean insert() {
        PreparedStatement pstmt = null;
        if (!mflag) {
            con = DBConnPool.getConnection();
        }
        try {
            if (!mflag) {
                // 如果是内部创建的连接，需要设置Commit模式
                con.setAutoCommit(false);
            }
            int tCount = this.size();
            int hasExeCount = 0;
            pstmt = con.prepareStatement("INSERT INTO ${tableName} VALUES( ${InsertColumnClause})");
            for (int i = 1; i <= tCount; i++) {
        <#if tableColumns??>
            <#list tableColumns as column>
            <#if column.dataType=="String">
                if (this.get(i).get${column.code}() == null || this.get(i).get${column.code}().equals("null")) {
                    pstmt.setString(${column_index+1}, null);
                } else {
                    pstmt.setString(${column_index+1}, this.get(i).get${column.code}());
                }

            <#elseif column.dataType=="Date">
                if (this.get(i).get${column.code}() == null || this.get(i).get${column.code}().equals("null")) {
                    pstmt.setDate(${column_index+1}, null);
                } else {
                    pstmt.setDate(${column_index+1}, Date.valueOf(this.get(i).get${column.code}()));
                }

            <#elseif column.dataType=="int"||column.dataType=="Integer">
                pstmt.setInt(${column_index+1}, this.get(i).get${column.code}());

            <#elseif column.dataType=="double"||column.dataType=="Double">
                pstmt.setDouble(${column_index+1}, this.get(i).get${column.code}());

            <#elseif column.dataType=="InputStream">
                if(this.get(i).get${column.code}() == null || this.get(i).get${column.code}().equals("null")) {
                    pstmt.setString(${column_index+1}, null);
                } else {
                    StringReader strReader = new StringReader(this.get(i).get${column.code}());
                    pstmt.setCharacterStream(${column_index+1}, strReader, this.get(i).get${column.code}().length());
                }

            </#if>
            </#list>
        </#if>
                pstmt.addBatch();
                hasExeCount++;
                if (hasExeCount % BATCH_SIZE == 0) {
                    pstmt.executeBatch();
                }
            }
            pstmt.executeBatch();
            pstmt.close();
            if (!mflag) {
                // 如果是内部创建的连接，执行成功后需要执行Commit
                con.commit();
                con.close();
            }
        } catch(Exception ex) {
            // @@错误处理
            ex.printStackTrace();
            this.mErrors.copyAllErrors(db.mErrors);
            CError tError = new CError();
            tError.moduleName = "${tableName}DBSet";
            tError.functionName = "insert()";
            tError.errorMessage = ex.toString();
            this.mErrors.addOneError(tError);
            int tCount = this.size();
            SQLString sqlObj = new SQLString("${tableName}");
            for (int i = 1; i <= tCount; i++) {
                // 输出出错Sql语句
                sqlObj.setSQL(1, this.get(i));
                System.out.println("出错Sql为：" + sqlObj.getSQL());
            }
            try {
                pstmt.close();
            } catch(Exception e) {
                e.printStackTrace();
            }
            if(!mflag) {
                //如果是内部创建的连接，出错后需要执行RollBack
                try {
                    con.rollback();
                    con.close();
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
            return false;
        } finally {
            if (!mflag) {
                try {
                    if (con.isClosed() == false) {
                        con.close();
                    }
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }

<#if pkList?? && (pkNum>0)>
    /**
     * 删除操作
     * 删除条件：主键
     * @return boolean
     */
    public boolean delete() {
        PreparedStatement pstmt = null;
        if (!mflag) {
            con = DBConnPool.getConnection();
        }
        try {
            if (!mflag) {
                // 如果是内部创建的连接，需要设置Commit模式
                con.setAutoCommit(false);
            }
            int tCount = this.size();
            int hasExeCount = 0;
            pstmt = con.prepareStatement("DELETE FROM ${tableName} WHERE  ${PKWhereClause}");
            for (int i = 1; i <= tCount; i++) {
        <#list pkList as pk>
            <#if pk.dataType=="String">
                if (this.get(i).get${pk.code}() == null || this.get(i).get${pk.code}().equals("null")) {
                    pstmt.setString(${pk_index+1}, null);
                } else {
                    pstmt.setString(${pk_index+1}, this.get(i).get${pk.code}());
                }

            <#elseif pk.dataType=="Date">
                if (this.get(i).get${pk.code}() == null || this.get(i).get${pk.code}().equals("null")) {
                    pstmt.setDate(${pk_index+1}, null);
                } else {
                    pstmt.setDate(${pk_index+1}, Date.valueOf(this.get(i).get${pk.code}()));
                }

            <#elseif pk.dataType=="int"||pk.dataType=="Integer">
                pstmt.setInt(${pk_index+1}, this.get(i).get${pk.code}());

            <#elseif pk.dataType=="double"||pk.dataType=="Double">
                pstmt.setDouble(${pk_index+1}, this.get(i).get${pk.code}());

            <#elseif pk.dataType=="InputStream">
                if(this.get(i).get${pk.code}() == null || this.get(i).get${pk.code}().equals("null")) {
                    pstmt.setString(${pk_index+1}, null);
                } else {
                    StringReader strReader = new StringReader(this.get(i).get${pk.code}());
                    pstmt.setCharacterStream(${pk_index+1}, strReader, this.get(i).get${pk.code}().length());
                }

            </#if>
        </#list>
                pstmt.addBatch();
                hasExeCount++;
                if (hasExeCount % BATCH_SIZE == 0) {
                    pstmt.executeBatch();
                }
            }
            pstmt.executeBatch();
            pstmt.close();
            if (!mflag) {
                // 如果是内部创建的连接，执行成功后需要执行Commit
                con.commit();
                con.close();
            }
        } catch(Exception ex) {
            // @@错误处理
            ex.printStackTrace();
            this.mErrors.copyAllErrors(db.mErrors);
            CError tError = new CError();
            tError.moduleName = "${tableName}DBSet";
            tError.functionName = "delete()";
            tError.errorMessage = ex.toString();
            this.mErrors.addOneError(tError);
            int tCount = this.size();
            SQLString sqlObj = new SQLString("${tableName}");
            for (int i = 1; i <= tCount; i++) {
                // 输出出错Sql语句
                sqlObj.setSQL(4, this.get(i));
                System.out.println("出错Sql为：" + sqlObj.getSQL());
            }
            try {
                pstmt.close();
            } catch(Exception e) {
                e.printStackTrace();
            }
            if (!mflag) {
                //如果是内部创建的连接，出错后需要执行RollBack
                try {
                    con.rollback();
                    con.close();
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
            return false;
        } finally {
            if (!mflag) {
                try {
                    if (con.isClosed() == false) {
                        con.close();
                    }
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }

    /**
     * 更新操作
     * 更新条件：主键
     * @return boolean
     */
    public boolean update() {
        PreparedStatement pstmt = null;
        if (!mflag) {
            con = DBConnPool.getConnection();
        }
        try {
            if (!mflag) {
                // 如果是内部创建的连接，需要设置Commit模式
                con.setAutoCommit(false);
            }
            int tCount = this.size();
            int hasExeCount = 0;
            pstmt = con.prepareStatement("UPDATE ${tableName} SET  ${UpdateColumnClause} WHERE  ${PKWhereClause}");
            for (int i = 1; i <= tCount; i++) {
    <#if tableColumns??>
        <#list tableColumns as column>
            <#if column.dataType=="String">
                if (this.get(i).get${column.code}() == null || this.get(i).get${column.code}().equals("null")) {
                    pstmt.setString(${column_index+1}, null);
                } else {
                    pstmt.setString(${column_index+1}, this.get(i).get${column.code}());
                }

            <#elseif column.dataType=="Date">
                if (this.get(i).get${column.code}() == null || this.get(i).get${column.code}().equals("null")) {
                    pstmt.setDate(${column_index+1}, null);
                } else {
                    pstmt.setDate(${column_index+1}, Date.valueOf(this.get(i).get${column.code}()));
                }

            <#elseif column.dataType=="int"||column.dataType=="Integer">
                pstmt.setInt(${column_index+1}, this.get(i).get${column.code}());

            <#elseif column.dataType=="double"||column.dataType=="Double">
                pstmt.setDouble(${column_index+1}, this.get(i).get${column.code}());

            <#elseif column.dataType=="InputStream">
                if(this.get(i).get${column.code}() == null || this.get(i).get${column.code}().equals("null")) {
                    pstmt.setString(${column_index+1}, null);
                } else {
                    StringReader strReader = new StringReader(this.get(i).get${column.code}());
                    pstmt.setCharacterStream(${column_index+1}, strReader, this.get(i).get${column.code}().length());
                }

            </#if>
        </#list>
    </#if>
        <#list pkList as pk>
            <#if pk.dataType=="String">
                if (this.get(i).get${pk.code}() == null || this.get(i).get${pk.code}().equals("null")) {
                    pstmt.setString(${pk_index+1+tableColumns?size}, null);
                } else {
                    pstmt.setString(${pk_index+1+tableColumns?size}, this.get(i).get${pk.code}());
                }

            <#elseif pk.dataType=="Date">
                if (this.get(i).get${pk.code}() == null || this.get(i).get${pk.code}().equals("null")) {
                    pstmt.setDate(${pk_index+1+tableColumns?size}, null);
                } else {
                    pstmt.setDate(${pk_index+1+tableColumns?size}, Date.valueOf(this.get(i).get${pk.code}()));
                }

            <#elseif pk.dataType=="int"||pk.dataType=="Integer">
                pstmt.setInt(${pk_index+1+tableColumns?size}, this.get(i).get${pk.code}());

            <#elseif pk.dataType=="double"||pk.dataType=="Double">
                pstmt.setDouble(${pk_index+1+tableColumns?size}, this.get(i).get${pk.code}());

            <#elseif pk.dataType=="InputStream">
                if(this.get(i).get${pk.code}() == null || this.get(i).get${pk.code}().equals("null")) {
                    pstmt.setString(${pk_index+1+tableColumns?size}, null);
                } else {
                    StringReader strReader = new StringReader(this.get(i).get${pk.code}());
                    pstmt.setCharacterStream(${pk_index+1+tableColumns?size}, strReader, this.get(i).get${pk.code}().length());
                }

            </#if>
        </#list>
                pstmt.addBatch();
                hasExeCount++;
                if (hasExeCount % BATCH_SIZE == 0) {
                    pstmt.executeBatch();
                }
            }
            pstmt.executeBatch();
            pstmt.close();
            if (!mflag) {
                // 如果是内部创建的连接，执行成功后需要执行Commit
                con.commit();
                con.close();
            }
        } catch(Exception ex) {
            // @@错误处理
            ex.printStackTrace();
            this.mErrors.copyAllErrors(db.mErrors);
            CError tError = new CError();
            tError.moduleName = "${tableName}DBSet";
            tError.functionName = "update()";
            tError.errorMessage = ex.toString();
            this.mErrors.addOneError(tError);
            int tCount = this.size();
            SQLString sqlObj = new SQLString("${tableName}");
            for (int i = 1; i <= tCount; i++) {
                // 输出出错Sql语句
                sqlObj.setSQL(2, this.get(i));
                System.out.println("出错Sql为：" + sqlObj.getSQL());
            }
            try {
                pstmt.close();
            } catch(Exception e) {
                e.printStackTrace();
            }
            if (!mflag) {
                //如果是内部创建的连接，出错后需要执行RollBack
                try {
                    con.rollback();
                    con.close();
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
            return false;
        } finally {
            if (!mflag) {
                try {
                    if (con.isClosed() == false) {
                        con.close();
                    }
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }
</#if>
}
