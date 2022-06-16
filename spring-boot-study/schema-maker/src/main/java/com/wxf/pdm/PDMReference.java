package com.wxf.pdm;

import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * @author weixf
 * @since 2022-01-21
 */
@Component
public class PDMReference {

    private String Id;
    private String Name;
    private String Code;
    private String ConstraintName;
    private String ParentTable_Ref; //Object1
    private String ParentKey_Ref;
    private String ChildTable_Ref; //Object2
    private int UpdateConstraint = 1;
    private int DeleteConstraint = 1;
    private String ImplementationType;
    private ArrayList Joins = new ArrayList(3);
    private int Join_Num = 0;

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

    public void setConstraintName(String name) {
        ConstraintName = name;
    }

    public String getParentTable() {
        return ParentTable_Ref;
    }

    public void setParentTable(String code) {
        ParentTable_Ref = code;
    }

    public String getParentKey() {
        return ParentKey_Ref;
    }

    public void setParentKey(String code) {
        ParentKey_Ref = code;
    }

    public String getChildTable() {
        return ChildTable_Ref;
    }

    public void setChildTable(String code) {
        ChildTable_Ref = code;
    }

    public String getImplementationType() {
        return ImplementationType;
    }

    public void setImplementationType(String code) {
        ImplementationType = code;
    }

    public void setUpdateConstraint(int code) {
        UpdateConstraint = code;
    }

    public int getUpdateConstraint() {
        return UpdateConstraint;
    }

    public void setDeleteConstraint(int code) {
        DeleteConstraint = code;
    }

    public int getDeleteConstraint() {
        return DeleteConstraint;
    }

    public void addJoin(PDMReferenceJoin col) {
        Joins.add(col);
        Join_Num++;
    }

    public int getJoinNum() {
        return Join_Num;
    }

    public PDMReferenceJoin getJoin(int i) {
        return (PDMReferenceJoin) Joins.get(i);
    }
}
