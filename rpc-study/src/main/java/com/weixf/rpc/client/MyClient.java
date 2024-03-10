package com.weixf.rpc.client;

import com.dtflys.forest.annotation.BaseRequest;
import com.dtflys.forest.annotation.Body;
import com.dtflys.forest.annotation.PostRequest;
import com.weixf.rpc.entity.common.ReqParams;
import com.weixf.rpc.entity.dept.PageDepartment;
import com.weixf.rpc.entity.dms.PageDmsOpenRecord;
import com.weixf.rpc.entity.emp.PageEmployee;
import com.weixf.rpc.entity.invehicle.InVehicleRecord;
import com.weixf.rpc.entity.outvehicle.OutVehicleRecord;


@BaseRequest(
        baseURL = "http://localhost:60009/api",
        contentType = "application/json;charset=utf-8"
)
public interface MyClient {


    @PostRequest(url = "/Staff/GetByCustom")
    PageEmployee getEmployees(@Body ReqParams param);


    @PostRequest(url = "/Organization/GetByFunc")
    PageDepartment getDepartments(@Body String param);

    @PostRequest(url = "/Organization/GetByFunc")
    PageDepartment getDepartmentById(@Body String param);


    // @PostRequest(url = "/InVehicle/GetByCustom")
    // PageInVehicleRecord pageInVehicleRecord(@JSONBody ReqParams param);

    @PostRequest(url = "/InVehicleRecord/GetByFunc")
    InVehicleRecord getInVehicleRecord(@Body String param);


    // @PostRequest(url = "/OutVehicle/GetByCustom")
    // PageOutVehicleRecord pageOutVehicleRecord(@JSONBody ReqParams param);

    @PostRequest(url = "/OutVehicle/GetByFunc")
    OutVehicleRecord getOutVehicleRecord(@Body String param);


    @PostRequest(url = "/DmsOpenRecord/GetByCustom")
    PageDmsOpenRecord pageDmsOpenRecord(@Body ReqParams param);
}
