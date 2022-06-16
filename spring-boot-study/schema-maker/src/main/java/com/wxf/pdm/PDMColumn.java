package com.wxf.pdm;

import org.springframework.stereotype.Component;

/**
 * @author weixf
 * @since 2022-01-21
 */
@Component
public class PDMColumn {

    private String Id;
    private String Name;
    private String Code;
    private String DataType; //数据类型
    private int Length; //数据长度
    private int Precision; //数据精度
    private int Mandatory = 0; //字段是否为空，默认可空
    private String DefaultValue; //字段默认值
    private String LowValue;
    private String HighValue;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
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

    public int getMandatory() {
        return Mandatory;
    }

    public void setMandatory(int mandatory) {
        Mandatory = mandatory;
    }

    public String getDefaultValue() {
        return DefaultValue;
    }

    public void setDefaultValue(String defaultvalue) {
        DefaultValue = defaultvalue;
    }

    public String getLowValue() {
        return LowValue;
    }

    public void setLowValue(String LowValue) {
        this.LowValue = LowValue;
    }

    public String getHighValue() {
        return HighValue;
    }

    public void setHighValue(String HighValue) {
        this.HighValue = HighValue;
    }
}
