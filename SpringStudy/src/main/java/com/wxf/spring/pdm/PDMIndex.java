package com.wxf.spring.pdm;

import java.util.ArrayList;

/**
 * @author weixf
 * @since 2022-01-08
 */
public class PDMIndex {


    private String id;
    private String name;
    private String code;
    private ArrayList<PDMColumn> columns = new ArrayList<PDMColumn>();

    public PDMIndex() {
    }

    public PDMIndex(String id, String name, String code, ArrayList<PDMColumn> columns) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.columns = columns;
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

    public ArrayList<PDMColumn> getColumns() {
        return columns;
    }

    public void setColumns(ArrayList<PDMColumn> columns) {
        this.columns = columns;
    }

    @Override
    public String toString() {
        return "PDMIndex{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", columns=" + columns +
                '}';
    }

    public void addColumn(PDMColumn column) {
        columns.add(column);
    }
}
