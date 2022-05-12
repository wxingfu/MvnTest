package com.wxf.rpc.entity.invehicle;

import lombok.Data;

import com.wxf.rpc.entity.common.State;
import com.wxf.rpc.entity.common.PageAttri;
import java.util.List;

@Data
public class PageInVehicleRecord {

    private PageAttri PageAttri;
    private State State;
    private List<InVehicle> Records;
}
