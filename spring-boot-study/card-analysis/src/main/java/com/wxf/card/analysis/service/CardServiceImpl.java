package com.wxf.card.analysis.service;

import com.alibaba.fastjson.JSONObject;
import com.wxf.card.analysis.util.IdCard;
import org.springframework.stereotype.Service;

@Service
public class CardServiceImpl {

    public JSONObject cardAnalysis(JSONObject object) {
        return IdCard.analysisIdCard(object.getString("cardNo"));
    }

}
