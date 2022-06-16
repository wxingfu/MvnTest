package com.wxf.pdm;

import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * @author weixf
 * @since 2022-01-21
 */
@Component
public class PDMTable {

    public String Id;
    private String Name;
    private String Code;
    private ArrayList Columns = new ArrayList(20);
    private int ColNum = 0;
    private ArrayList Keys = new ArrayList(3);
    private int KeyNum = 0;
    private String PrimaryKey_Ref; //Keys中的Key的Id
    private String User;

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

    public String getPrimaryKey() {
        return PrimaryKey_Ref;
    }

    public void setPrimaryKey(String pk) {
        PrimaryKey_Ref = pk;
    }

    public void addColumn(PDMColumn col) {
        Columns.add(col);
        ColNum++;
    }

    public PDMColumn getColumn(int i) {
        return (PDMColumn) Columns.get(i);
    }

    public int getColNum() {
        return ColNum;
    }

    public void addKey(PDMKey key) {
        Keys.add(key);
        KeyNum++;
    }

    public PDMKey getKey(int i) {
        return (PDMKey) Keys.get(i);
    }

    public int getKeyNum() {
        return KeyNum;
    }

    public String getUser() {
        return User;
    }

    public void setUser(String User) {
        this.User = User;
    }
}
