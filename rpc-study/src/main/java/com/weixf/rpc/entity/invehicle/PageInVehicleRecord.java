package com.weixf.rpc.entity.invehicle;

import com.weixf.rpc.entity.common.PageAttri;
import com.weixf.rpc.entity.common.State;
import lombok.Data;

import java.util.List;

@Data
public class PageInVehicleRecord {

    private PageAttri PageAttri;
    private State State;
    private List<InVehicle> Records;
}
