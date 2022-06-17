package com.wxf.table;


import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * @author weixf
 * @since 2022-01-21
 */
@Component
public class Schema {

    private String Name;
    private String Code;
    private String DBMSCode;
    private String DBMSName;
    private ArrayList<Table> Tables = new ArrayList<>();
    private int TabNum = 0;

    public Schema() {
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

    public void addTable(Table table) {
        Tables.add(table);
        TabNum++;
    }

    public int getTabNum() {
        return TabNum;
    }

    public Table getTable(int i) {
        return (Table) Tables.get(i);
    }

    public String getDBMSCode() {
        return DBMSCode;
    }

    public String getDBMSName() {
        return DBMSName;
    }

    public void setDBMSCode(String DBMSCode) {
        this.DBMSCode = DBMSCode;
    }

    public void setDBMSName(String DBMSName) {
        this.DBMSName = DBMSName;
    }
}
