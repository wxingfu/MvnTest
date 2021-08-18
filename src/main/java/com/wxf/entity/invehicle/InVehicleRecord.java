package com.wxf.entity.invehicle;

import lombok.Data;

import java.util.List;

@Data
public class InVehicleRecord {

    private com.wxf.entity.common.State State;
    private InVehicle Model;
    private List<InVehicle> Models;
}
