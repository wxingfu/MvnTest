package com.wxf.rpc.entity.outvehicle;

import com.wxf.rpc.entity.common.PageAttri;
import com.wxf.rpc.entity.common.State;
import lombok.Data;

import java.util.List;

@Data
public class PageOutVehicleRecord {

    private PageAttri PageAttri;
    private State State;
    private List<OutVehicle> Records;

}
