package com.wxf.rpc.entity.dept;

import lombok.Data;

@Data
public class Department {

    private String ParentId;
    private String Gid;
    private int ID;
    private String Rid;
    private String Name;
    private String Remark;
}
