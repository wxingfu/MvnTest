/**
 * Copyright (c) 2021 Sinosoft Co.,LTD.
 * All right reserved.
 */

package com.sinosoft.lis.schema;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.io.InputStream;
import java.util.Date;

import com.sinosoft.utility.ChgData;
import com.sinosoft.utility.SQLString;
import com.sinosoft.utility.StrTool;
import com.sinosoft.utility.SysConst;
import com.sinosoft.utility.Schema;
import com.sinosoft.utility.CError;
import com.sinosoft.utility.CErrors;
import com.sinosoft.lis.pubfun.Arith;
import com.sinosoft.lis.pubfun.FDate;

/**
 * <p>自动生成的文件，不可手工修改！</p>
 * <p>ClassName: ${className}Schema </p>
 * <p>Description: DB层 Schema 类文件 </p>
 * <p>Company: Sinosoft Co.,LTD </p>
 *
 * @Database: ${dataBase}
 * @author: Makerx
 * @CreateDatetime: ${createDateTime}
 * @vm: ${javaVmName}(build ${javaVmVersion}, ${javaVmVendor})
 * @o: ${osName}(${osArch})
 * @creator: ${userName}(${userCountry})
 */

public class ${className}Schema implements Schema, Cloneable {

<#if tableColumns??>
    // @Field
    <#list tableColumns as column>
    /**
     * ${column.name}
     *
     * <#if column.coment??>${column.coment}</#if>
     */
    private ${column.dataType} ${column.code};

    </#list>
</#if>
    public static final int FIELDNUM = ${columnNum}; // 数据库表的字段个数

    private static String[] PK; // 主键

    private FDate fDate = new FDate(); // 处理日期

    public CErrors mErrors; // 错误信息

    // @Constructor
    public ${className}Schema() {
        mErrors = new CErrors();
    <#if pkList?? && (pkNum > 0)>
        String[] pk = new String[${pkNum}];
        <#list pkList as pk>
        pk[${pk_index}] = "${pk.code}";
        </#list>
    </#if>
        PK = pk;
    }

    /**
     * Schema克隆
     *
     * @return Object
     * @throws CloneNotSupportedException
     */
    public Object clone() throws CloneNotSupportedException {
        ${className}Schema cloned = (${className}Schema) super.clone();
        cloned.fDate = (FDate) fDate.clone();
        cloned.mErrors = (CErrors) mErrors.clone();
        return cloned;
    }

    // @Method
    public String[] getPK() {
    <#if pkList?? && (pkNum > 0) >
        return PK;
    <#else>
        return null;
    </#if>
    }

<#if tableColumns??>
<#list tableColumns as column>
<#if column.dataType=="Date">
    public String get${column.code}() {
        if (${column.code} != null) {
            return fDate.getString(${column.code});
        } else {
            return null;
        }
    }

<#elseif column.dataType=="InputStream">
    public InputStream get${column.code}() {
        return ${column.code};
    }

<#else>
    public ${column.dataType} get${column.code}() {
        return ${column.code};
    }

</#if>
    public void set${column.code}(${column.dataType} a${column.code}) {
        ${column.code} = a${column.code};
    }
<#if column.dataType=="Date">

    public void set${column.code}(String a${column.code}) {
        if (a${column.code} != null && !a${column.code}.equals("")) {
            ${column.code} = fDate.getDate(a${column.code});
        } else {
            ${column.code} = null;
        }
    }
</#if>
<#if column.dataType=="int">

    public void set${column.code}(Integer a${column.code}) {
        if (a${column.code} != null) {
            ${column.code} = a${column.code}.intValue();
        }
    }

    public void set${column.code}(String a${column.code}) {
        if (a${column.code} != null && !a${column.code}.equals("")) {
            Integer tInteger = new Integer(a${column.code});
            int i = tInteger.intValue();
            ${column.code} = i;
        }
    }
</#if>
<#if column.dataType=="double">


    public void set${column.code}(Double a${column.code}) {
        if (a${column.code} != null) {
            ${column.code} = a${column.code}.doubleValue();
        }
    }

    public void set${column.code}(String a${column.code}) {
        if (a${column.code} != null && !a${column.code}.equals("")) {
            Double tDouble = new Double(a${column.code});
            ${column.code} = tDouble.doubleValue();
        }
    }
</#if>

</#list>
</#if>
    /**
    * 使用另外一个${className}Schema对象给Schema赋值
    * @param: a${className}Schema ${className}Schema
    **/
    public void setSchema(${className}Schema a${className}Schema) {
<#if tableColumns??>
    <#list tableColumns as column>
        <#if column.dataType=="Date">
        this.${column.code} = fDate.getDate(a${className}Schema.get${column.code}());
        <#else>
        this.${column.code} = a${className}Schema.get${column.code}();
        </#if>
    </#list>
</#if>
    }

    /**
     * 使用 ResultSet 中的第 i 行给 Schema 赋值
     * @param: rs ResultSet
     * @param: i int
     * @return: boolean
     */
    public boolean setSchema(ResultSet rs, int i) {
        try {
            //rs.absolute(i); // 非滚动游标
<#if tableColumns??>
    <#list tableColumns as column>
        <#if column.dataType=="float">
            this.${column.code} = rs.getFloat(${column_index + 1});

        <#elseif column.dataType=="double">
            this.${column.code} = rs.getDouble(${column_index + 1});

        <#elseif column.dataType=="int">
            this.${column.code} = rs.getInt(${column_index + 1});

        <#elseif column.dataType=="Date">
            this.${column.code} = rs.getDate(${column_index + 1});

        <#elseif column.dataType=="InputStream">
            this.${column.code} = rs.getBinaryStream(${column_index + 1});

        <#elseif column.dataType=="String">
            if (rs.getString(${column_index + 1}) == null) {
                this.${column.code} = null;
            } else {
                this.${column.code} = rs.getString(${column_index + 1}).trim();
            }

        </#if>
    </#list>
</#if>
        } catch(SQLException sqle){
            System.out.println("数据库中表${className}字段个数和Schema中的字段个数不一致，或执行db.executeQuery查询时未使用select * from tables");
            // @@错误处理
            CError tError = new CError();
            tError.moduleName = "${className}Schema";
            tError.functionName = "setSchema";
            tError.errorMessage = sqle.toString();
            this.mErrors.addOneError(tError);
            return false;
        }
        return true;
    }

    public ${className}Schema getSchema(){
        ${className}Schema a${className}Schema = new ${className}Schema();
        a${className}Schema.setSchema(this);
        return a${className}Schema;
    }

    /**
     * 数据打包，按 XML 格式打包，顺序参见<A href ={@docRoot}/dataStructure/tb.html#Prp${className}描述/A>表字段
     * @return: String 返回打包后字符串
     */
    public String encode() {
        StringBuffer strReturn = new StringBuffer(256);
<#if tableColumns??>
    <#list tableColumns as column>
        <#if column_index==(tableColumns?size-1)>
            <#assign b="true">
        <#else>
            <#assign b="false">
        </#if>
        <#if column.dataType=="String"||column.dataType=="Date">
            <#if column.dataType=="Date">
        strReturn.append(StrTool.cTrim(fDate.getString(${column.code})));
            <#else>
        strReturn.append(StrTool.cTrim(${column.code}));
            </#if>
            <#if b=="false">
        strReturn.append(SysConst.PACKAGESPILTER);

            </#if>
        <#else>
            <#if column.dataType=="InputStream">
        strReturn.append(1);
            <#else>
        strReturn.append(ChgData.chgData(${column.code}));
            </#if>
            <#if b=="false">
        strReturn.append(SysConst.PACKAGESPILTER);

            </#if>
        </#if>
    </#list>
</#if>
        return strReturn.toString();
    }

    /**
     * 数据解包，解包顺序参见<A href ={@docRoot}/dataStructure/tb.html#Prp${className}>历史记账凭证主表信息</A>表字段
     * @param: strMessage String 包含一条纪录数据的字符串
     * @return: boolean
     */
    public boolean decode(String strMessage) {
        try {
<#if tableColumns??>
    <#list tableColumns as column>
        <#if column.dataType=="String">
            ${column.code} = StrTool.getStr(StrTool.GBKToUnicode(strMessage), ${column_index+1}, SysConst.PACKAGESPILTER);

        <#elseif column.dataType=="int">
            ${column.code} = new Integer(ChgData.chgNumericStr(StrTool.getStr(strMessage, ${column_index+1}, SysConst.PACKAGESPILTER))).intValue();

        <#elseif column.dataType=="float">
            ${column.code} = new Float(ChgData.chgNumericStr(StrTool.getStr(strMessage, ${column_index+1}, SysConst.PACKAGESPILTER))).floatValue();

        <#elseif column.dataType=="double">
            ${column.code} = new Double(ChgData.chgNumericStr(StrTool.getStr(strMessage, ${column_index+1}, SysConst.PACKAGESPILTER))).doubleValue();

        <#elseif column.dataType=="Date">
            ${column.code} = fDate.getDate(StrTool.getStr(StrTool.GBKToUnicode(strMessage), ${column_index+1}, SysConst.PACKAGESPILTER));

        <#elseif column.dataType=="Integer">
            ${column.code} = new Integer(ChgData.chgNumericStr(StrTool.getStr(strMessage, ${column_index+1}, SysConst.PACKAGESPILTER)));

        <#elseif column.dataType=="Double">
            ${column.code} = new Double(ChgData.chgNumericStr(StrTool.getStr(strMessage, ${column_index+1}, SysConst.PACKAGESPILTER)));

        </#if>
    </#list>
</#if>
        } catch(NumberFormatException ex) {
            // @@错误处理
            CError tError = new CError();
            tError.moduleName = "${className}Schema";
            tError.functionName = "decode";
            tError.errorMessage = ex.toString();
            this.mErrors.addOneError(tError);
            return false;
        }
        return true;
    }

    /**
     * 取得对应传入参数的String形式的字段值
     * @param: FCode String 希望取得的字段名
     * @return: String
     * 如果没有对应的字段，返回""
     * 如果字段值为空，返回"null"
     */
    public String getV(String FCode) {
        String strReturn = "";
<#if tableColumns??>
    <#list tableColumns as column>
        if (FCode.equals("${column.code}")) {
        <#if column.dataType=="Date">
            strReturn = StrTool.GBKToUnicode(this.get${column.code}());
        <#elseif column.dataType=="int"||column.dataType=="Integer"||column.dataType=="double"||column.dataType=="Double">
            strReturn = String.valueOf(${column.code});
        <#elseif column.dataType=="String">
            strReturn = StrTool.GBKToUnicode(${column.code});
        <#else>
            strReturn = StrTool.GBKToUnicode(String.valueOf(${column.code}));
        </#if>
        }

    </#list>
</#if>
        if (strReturn.equals("")) {
            strReturn = "null";
        }
        return strReturn;
    }

    /**
     * 取得Schema中指定索引值所对应的字段值
     * @param: nFieldIndex int 指定的字段索引值
     * @return: String
     * 如果没有对应的字段，返回""
     * 如果字段值为空，返回"null"
     */
    public String getV(int nFieldIndex) {
        String strFieldValue = "";
        switch(nFieldIndex) {
<#if tableColumns??>
    <#list tableColumns as column>
            case ${column_index}:
            <#if column.dataType=="Date">
                strFieldValue = StrTool.GBKToUnicode(this.get${column.code}());
            <#elseif column.dataType=="int"||column.dataType=="Integer"||column.dataType=="double"||column.dataType=="Double">
                strFieldValue = String.valueOf(${column.code});
            <#elseif column.dataType=="String">
                strFieldValue = StrTool.GBKToUnicode(${column.code});
            <#else>
                strFieldValue = String.valueOf(${column.code}));
            </#if>
                break;
    </#list>
</#if>
            default:
                strFieldValue = "";
        }
        if (strFieldValue.equals("")) {
            strFieldValue = "null";
        }
        return strFieldValue;
    }

    /**
     * 设置对应传入参数的String形式的字段值
     * @param: FCode String 需要赋值的对象
     * @param: FValue String 要赋的值
     * @return: boolean
     */
    public boolean setV(String FCode, String FValue) {
        if (StrTool.cTrim(FCode).equals("")) {
            return false;
        }
<#if tableColumns??>
    <#list tableColumns as column>
        if (FCode.equalsIgnoreCase("${column.code}")) {
        <#if column.dataType=="Date">
            if(FValue != null && !FValue.equals("")){
                ${column.code} = fDate.getDate(FValue);
            } else {
                ${column.code} = null;
            }
        <#elseif column.dataType=="int">
            if (FValue != null && !FValue.equals("")) {
                Integer tInteger = new Integer(FValue);
                int i = tInteger.intValue();
                ${column.code} = i;
            }
        <#elseif column.dataType=="float">
            if (FValue != null && !FValue.equals("")) {
                Float tFloat = new Float(FValue);
                float f = tFloat.floatValue();
                ${column.code} = f;
            }
        <#elseif column.dataType=="String">
            if (FValue != null && !FValue.equals("")) {
                ${column.code} = FValue.trim();
            } else {
                ${column.code} = null;
            }
        <#elseif column.dataType=="double">
            if (FValue != null && !FValue.equals("")) {
                Double tDouble = new Double(FValue);
                double d = tDouble.doubleValue();
                ${column.code} = d;
            }
        <#elseif column.dataType=="Double">
            if (FValue != null && !FValue.equals("")) {
                ${column.code} = new Double(FValue);
            } else {
                ${column.code} = null;
            }
        <#elseif column.dataType=="Integer">
            if (FValue != null && !FValue.equals("")) {
                ${column.code} = new Integer(FValue);
            } else {
                ${column.code} = null;
            }
        </#if>
        }
    </#list>
</#if>
        return true;
    }

    public boolean equals(Object otherObject){
        if (this == otherObject) {
            return true;
        }
        if (otherObject == null) {
            return false;
        }
        if (getClass() != otherObject.getClass()) {
            return false;
        }
        ${className}Schema other = (${className}Schema) otherObject;
        return
<#if tableColumns??>
    <#list tableColumns as column>
        <#if column_index!=0>
            <#assign relation="&&">
        <#else>
            <#assign relation="">
        </#if>
        <#if column_index==(tableColumns?size-1)>
            <#assign endChar=";">
        <#else>
            <#assign endChar="">
        </#if>
        <#if column.dataType=="Date">
            ${relation} (${column.code} == null ? other.get${column.code}() == null : fDate.getString(${column.code}).equals(other.get${column.code}()))${endChar}
        <#elseif column.dataType=="int"||column.dataType=="double"||column.dataType=="float">
            ${relation} ${column.code} == other.get${column.code}()${endChar}
        <#elseif column.dataType=="String"||column.dataType=="Double"||column.dataType=="Integer">
            ${relation} (${column.code} == null ? other.get${column.code}() == null : ${column.code}.equals(other.get${column.code}()))${endChar}
        <#elseif column.dataType=="InputStream">
            ${endChar}
        </#if>
    </#list>
</#if>
    }

    /**
     * 取得Schema拥有字段的数量
     * @return: int
     */
    public int getFieldCount() {
        return FIELDNUM;
    }

    /**
     * 取得Schema中指定字段名所对应的索引值
     * 如果没有对应的字段，返回-1
     * @param: strFieldName String
     * @return: int
     */
    public int getFieldIndex(String strFieldName) {
<#if tableColumns??>
    <#list tableColumns as column>
        if (strFieldName.equals("${column.code}")) {
            return ${column_index};
        }
    </#list>
</#if>
        return -1;
    }

    /**
     * 取得Schema中指定索引值所对应的字段名
     * 如果没有对应的字段，返回""
     * @param: nFieldIndex int
     * @return: String
     */
    public String getFieldName(int nFieldIndex) {
        String strFieldName = "";
        switch(nFieldIndex) {
    <#if tableColumns??>
        <#list tableColumns as column>
            case ${column_index}:
                strFieldName = "${column.code}";
                break;
        </#list>
    </#if>
            default:
                strFieldName = "";
        }
        return strFieldName;
    }

    /**
     * 取得Schema中指定字段名所对应的字段类型
     * 如果没有对应的字段，返回Schema.TYPE_NOFOUND
     * @param: strFieldName String
     * @return: int
     */
    public int getFieldType(String strFieldName) {
        if (strFieldName == null) {
            return Schema.TYPE_NOFOUND;
        }
<#if tableColumns??>
    <#list tableColumns as column>
        if (strFieldName.equals("${column.code}")) {
        <#if column.dataType=="String">
            return Schema.TYPE_STRING;
        <#elseif column.dataType=="Date">
            return Schema.TYPE_DATE;
        <#elseif column.dataType=="int">
            return Schema.TYPE_INT;
        <#elseif column.dataType=="float">
            return Schema.TYPE_FLOAT;
        <#elseif column.dataType=="double">
            return Schema.TYPE_DOUBLE;
        <#elseif column.dataType=="Integer">
            return Schema.TYPE_INT;
        <#elseif column.dataType=="Double">
            return Schema.TYPE_DOUBLE;
        <#else>
            return Schema.TYPE_NOFOUND;
        </#if>
        }
    </#list>
</#if>
        return Schema.TYPE_NOFOUND;
    }

    /**
     * 取得Schema中指定索引值所对应的字段类型
     * 如果没有对应的字段，返回Schema.TYPE_NOFOUND
     * @param: nFieldIndex int
     * @return: int
     */
    public int getFieldType(int nFieldIndex) {
        int nFieldType = Schema.TYPE_NOFOUND;
        switch(nFieldIndex) {
    <#if tableColumns??>
        <#list tableColumns as column>
            case ${column_index}:
            <#if column.dataType=="String">
                nFieldType = Schema.TYPE_STRING;
                break;
            <#elseif column.dataType=="Date">
                nFieldType = Schema.TYPE_DATE;
                break;
            <#elseif column.dataType=="int">
                nFieldType = Schema.TYPE_INT;
                break;
            <#elseif column.dataType=="float">
                nFieldType = Schema.TYPE_FLOAT;
                break;
            <#elseif column.dataType=="double">
                nFieldType = Schema.TYPE_DOUBLE;
                break;
            <#elseif column.dataType=="Integer">
                nFieldType = Schema.TYPE_INT;
                break;
            <#elseif column.dataType=="Double">
                nFieldType = Schema.TYPE_DOUBLE;
                break;
            <#else>
                nFieldType = Schema.TYPE_NOFOUND;
                break;
            </#if>
        </#list>
            default:
                nFieldType = Schema.TYPE_NOFOUND;
    </#if>
        }
        return nFieldType;
    }
}
