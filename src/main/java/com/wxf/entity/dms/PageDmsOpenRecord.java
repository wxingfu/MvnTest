package com.wxf.entity.dms;

import lombok.Data;

import java.util.List;

@Data
public class PageDmsOpenRecord {

    private com.wxf.entity.common.PageAttri PageAttri;
    private com.wxf.entity.common.State State;
    private List<DmsOpenRecord> Records;
}
