package com.wxf.spring.pdm;

import java.util.ArrayList;

/**
 * @author weixf
 * @since 2022-01-08
 */
public class PDMKey {


    private String id;
    private String name;
    private String code;
    private String constraintName;
    private ArrayList<PDMColumn> columns = new ArrayList<PDMColumn>();

    public PDMKey() {
    }

    public PDMKey(String id, String name, String code, String constraintName, ArrayList<PDMColumn> columns) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.constraintName = constraintName;
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

    public String getConstraintName() {
        return constraintName;
    }

    public void setConstraintName(String constraintName) {
        this.constraintName = constraintName;
    }

    public ArrayList<PDMColumn> getColumns() {
        return columns;
    }

    public void setColumns(ArrayList<PDMColumn> columns) {
        this.columns = columns;
    }

    @Override
    public String toString() {
        return "PDMKey{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", constraintName='" + constraintName + '\'' +
                ", columns=" + columns +
                '}';
    }

    public void addColumn(PDMColumn column) {
        columns.add(column);
    }

}
