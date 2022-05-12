package com.wxf.utils;

import com.wxf.utils.poi.ExcelUtils;
import com.wxf.utils.poi.dto.SlrEmpSalary;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MyTest {

    @Test
    public void getExcelData() throws Exception {
        //导出数据
        List<SlrEmpSalary> dataList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            SlrEmpSalary slrEmpSalary = new SlrEmpSalary();
            slrEmpSalary.setEmployeeName("aaa");
            slrEmpSalary.setEmployeeCode("190378091");
            slrEmpSalary.setSeqNumber(123 + i);
            dataList.add(slrEmpSalary);
        }

        //导出
        File file = new File("D:\\MyWork\\files\\#2326\\ttttt.xls");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        ExcelUtils.exportExcel(fileOutputStream, dataList, SlrEmpSalary.class, null, "aaaaa");
    }

    @Test
    public void ttt() throws MalformedURLException {

        String fileUrl = "http://kutta-risk-manage-1254024480.cos.ap-beijing.myqcloud.com/cfcd208495d565ef66e7dff9f98764da/1649303609484340706198201014478.jpeg?sign=q-sign-algorithm%3Dsha1%26q-ak%3DAKID0oMegRP8anjOnwpQkna5JL6apoWx70cx%26q-sign-time%3D1649303616%3B1649390016%26q-key-time%3D1649303616%3B1649390016%26q-header-list%3Dhost%26q-url-param-list%3D%26q-signature%3D6308bfc36026035781c4d02ada6a53a11ca79faa";
        String path = new URL(fileUrl).getPath();
        String type = path.substring(path.lastIndexOf(".") + 1);
        System.out.println(type);

    }
}
