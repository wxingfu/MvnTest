package com.wxf.entity.emp;

import lombok.Data;

import java.util.List;

@Data
public class PageEmployee {

    private com.wxf.entity.common.PageAttri PageAttri;
    private com.wxf.entity.common.State State;
    private List<Employee> Records;
}
