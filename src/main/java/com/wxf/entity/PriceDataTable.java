package com.wxf.entity;

import com.deepoove.poi.data.PictureRenderData;
import com.deepoove.poi.expression.Name;

// @Data
public class PriceDataTable {

    private String name;
    private String type;
    private PictureRenderData logo;
    private String clazz;
    private String standards;

    @Name("price")
    private PriceData price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public PictureRenderData getLogo() {
        return logo;
    }

    public void setLogo(PictureRenderData logo) {
        this.logo = logo;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public String getStandards() {
        return standards;
    }

    public void setStandards(String standards) {
        this.standards = standards;
    }

    public PriceData getPrice() {
        return price;
    }

    public void setPrice(PriceData price) {
        this.price = price;
    }
}
