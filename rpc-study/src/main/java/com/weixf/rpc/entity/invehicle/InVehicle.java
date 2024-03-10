package com.weixf.rpc.entity.invehicle;

import lombok.Data;

import java.util.Date;

@Data
public class InVehicle {

    private long ID;
    private String ParkingId;
    private String TokenNo;
    private int TokenType;
    private String ParkId;
    private String TcmId;
    private String TcmName;
    private String StaffNo;
    private String StaffName;
    private String RegPlate;
    private String InAutoPlate;
    private String VehicleBand;
    private String VehicleColor;
    private String VehicleCategory;
    private String PlateColor;
    private String InLaneId;
    private String InLaneName;
    private Date InTime;
    private String InPicture;
    private String InPicture2;
    private String InPictureStaff;
    private String InOperatorId;
    private String InOperatorName;
    private int InType;
    private int InFlag;
    private String LotFullRemark;
    private int State;
    private int PayMark;
    private Date LastChargeTime;
    private int GroupLotState;
    private String ReservationNo;
    private String InRemark;
    private String TerminalId;
    private String TerminalName;
    private String Province;
    private String Gid;
    private String Rid;
    // private String NoSenseMark;
}
