package com.wxf.pdm;

import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * @author weixf
 * @since 2022-01-21
 */
@Component
public class PDM {
    private String Id;
    private String Name;
    private String Code;
    private String DBMSCode;
    private String DBMSName;
    private ArrayList<PDMPhysicalDiagram> PhysicalDiagrams = new ArrayList<>();
    private int PhysicalDiagramNum = 0;
    private ArrayList<PDMUser> Users = new ArrayList<>(3);
    private int UserNum = 0;
    private ArrayList<PDMTable> Tables = new ArrayList<>(20);
    private int TabNum = 0;
    private ArrayList<PDMReference> References = new ArrayList();
    private int RefNum = 0;

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

    public String getDBMSCode() {
        return DBMSCode;
    }

    public void setDBMSCode(String DBMSCode) {
        this.DBMSCode = DBMSCode;
    }

    public String getDBMSName() {
        return DBMSName;
    }

    public void setDBMSName(String DBMSName) {
        this.DBMSName = DBMSName;
    }

    public void addPhysicalDiagram(PDMPhysicalDiagram PhysicalDiagram) {
        PhysicalDiagrams.add(PhysicalDiagram);
        PhysicalDiagramNum++;
    }

    public int getPhysicalDiagramNum() {
        return PhysicalDiagramNum;
    }

    public PDMPhysicalDiagram getPhysicalDiagram(int i) {
        return PhysicalDiagrams.get(i);
    }

    public void addUser(PDMUser User) {
        Users.add(User);
        UserNum++;
    }

    public int getUserNum() {
        return UserNum;
    }

    public PDMUser getUser(int i) {
        return Users.get(i);
    }

    public synchronized void addTable(PDMTable table) {
        Tables.add(table);
        TabNum++;
    }

    public int getTabNum() {
        return TabNum;
    }

    public PDMTable getTable(int i) {
        return Tables.get(i);
    }

    public void addReference(PDMReference table) {
        References.add(table);
        RefNum++;
    }

    public int getRefNum() {
        return RefNum;
    }

    public PDMReference getReference(int i) {
        return (PDMReference) References.get(i);
    }
}
