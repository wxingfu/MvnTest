package com.wxf.rpc.entity.outvehicle;

import com.wxf.rpc.entity.common.State;
import lombok.Data;

import java.util.List;

@Data
public class OutVehicleRecord {

    private State State;
    private OutVehicle Model;
    private List<OutVehicle> Models;
}
