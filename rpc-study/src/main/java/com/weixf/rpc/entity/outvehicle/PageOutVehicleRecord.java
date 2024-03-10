package com.weixf.rpc.entity.outvehicle;

import com.weixf.rpc.entity.common.PageAttri;
import com.weixf.rpc.entity.common.State;
import lombok.Data;

import java.util.List;

@Data
public class PageOutVehicleRecord {

    private PageAttri PageAttri;
    private State State;
    private List<OutVehicle> Records;

}
