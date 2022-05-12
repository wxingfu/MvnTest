package com.wxf.rpc.entity.emp;

import lombok.Data;

import com.wxf.rpc.entity.common.State;
import com.wxf.rpc.entity.common.PageAttri;

import java.util.List;

@Data
public class PageEmployee {

    private PageAttri PageAttri;
    private State State;
    private List<Employee> Records;
}
