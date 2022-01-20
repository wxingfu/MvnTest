<#import "lib/copyright.ftl" as copyright>
<@copyright.copyright date="${currentYear}" />

package com.sinosoft.lis.db;

import java.io.StringReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.Date;
import java.sql.Types;
import java.util.ArrayList;

import com.sinosoft.lis.schema.${tableName}Schema;
import com.sinosoft.lis.vschema.${tableName}Set;
import com.sinosoft.utility.DBOper;
import com.sinosoft.utility.DBConnPool;
import com.sinosoft.utility.SQLString;
import com.sinosoft.utility.StrTool;
import com.sinosoft.utility.SysConst;
import com.sinosoft.utility.CError;
import com.sinosoft.utility.CErrors;
import com.sinosoft.lis.pubfun.FDate;
import com.sinosoft.lis.pubfun.PreValueBase;

<#import "lib/class_comments.ftl" as classComments>
<@classComments.comment type="DB"/>
public class ${tableName}DB extends ${tableName}Schema {

    // @Field
    private Connection con;

    private DBOper db;

    /**
     * flag = true: 传入Connection
     * flag = false: 不传入Connection
     */
    private boolean mflag = false;

    /**
     * 为批量操作而准备的语句和游标对象
     */
    private ResultSet mResultSet = null;

    private PreparedStatement mStatement = null;

    private FDate fDate = new FDate(); // 处理日期

    // @Constructor
    public ${tableName}DB(Connection cConnection) {
        con = cConnection;
        db = new DBOper(con, "${tableName}");
        mflag = true;
    }

    public ${tableName}DB() {
        con = null;
        db = new DBOper("${tableName}");
        mflag = false;
    }

    // @Method
    public boolean deleteSQL() {
        ${tableName}Schema tSchema = this.getSchema();
        if (db.deleteSQL(tSchema)) {
            return true;
        } else {
            // @@错误处理
            this.mErrors.copyAllErrors(db.mErrors);
            CError tError = new CError();
            tError.moduleName = "${tableName}DB";
            tError.functionName = "deleteSQL";
            tError.errorMessage = "操作失败!";
            this.mErrors.addOneError(tError);
            return false;
        }
    }

    public int getCount() {
        ${tableName}Schema tSchema = this.getSchema();
        int tCount = db.getCount(tSchema);
        if (tCount < 0) {
            // @@错误处理
            this.mErrors.copyAllErrors(db.mErrors);
            CError tError = new CError();
            tError.moduleName = "${tableName}DB";
            tError.functionName = "getCount";
            tError.errorMessage = "操作失败!";
            this.mErrors.addOneError(tError);
            return -1;
        }
        return tCount;
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
            pstmt = con.prepareStatement("INSERT INTO ${tableName} VALUES(${InsertColumnClause}));
<#if tableColumns??>
    <#list tableColumns as column>
        <#if column.dataType=="String">
            if (this.get${column.code}() == null || this.get${column.code}().equals("null")) {
                pstmt.setNull(${column_index+1}, Types.VARCHAR);
            } else {
                pstmt.setString(${column_index+1}, this.get${column.code}());
            }

        <#elseif column.dataType=="Date">
            if (this.get${column.code}() == null || this.get${column.code}().equals("null")) {
                pstmt.setNull(${column_index+1}, Types.DATE);
            } else {
                pstmt.setDate(${column_index+1}, Date.valueOf(this.get${column.code}()));
            }

        <#elseif column.dataType=="int"||column.dataType=="Integer">
            pstmt.setInt(${column_index+1}, this.get${column.code}());

        <#elseif column.dataType=="double"||column.dataType=="Double">
            pstmt.setDouble(${column_index+1}, this.get${column.code}());

        <#elseif column.dataType=="InputStream">
            if (this.get${column.code}() == null || this.get${column.code}().equals("null")) {
                pstmt.setNull(${column_index+1}, Types.BLOB);
            } else {
                StringReader strReader = new StringReader(this.get${column.code}());
                pstmt.setCharacterStream(${column_index+1}, strReader, this.get${column_index+1}().length());
            }

        </#if>
    </#list>
</#if>
            // execute sql
            pstmt.executeUpdate();
            pstmt.close();
        } catch(Exception ex) {
            ex.printStackTrace();
            // @@错误处理
            this.mErrors.copyAllErrors(db.mErrors);
            CError tError = new CError();
            tError.moduleName = "${tableName}DB";
            tError.functionName = "insert()";
            tError.errorMessage = ex.toString();
            this.mErrors.addOneError(tError);
            // 输出出错Sql语句
            SQLString sqlObj = new SQLString("${tableName}");
            sqlObj.setSQL(1, this.getSchema());
            System.out.println("出错Sql为：" + sqlObj.getSQL());
            try {
                pstmt.close();
            } catch(Exception e) {
                e.printStackTrace();
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
     **/
    public boolean delete() {
        PreparedStatement pstmt = null;
        if (!mflag) {
            con = DBConnPool.getConnection();
        }
        try {
            pstmt = con.prepareStatement("DELETE FROM ${tableName} WHERE  ${PKWhereClause}");
    <#list pkList as pk>
        <#if pk.dataType=="String">
            if (this.get${pk.code}() == null || this.get${pk.code}().equals("null")) {
                pstmt.setNull(${pk_index+1}, Types.VARCHAR);
            } else {
                pstmt.setString(${pk_index+1}, this.get${pk.code}());
            }

        <#elseif pk.dataType=="Date">
            if (this.get${pk.code}() == null || this.get${pk.code}().equals("null")) {
                pstmt.setNull(${pk_index+1}, Types.DATE);
            } else {
                pstmt.setDate(${pk_index+1}, Date.valueOf(this.get${pk.code}()));
            }

        <#elseif pk.dataType=="int"||pk.dataType=="Integer">
            pstmt.setInt(${pk_index+1}, this.get${pk.code}());

        <#elseif pk.dataType=="double"||pk.dataType=="Double">
            pstmt.setDouble(${pk_index+1}, this.get${pk.code}());

        <#elseif pk.dataType=="InputStream">
            if(this.get${pk.code}() == null || this.get${pk.code}().equals("null")) {
                pstmt.setString(${pk_index+1}, Types.BLOB);
            } else {
                StringReader strReader = new StringReader(this.get${pk.code}());
                pstmt.setCharacterStream(${pk_index+1}, strReader, this.get${pk.code}().length());
            }

        </#if>
    </#list>
            pstmt.executeUpdate();
            pstmt.close();
        } catch(Exception ex) {
            ex.printStackTrace();
            // @@错误处理
            this.mErrors.copyAllErrors(db.mErrors);
            CError tError = new CError();
            tError.moduleName = "${tableName}DB";
            tError.functionName = "delete()";
            tError.errorMessage = ex.toString();
            this.mErrors.addOneError(tError);
            // 输出出错Sql语句
            SQLString sqlObj = new SQLString("${tableName}");
            sqlObj.setSQL(4, this.getSchema());
            System.out.println("出错Sql为：" + sqlObj.getSQL());
            try {
                pstmt.close();
            } catch(Exception e) {
                e.printStackTrace();
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
            pstmt = con.prepareStatement("UPDATE ${tableName} SET ${UpdateColumnClause} WHERE ${PKWhereClause}");
<#if tableColumns??>
    <#list tableColumns as column>
        <#if column.dataType=="String">
            if (this.get${column.code}() == null || this.get${column.code}().equals("null")) {
                pstmt.setNull(${column_index+1}, Types.VARCHAR);
            } else {
                pstmt.setString(${column_index+1}, this.get${column.code}());
            }

        <#elseif column.dataType=="Date">
            if (this.get${column.code}() == null || this.get${column.code}().equals("null")) {
                pstmt.setNull(${column_index+1}, Types.DATE);
            } else {
                pstmt.setDate(${column_index+1}, Date.valueOf(this.get${column.code}()));
            }

        <#elseif column.dataType=="int"||column.dataType=="Integer">
            pstmt.setInt(${column_index+1}, this.get${column.code}());

        <#elseif column.dataType=="double"||column.dataType=="Double">
            pstmt.setDouble(${column_index+1}, this.get${column.code}());

        <#elseif column.dataType=="InputStream">
            if (this.get${column.code}() == null || this.get${column.code}().equals("null")) {
                pstmt.setNull(${column_index+1}, Types.BLOB);
            } else {
                StringReader strReader = new StringReader(this.get${column.code}());
                pstmt.setCharacterStream(${column_index+1}, strReader, this.get${column_index+1}().length());
            }

        </#if>
    </#list>
</#if>
    <#list pkList as pk>
        <#if pk.dataType=="String">
            if (this.get${pk.code}() == null || this.get${pk.code}().equals("null")) {
                pstmt.setNull(${pk_index+1+tableColumns?size}, Types.VARCHAR);
            } else {
                pstmt.setString(${pk_index+1+tableColumns?size}, this.get${pk.code}());
            }

        <#elseif pk.dataType=="Date">
            if (this.get${pk.code}() == null || this.get${pk.code}().equals("null")) {
                pstmt.setNull(${pk_index+1+tableColumns?size}, Types.DATE);
            } else {
                pstmt.setDate(${pk_index+1+tableColumns?size}, Date.valueOf(this.get${pk.code}()));
            }

        <#elseif pk.dataType=="int"||pk.dataType=="Integer">
            pstmt.setInt(${pk_index+1+tableColumns?size}, this.get${pk.code}());

        <#elseif pk.dataType=="double"||pk.dataType=="Double">
            pstmt.setDouble(${pk_index+1+tableColumns?size}, this.get${pk.code}());

        <#elseif pk.dataType=="InputStream">
            if(this.get${pk.code}() == null || this.get${pk.code}().equals("null")) {
                pstmt.setString(${pk_index+1+tableColumns?size}, Types.BLOB);
            } else {
                StringReader strReader = new StringReader(this.get${pk.code}());
                pstmt.setCharacterStream(${pk_index+1+tableColumns?size}, strReader, this.get${pk.code}().length());
            }

        </#if>
    </#list>
            pstmt.executeUpdate();
            pstmt.close();
        } catch(Exception ex) {
            ex.printStackTrace();
            // @@错误处理
            this.mErrors.copyAllErrors(db.mErrors);
            CError tError = new CError();
            tError.moduleName = "${tableName}DB";
            tError.functionName = "update()";
            tError.errorMessage = ex.toString();
            this.mErrors.addOneError(tError);
            // 输出出错Sql语句
            SQLString sqlObj = new SQLString("${tableName}");
            sqlObj.setSQL(2, this.getSchema());
            System.out.println("出错Sql为：" + sqlObj.getSQL());
            try {
                pstmt.close();
            } catch(Exception e) {
                e.printStackTrace();
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






