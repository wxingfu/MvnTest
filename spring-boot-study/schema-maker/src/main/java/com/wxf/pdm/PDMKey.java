package com.wxf.pdm;

import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * @author weixf
 * @since 2022-01-21
 */
@Component
public class PDMKey {

    private String Id;
    private String Name;
    private String Code;
    private String ConstraintName;
    private ArrayList Columns_Ref = new ArrayList(5); //使用Column中的ID
    private int ColNum = 0;

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

    public String getConstraintName() {
        return ConstraintName;
    }

    public void setConstraintName(String constraint) {
        ConstraintName = constraint;
    }

    public void addColumn(String col) {
        Columns_Ref.add(col);
        ColNum++;
    }

    public int getColNum() {
        return ColNum;
    }

    public String getColumn(int i) {
        return (String) Columns_Ref.get(i);
    }

}
