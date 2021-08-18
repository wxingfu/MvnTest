package com.wxf.entity.outvehicle;

import lombok.Data;

import java.util.List;

@Data
public class PageOutVehicleRecord {

    private com.wxf.entity.common.PageAttri PageAttri;
    private com.wxf.entity.common.State State;
    private List<OutVehicle> Records;

}
