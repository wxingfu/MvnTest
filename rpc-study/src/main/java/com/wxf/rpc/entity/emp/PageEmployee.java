package com.wxf.rpc.entity.emp;

import com.wxf.rpc.entity.common.PageAttri;
import com.wxf.rpc.entity.common.State;
import lombok.Data;

import java.util.List;

@Data
public class PageEmployee {

    private PageAttri PageAttri;
    private State State;
    private List<Employee> Records;
}
