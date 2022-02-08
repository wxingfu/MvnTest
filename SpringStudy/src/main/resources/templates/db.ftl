<#import "lib/copyright.ftl" as copyright>
<@copyright.copyright/>

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

<#if pkList?? && (pkList?size>0)>
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

    /**
     * 查询操作
     * 查询条件：主键
     * @return boolean
     */
    public boolean getInfo() {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        if (!mflag) {
            con = DBConnPool.getConnection();
        }
        try {
            pstmt = con.prepareStatement("SELECT * FROM ${tableName} WHERE ${PKWhereClause}", ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
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
            rs = pstmt.executeQuery();
            int i = 0;
            if (rs.next()) {
                i++;
                if (!this.setSchema(rs, i)) {
                    // @@错误处理
                    CError tError = new CError();
                    tError.moduleName = "${tableName}DB";
                    tError.functionName = "getInfo";
                    tError.errorMessage = "取数失败!";
                    this.mErrors.addOneError(tError);
                    i = 0;
                }
            }
            try {
                rs.close();
            } catch(Exception e1) {
                e1.printStackTrace();
            }
            try{
                pstmt.close();
            } catch(Exception e2) {
                e2.printStackTrace();
            }
            if (i == 0) {
                return false;
            }
        } catch(Exception e) {
            e.printStackTrace();
            CError tError = new CError();
            tError.moduleName = "${tableName}DB";
            tError.functionName = "getInfo";
            tError.errorMessage = e.toString();
            this.mErrors.addOneError(tError);
            // 输出出错Sql语句
            SQLString sqlObj = new SQLString("${tableName}");
            sqlObj.setSQL(6, this.getSchema());
            System.out.println("出错Sql为：" + sqlObj.getSQL());
            try {
                rs.close();
            } catch(Exception e1) {
                e1.printStackTrace();
            }
            try {
                pstmt.close();
            } catch(Exception e2) {
                e2.printStackTrace();
            }
            return false;
        } finally {
            // 断开数据库连接
            if (!mflag) {
                try{
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

    public ${tableName}Set query() {
        return query(-1,-1);
    }

    /**
     * 查询操作
     * 查询条件：传入的schema中有值的字段
     * @return boolean
     */
    public ${tableName}Set query(int nStart, int nCount) {
        SQLString sqlObj = new SQLString("${tableName}");
        sqlObj.setSQL(8, this.getSchema());
        String sql = sqlObj.getSQL();
        ArrayList bindVarAL = sqlObj.getBindVarAL();
        return executeQueryAsBindVariable(sql, nStart, nCount, bindVarAL);
    }

    public ${tableName}Set executeQuery(String sql) {
        return executeQuery(sql, -1, -1);
    }

    public ${tableName}Set executeQuery(String sql, int nStart, int nCount) {
        return executeQueryAsBindVariable(sql, nStart, nCount, null);
    }

    public ${tableName}Set executeQueryAsBindVariable(String sql, ArrayList pvbList) {
        return executeQueryAsBindVariable(sql, -1, -1, pvbList);
    }

    public ${tableName}Set executeQueryAsBindVariable(String sql, int nStart, int nCount, ArrayList pvbList) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ${tableName}Set a${tableName}Set = new ${tableName}Set();
        if (!mflag) {
            con = DBConnPool.getConnection();
        }
        try {
            pstmt = con.prepareStatement(sql, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            if (pvbList != null && pvbList.size() > 0) {
                for (int i = 0; i < pvbList.size(); i++){
                    PreValueBase tPvb = (PreValueBase) pvbList.get(i);
                    pstmt.setObject(tPvb.getLocation(), tPvb.getValue(), tPvb.getValueType());
                }
            }
            rs = pstmt.executeQuery();
            int i = 0;
            while (rs.next()) {
                i++;
                //如果nStart=-1，表示全部查询
                if (nStart != -1 && i < nStart) {
                    continue;
                }
                if (nStart != -1 && i >= nStart + nCount) {
                    break;
                }
                ${tableName}Schema s1 = new ${tableName}Schema();
                if (!s1.setSchema(rs, i)) {
                    // @@错误处理
                    CError tError = new CError();
                    tError.moduleName = "${tableName}DB";
                    tError.functionName = "executeQuery";
                    tError.errorMessage = "sql语句有误，请查看表名及字段名信息!";
                    this.mErrors.addOneError(tError);
                }
                a${tableName}Set.add(s1);
            }
            try {
                rs.close();
            } catch(Exception e1) {
                e1.printStackTrace();
            }
            try{
                pstmt.close();
            } catch(Exception e2) {
                e2.printStackTrace();
            }
        } catch(Exception e) {
            System.out.println("##### Error Sql in ${tableName}DB at query(): " + sql);
            // @@错误处理
            CError tError = new CError();
            tError.moduleName = "${tableName}DB";
            tError.functionName = "query";
            tError.errorMessage = e.toString();
            this.mErrors.addOneError(tError);
            try {
                rs.close();
            } catch(Exception e1) {
                e1.printStackTrace();
            }
            try {
                pstmt.close();
            } catch(Exception e2) {
                e2.printStackTrace();
            }
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
        return a${tableName}Set;
    }

    public boolean update(String strWherePart) {
        Statement stmt = null;
        if (!mflag) {
            con = DBConnPool.getConnection();
        }
        SQLString sqlObj = new SQLString("${tableName}");
        sqlObj.setSQL(2, this.getSchema());
        String sql = "update ${tableName} " + sqlObj.getUpdPart() + " where " + strWherePart;
        try {
            stmt = con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            int operCount = stmt.executeUpdate(sql);
            if (operCount == 0) {
                // @@错误处理
                CError tError = new CError();
                tError.moduleName = "${tableName}DB";
                tError.functionName = "update";
                tError.errorMessage = "更新数据失败!";
                this.mErrors.addOneError(tError);
                return false;
            }
        } catch(Exception e) {
            System.out.println("##### Error Sql in ${tableName}DB at update(String strWherePart): " + sql);
            // @@错误处理
            CError tError = new CError();
            tError.moduleName = "${tableName}DB";
            tError.functionName = "update";
            tError.errorMessage = e.toString();
            this.mErrors.addOneError(tError);
            try {
                stmt.close();
            } catch(Exception ex1) {
                ex1.printStackTrace();
            }
            return false;
        } finally {
            // 断开数据库连接
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
     * 准备数据集
     * @param strSQL String
     * @return boolean
     */
    public boolean prepareData(String strSQL) {
        return prepareDataAsBindVariable(strSQL, null);
    }

    /**
     * 准备数据集，使用绑定变量方式
     * @param strSQL String
     * @param pvbList ArrayList
     * @return boolean
     */
    public boolean prepareDataAsBindVariable(String strSQL, ArrayList pvbList) {
        if (mResultSet != null) {
            // @@错误处理
            CError tError = new CError();
            tError.moduleName = "${tableName}DB";
            tError.functionName = "prepareData";
            tError.errorMessage = "数据集非空，程序在准备数据集之后，没有关闭！";
            this.mErrors.addOneError(tError);
            return false;
        }
        if (!mflag) {
            con = DBConnPool.getConnection();
        }
        try {
            mStatement = con.prepareStatement(StrTool.GBKToUnicode(strSQL), ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            if (pvbList != null && pvbList.size() > 0) {
                for (int i = 0; i < pvbList.size(); i++) {
                    PreValueBase tPvb = (PreValueBase) pvbList.get(i);
                    mStatement.setObject(tPvb.getLocation(), tPvb.getValue(), tPvb.getValueType());
                }
            }
            mResultSet = mStatement.executeQuery();
        } catch(Exception e) {
            // @@错误处理
            CError tError = new CError();
            tError.moduleName = "${tableName}DB";
            tError.functionName = "prepareData";
            tError.errorMessage = e.toString();
            this.mErrors.addOneError(tError);
            try {
                mResultSet.close();
            } catch(Exception ex2) {
                ex2.printStackTrace();
            }
            try {
                mStatement.close();
            } catch(Exception ex3) {
                ex3.printStackTrace();
            }
            try {
                if (con.isClosed() == false) {
                    con.close();
                }
            } catch(Exception e1) {
                e1.printStackTrace();
            }
            return false;
        }
        return true;
    }

    /**
     * 获取数据集
     * @return boolean
     */
    public boolean hasMoreData() {
        boolean flag = true;
        if (null == mResultSet) {
            CError tError = new CError();
            tError.moduleName = "${tableName}DB";
            tError.functionName = "hasMoreData";
            tError.errorMessage = "数据集为空，请先准备数据集！";
            this.mErrors.addOneError(tError);
            return false;
        }
        try {
            flag = mResultSet.next();
        } catch(Exception ex) {
            CError tError = new CError();
            tError.moduleName = "${tableName}DB";
            tError.functionName = "hasMoreData";
            tError.errorMessage = ex.toString();
            this.mErrors.addOneError(tError);
            try {
                mResultSet.close();
                mResultSet = null;
            } catch(Exception ex2) {
                ex2.printStackTrace();
            }
            try {
                mStatement.close();
                mStatement = null;
            } catch(Exception ex3) {
                ex3.printStackTrace();
            }
            if (!mflag) {
                try {
                    con.close();
                } catch(Exception et) {
                    et.printStackTrace();
                }
            }
            return false;
        }
        return flag;
    }

    /**
     * 获取定量数据
     * @return ${tableName}Set
     */
    public ${tableName}Set getData() {
        int tCount = 0;
        ${tableName}Set t${tableName}Set = new ${tableName}Set();
        ${tableName}Schema t${tableName}Schema = null;
        if (null == mResultSet) {
            CError tError = new CError();
            tError.moduleName = "${tableName}DB";
            tError.functionName = "getData";
            tError.errorMessage = "数据集为空，请先准备数据集！";
            this.mErrors.addOneError(tError);
            return null;
        }
        try {
            tCount = 1;
            if (mResultSet.next()) {
                t${tableName}Schema = new ${tableName}Schema();
                t${tableName}Schema.setSchema(mResultSet, 1);
                t${tableName}Set.add(t${tableName}Schema);
            }
            //注意mResultSet.next()的作用
            while (tCount++ < SysConst.FETCHCOUNT && mResultSet.next()) {
                t${tableName}Schema = new ${tableName}Schema();
                t${tableName}Schema.setSchema(mResultSet, 1);
                t${tableName}Set.add(t${tableName}Schema);
            }
        } catch(Exception ex) {
            CError tError = new CError();
            tError.moduleName = "${tableName}DB";
            tError.functionName = "getData";
            tError.errorMessage = ex.toString();
            this.mErrors.addOneError(tError);
            try {
                mResultSet.close();
                mResultSet = null;
            } catch(Exception ex2) {
                ex2.printStackTrace();
            }
            try {
                mStatement.close();
                mStatement = null;
            } catch(Exception ex3) {
                ex3.printStackTrace();
            }
            if (!mflag) {
                try {
                    con.close();
                } catch(Exception et) {
                    et.printStackTrace();
                }
            }
            return null;
        }
        return t${tableName}Set;
    }

    /**
     * 关闭数据集
     * @return boolean
     */
    public boolean closeData() {
        boolean flag = true;
        try {
            if (null == mResultSet) {
                CError tError = new CError();
                tError.moduleName = "${tableName}DB";
                tError.functionName = "closeData";
                tError.errorMessage = "数据集已经关闭了！";
                this.mErrors.addOneError(tError);
                flag = false;
            } else {
                mResultSet.close();
                mResultSet = null;
            }
        } catch(Exception ex2) {
            CError tError = new CError();
            tError.moduleName = "${tableName}DB";
            tError.functionName = "closeData";
            tError.errorMessage = ex2.toString();
            this.mErrors.addOneError(tError);
            flag = false;
        }
        try {
            if (null == mStatement) {
                CError tError = new CError();
                tError.moduleName = "${tableName}DB";
                tError.functionName = "closeData";
                tError.errorMessage = "语句已经关闭了！";
                this.mErrors.addOneError(tError);
                flag = false;
            } else {
                mStatement.close();
                mStatement = null;
            }
        } catch(Exception ex3) {
            CError tError = new CError();
            tError.moduleName = "${tableName}DB";
            tError.functionName = "closeData";
            tError.errorMessage = ex3.toString();
            this.mErrors.addOneError(tError);
            flag = false;
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
        return flag;
    }
}
