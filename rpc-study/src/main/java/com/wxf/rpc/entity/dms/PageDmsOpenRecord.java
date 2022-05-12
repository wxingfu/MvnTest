package com.wxf.rpc.entity.dms;

import lombok.Data;

import com.wxf.rpc.entity.common.State;
import com.wxf.rpc.entity.common.PageAttri;
import java.util.List;

@Data
public class PageDmsOpenRecord {

    private PageAttri PageAttri;
    private State State;
    private List<DmsOpenRecord> Records;
}
