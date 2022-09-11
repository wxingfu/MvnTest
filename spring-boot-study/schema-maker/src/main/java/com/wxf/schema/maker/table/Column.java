package com.wxf.schema.maker.table;

import org.springframework.stereotype.Component;

/**
 * @author weixf
 * @since 2022-01-21
 */
@Component
public class Column {
    private String Name;
    private String Code;
    private String DataType; //Java数据类型
    private int Length; //数据长度
    private int Precision; //数据精度
    private int Nullable = 1; //默认可空
    private String DefaultValue; //字段默认值

    private int DBSqlType; // 字段的SQL类型(数据库)，主要是针对ps的null值处理
    private int DBLength; // 字段的长度(数据库)，主要是针对char类型的ps处理

    private String PDMDataType; //字段类型(PDM)

    public Column() {
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getDataType() {
        return DataType;
    }

    public void setDataType(String datatype) {
        DataType = datatype;
    }

    public int getLength() {
        return Length;
    }

    public void setLength(int length) {
        Length = length;
    }

    public int getPrecision() {
        return Precision;
    }

    public void setPrecision(int precision) {
        Precision = precision;
    }

    public int getNullable() {
        return Nullable;
    }

    public void setNullable(int nullable) {
        Nullable = nullable;
    }

    public String getDefaultValue() {
        return DefaultValue;
    }

    public void setDefaultValue(String defaultvalue) {
        DefaultValue = defaultvalue;
    }

    public int getDBSqlType() {
        return DBSqlType;
    }

    public void setDBSqlType(int dbSqlType) {
        this.DBSqlType = dbSqlType;
    }

    public int getDBLength() {
        return DBLength;
    }

    public void setDBLength(int DBLength) {
        this.DBLength = DBLength;
    }

    public String getPDMDataType() {
        return PDMDataType;
    }

    public void setPDMDataType(String PDMDataType) {
        this.PDMDataType = PDMDataType;
    }
}