package com.weixf.card.analysis.controller;

import com.alibaba.fastjson.JSONObject;
import com.weixf.card.analysis.entity.JsonResult;
import com.weixf.card.analysis.service.CardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/analysis")
public class AnalysisController {


    @Autowired
    private CardServiceImpl cardService;

    /**
     * 解析身份证信息
     *
     * @return
     */
    @PostMapping("/analysisCardNo")
    public JsonResult analysisCardNo(@RequestBody JSONObject obj) {
        return new JsonResult(cardService.cardAnalysis(obj));
    }


}
