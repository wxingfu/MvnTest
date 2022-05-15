package com.wxf.service;

import com.alibaba.fastjson.JSONObject;
import com.wxf.util.IdCard;
import org.springframework.stereotype.Service;

@Service
public class CardServiceImpl {

    public JSONObject cardAnalysis(JSONObject object) {
        return IdCard.analysisIdCard(object.getString("cardNo"));
    }

}
