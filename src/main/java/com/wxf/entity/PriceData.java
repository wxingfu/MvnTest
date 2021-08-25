package com.wxf.entity;

import com.deepoove.poi.data.RowRenderData;

import java.util.List;

// @Data
public class PriceData {

    private List<RowRenderData> price;

    public List<RowRenderData> getPrice() {
        return price;
    }

    public void setPrice(List<RowRenderData> price) {
        this.price = price;
    }
}
