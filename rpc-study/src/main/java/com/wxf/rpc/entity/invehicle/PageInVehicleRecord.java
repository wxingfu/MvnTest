package com.wxf.rpc.entity.invehicle;

import com.wxf.rpc.entity.common.PageAttri;
import com.wxf.rpc.entity.common.State;
import lombok.Data;

import java.util.List;

@Data
public class PageInVehicleRecord {

    private PageAttri PageAttri;
    private State State;
    private List<InVehicle> Records;
}
