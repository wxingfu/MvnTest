package com.weixf.rpc.entity.invehicle;

import com.weixf.rpc.entity.common.State;
import lombok.Data;

import java.util.List;

@Data
public class InVehicleRecord {

    private State State;
    private InVehicle Model;
    private List<InVehicle> Models;
}
