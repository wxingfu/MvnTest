package com.weixf.rpc.entity.dms;

import lombok.Data;

import java.util.Date;

@Data
public class DmsOpenRecord {

    private long ID;
    private String TokenId;
    private int TokenType;
    private int Flag;
    private String TcmId;
    private String TcmName;
    private String StaffId;
    private String StaffNo;
    private String StaffName;
    private String OrganizationNo;
    private String OrganizationName;
    private String DevTypeCode;
    private int DevNo;
    private int DoorNo;
    private String DevName;
    private Date OpenDate;
    private String OpenPicNo;
    private String WorkStationId;
    private String WorkStationName;
    private Date OperateDate;
    private String Remark;
    private String Gid;
    private String Rid;
    private int BodyTemperature;
    private int ComeGo;
}
