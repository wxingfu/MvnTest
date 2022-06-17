package com.wxf.pdm;

import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * @author weixf
 * @since 2022-01-21
 */
@Component
public class PDMPhysicalDiagram {

    private String Id;
    private String Name;
    private String Code;
    private ArrayList<String> Tables = new ArrayList<>(20);
    private int TabNum = 0;

    public PDMPhysicalDiagram() {
    }

    public String getId()
    {
        return Id;
    }

    public void setId(String id)
    {
        Id = id;
    }

    public String getName()
    {
        return Name;
    }

    public void setName(String name)
    {
        Name = name;
    }

    public String getCode()
    {
        return Code;
    }

    public void setCode(String code)
    {
        Code = code;
    }

    public void addTable(String table)
    {
        Tables.add(table);
        TabNum++;
    }

    public int getTabNum()
    {
        return TabNum;
    }

    public String getTable(int i)
    {
        return(String)Tables.get(i);
    }

}
