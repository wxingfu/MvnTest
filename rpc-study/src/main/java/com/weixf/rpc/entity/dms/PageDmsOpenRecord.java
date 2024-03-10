package com.weixf.rpc.entity.dms;

import com.weixf.rpc.entity.common.PageAttri;
import com.weixf.rpc.entity.common.State;
import lombok.Data;

import java.util.List;

@Data
public class PageDmsOpenRecord {

    private PageAttri PageAttri;
    private State State;
    private List<DmsOpenRecord> Records;
}
