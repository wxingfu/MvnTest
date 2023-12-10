package com.wxf.rpc.entity.dms;

import com.wxf.rpc.entity.common.PageAttri;
import com.wxf.rpc.entity.common.State;
import lombok.Data;

import java.util.List;

@Data
public class PageDmsOpenRecord {

    private PageAttri PageAttri;
    private State State;
    private List<DmsOpenRecord> Records;
}
