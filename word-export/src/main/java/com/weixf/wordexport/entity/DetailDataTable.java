package com.weixf.wordexport.entity;

import com.deepoove.poi.expression.Name;

public class DetailDataTable {

    /**
     * 动态表格数据绑定需要的注解
     */
    @Name("detail_table")
    private DetailData detailData;

    private int total;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public DetailData getDetailData() {
        return detailData;
    }

    public void setDetailData(DetailData detailData) {
        this.detailData = detailData;
    }
}
