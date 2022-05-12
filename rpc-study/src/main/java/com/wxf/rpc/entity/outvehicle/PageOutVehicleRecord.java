package com.wxf.rpc.entity.outvehicle;

import lombok.Data;
import com.wxf.rpc.entity.common.State;
import com.wxf.rpc.entity.common.PageAttri;

import java.util.List;

@Data
public class PageOutVehicleRecord {

    private PageAttri PageAttri;
    private State State;
    private List<OutVehicle> Records;

}
