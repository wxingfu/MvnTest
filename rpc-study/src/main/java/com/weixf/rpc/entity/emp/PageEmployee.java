package com.weixf.rpc.entity.emp;

import com.weixf.rpc.entity.common.PageAttri;
import com.weixf.rpc.entity.common.State;
import lombok.Data;

import java.util.List;

@Data
public class PageEmployee {

    private PageAttri PageAttri;
    private State State;
    private List<Employee> Records;
}
