package com.wxf.utils.poi.dto;

import com.wxf.utils.poi.annotation.EnableExport;
import com.wxf.utils.poi.annotation.EnableExportField;
import com.wxf.utils.poi.annotation.ImportIndex;
import com.wxf.utils.poi.enums.ColorEnum;

import java.io.Serializable;

@EnableExport(fileName = "")
public class SlrEmpSalary implements Serializable {

    @ImportIndex(index = 0)
    @EnableExportField(colName = "序号", colWidth = 80)
    private int seqNumber;

    @ImportIndex(index = 1)
    @EnableExportField(colName = "员工编号", colWidth = 160, cellColor = ColorEnum.RED)
    private String employeeCode;

    @ImportIndex(index = 2)
    @EnableExportField(colName = "员工姓名", colWidth = 160)
    private String employeeName;


    public int getSeqNumber() {
        return seqNumber;
    }

    public void setSeqNumber(int seqNumber) {
        this.seqNumber = seqNumber;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
}
