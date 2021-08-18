package com.wxf.entity.invehicle;

import lombok.Data;

import java.util.List;

@Data
public class PageInVehicleRecord {

    private com.wxf.entity.common.PageAttri PageAttri;
    private com.wxf.entity.common.State State;
    private List<InVehicle> Records;
}
