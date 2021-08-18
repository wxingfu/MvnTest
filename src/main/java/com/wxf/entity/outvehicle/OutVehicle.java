package com.wxf.entity.outvehicle;

import lombok.Data;

import java.util.Date;

@Data
public class OutVehicle {

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
    private String InLaneName;
    private Date InTime;
    private String InPicture;
    private String InPicture2;
    private String InPictureStaff;
    private String InOperatorId;
    private String InOperatorName;
    private int InType;
    private int InFlag;
    private String InLaneId;
    private String LotFullRemark;
    private int GroupLotState;
    private String ReservationNo;
    private String InTerminalId;
    private String InTerminalName;
    private String InRemark;
    private String VehicleColor;
    private String VehicleCategory;
    private String VehicleBand;
    private String PlateColor;
    private String OutAutoPlate;
    private String OutLaneName;
    private Date OutTime;
    private String OutPicture;
    private String OutPicture2;
    private String OutPictureStaff;
    private String OutOperatorId;
    private String OutOperatorName;
    private int OutType;
    private int OutFlag;
    private String OutLaneId;
    private String OutRemark;
    private String StayLasts;
    private String TerminalId;
    private String TerminalName;
    private int TranAmount;
    private int AccountPayAmount;
    private int CashAmount;
    private int FreeAmount;
    private int DeductedAmount;
    private int DeductedHours;
    private int DeductedHoursAmount;
    private String Province;
    private String Gid;
    private String Rid;
}
