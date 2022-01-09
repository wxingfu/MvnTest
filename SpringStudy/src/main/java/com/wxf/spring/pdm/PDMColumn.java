package com.wxf.spring.pdm;

/**
 * @author weixf
 * @since 2022-01-08
 */
public class PDMColumn {


    private String id;
    private String name;
    private String code;
    private String dataType; // 数据类型
    private int length; // 数据长度
    private int precision; // 数据精度
    private int mandatory = 0; // 字段是否为空，默认可空
    private String defaultValue; // 字段默认值
    private String lowValue;
    private String highValue;
    private String comment;
    private PDMTable table;

    public PDMColumn() {
    }

    public PDMColumn(String id, String name, String code, String dataType, int length, int precision, int mandatory, String defaultValue, String lowValue, String highValue, String comment, PDMTable table) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.dataType = dataType;
        this.length = length;
        this.precision = precision;
        this.mandatory = mandatory;
        this.defaultValue = defaultValue;
        this.lowValue = lowValue;
        this.highValue = highValue;
        this.comment = comment;
        this.table = table;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getPrecision() {
        return precision;
    }

    public void setPrecision(int precision) {
        this.precision = precision;
    }

    public int getMandatory() {
        return mandatory;
    }

    public void setMandatory(int mandatory) {
        this.mandatory = mandatory;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getLowValue() {
        return lowValue;
    }

    public void setLowValue(String lowValue) {
        this.lowValue = lowValue;
    }

    public String getHighValue() {
        return highValue;
    }

    public void setHighValue(String highValue) {
        this.highValue = highValue;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public PDMTable getTable() {
        return table;
    }

    public void setTable(PDMTable table) {
        this.table = table;
    }

    @Override
    public String toString() {
        return "PDMColumn{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", dataType='" + dataType + '\'' +
                ", length=" + length +
                ", precision=" + precision +
                ", mandatory=" + mandatory +
                ", defaultValue='" + defaultValue + '\'' +
                ", lowValue='" + lowValue + '\'' +
                ", highValue='" + highValue + '\'' +
                ", comment='" + comment + '\'' +
                ", table=" + table +
                '}';
    }
}
