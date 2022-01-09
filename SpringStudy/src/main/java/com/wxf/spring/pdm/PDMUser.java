package com.wxf.spring.pdm;

import java.util.ArrayList;

/**
 * @author weixf
 * @since 2022-01-08
 */
public class PDMUser {


    private String id;
    private String name;
    private String code;
    private ArrayList<PDMTable> tables = new ArrayList<PDMTable>();

    public PDMUser() {
    }

    public PDMUser(String id, String name, String code, ArrayList<PDMTable> tables) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.tables = tables;
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

    public ArrayList<PDMTable> getTables() {
        return tables;
    }

    public void setTables(ArrayList<PDMTable> tables) {
        this.tables = tables;
    }

    @Override
    public String toString() {
        return "PDMUser{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", tables=" + tables +
                '}';
    }
}
