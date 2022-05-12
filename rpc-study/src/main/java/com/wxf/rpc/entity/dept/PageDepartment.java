package com.wxf.rpc.entity.dept;

import com.wxf.rpc.entity.common.State;
import lombok.Data;

import java.util.List;

@Data
public class PageDepartment {

    private State State;
    private Department Model;
    private List<Department> Models;
}
