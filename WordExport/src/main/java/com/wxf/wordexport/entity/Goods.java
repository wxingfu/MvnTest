package com.wxf.wordexport.entity;

import com.deepoove.poi.data.PictureRenderData;
import lombok.Data;

@Data
public class Goods {

    private int count;
    private String name;
    private String desc;
    private int discount;
    private int tax;
    private int price;
    private int totalPrice;
    private PictureRenderData picture;

}
