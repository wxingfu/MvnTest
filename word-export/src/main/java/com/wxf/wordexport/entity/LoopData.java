package com.wxf.wordexport.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class LoopData {

    List<Goods> goods = new ArrayList<>();
    List<Labor> labors = new ArrayList<>();
    private String total;
}
