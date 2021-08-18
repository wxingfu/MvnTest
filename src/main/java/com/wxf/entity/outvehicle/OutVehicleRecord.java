package com.wxf.entity.outvehicle;

import lombok.Data;

import java.util.List;

@Data
public class OutVehicleRecord {

    private com.wxf.entity.common.State State;
    private OutVehicle Model;
    private List<OutVehicle> Models;
}
