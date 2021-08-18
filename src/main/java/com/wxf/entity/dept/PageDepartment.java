package com.wxf.entity.dept;

import lombok.Data;

import java.util.List;

@Data
public class PageDepartment {

    private com.wxf.entity.common.State State;
    private Department Model;
    private List<Department> Models;
}
