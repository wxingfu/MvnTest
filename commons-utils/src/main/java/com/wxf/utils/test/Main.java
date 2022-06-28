package com.wxf.utils.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wxf.utils.http.HttpUtils;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/*
 *
 * @author weixf
 * @date 2022-06-22
 */
public class Main {

    public static void main(String[] args) throws Exception {

        String password = "ABCDEF";
        String md5Key = "123456";
        // 核保
        String content = "{\"header\":{\"comId\":\"470002\",\"licenseTag\":\"jintou\",\"requestType\":\"P01\",\"sendTime\":1655695783647,\"uuid\":\"1655695783959130048\",\"version\":\"1.2.1.0\"},\"body\":{\"channelType\":\"JT\",\"subChannel\":\"JDJR\",\"expandInfo\":{\"operTraceSessionId\":\"a2a12069e79557e37e8389b341c0b833\"},\"holderInfo\":{\"holderType\":1,\"holderName\":\"王洪涛\",\"holderSex\":1,\"holderBirthday\":\"1990-03-07\",\"holderCardType\":1,\"holderCardNo\":\"11010119900307221X\",\"holderCardEndDate\":\"2030-07-26\",\"holderMobile\":\"15000095783\",\"holderEmail\":\"vccz@163.com\",\"holderAnnualIncome\":\"11\",\"holderProvinceId\":\"110000\",\"holderCityId\":\"110100\",\"holderAreaId\":\"110101\",\"holderAddress\":\"投保人详细地址不能少于5个汉字，并包含路、弄、村、组、栋、单元、室、号其中一个，且必须有一位数字\",\"holderJobCode\":\"2010101\",\"isInsured\":1},\"orderTotalPayPrice\":116640,\"orderTotalPrice\":116640,\"policyList\":[{\"insuredInfoList\":[{\"insuredNum\":1,\"insuredPrice\":116640,\"insuredRelation\":0,\"insuredType\":1,\"insuredName\":\"王洪涛\",\"insuredSex\":1,\"insuredBirthday\":\"1990-03-07\",\"insuredCardType\":1,\"insuredCardNo\":\"11010119900307221X\",\"insuredCardEndDate\":\"2030-07-26\",\"insuredMobile\":\"15000095783\",\"insuredEmail\":\"vccz@163.com\",\"insuredHeight\":\"178\",\"insuredWeight\":\"68\",\"insuredAnnualIncome\":\"11\",\"insuredProvinceId\":\"110000\",\"insuredCityId\":\"110100\",\"insuredAreaId\":\"110101\",\"insuredAddress\":\"被保人详细地址不能少于5个汉字，并包含路、弄、村、组、栋、单元、室、号其中一个，且必须有一位数字\",\"insuredJobCode\":\"2010101\",\"insuredMedical\":1,\"benefitType\":1}],\"policyInfo\":{\"policyId\":\"16556957839591300480000\",\"policyPayPrice\":116640,\"policyPrice\":116640,\"antiMoneyLaunder\":0,\"bankNo\":\"105\",\"cardId\":\"6227002190466716191\",\"insuredNum\":1},\"productSchemeList\":[{\"mainInsuranceFlag\":1,\"productCode\":\"B27D01\",\"productSchemeInfo\":{\"insurancePeriod\":\"10\",\"insurancePeriodType\":\"Y\",\"paymentType\":\"Y\",\"policyBeginDate\":\"2022-06-28 00:00:00\",\"policyEndDate\":\"2032-06-21 00:00:00\",\"productName\":\"华贵大麦2022定期寿险（互联网专属）\",\"schemeCode\":\"2025955593992795\",\"schemeCoverage\":100000000,\"schemeName\":\"100万\",\"schemePremium\":103900},\"renewalInfo\":{\"paymentFrequency\":\"1\",\"paymentFrequencyType\":\"Y\",\"paymentPeriod\":\"5\",\"paymentPeriodType\":\"Y\"},\"riskList\":[{\"deductibleAmount\":0,\"dutyCoverage\":100000000,\"riskExplain\":\"身故及全残-100万\",\"riskName\":\"身故及全残\",\"riskType\":\"0\"}],\"waitPeriodDays\":90},{\"mainInsuranceFlag\":2,\"productCode\":\"B27D03\",\"productSchemeInfo\":{\"insurancePeriod\":\"10\",\"insurancePeriodType\":\"Y\",\"paymentType\":\"Y\",\"policyBeginDate\":\"2022-06-28 00:00:00\",\"policyEndDate\":\"2032-06-21 00:00:00\",\"productName\":\"水陆意外责任\",\"schemeCode\":\"2025955139993908\",\"schemeCoverage\":100000000,\"schemeName\":\"100万\",\"schemePremium\":103900},\"renewalInfo\":{\"paymentFrequency\":\"1\",\"paymentFrequencyType\":\"Y\",\"paymentPeriod\":\"5\",\"paymentPeriodType\":\"Y\"},\"riskList\":[{\"deductibleAmount\":0,\"dutyCoverage\":100000000,\"riskExplain\":\"身故及全残-100万\",\"riskName\":\"身故及全残\",\"riskType\":\"1\"}],\"waitPeriodDays\":90},{\"mainInsuranceFlag\":2,\"productCode\":\"B27D02\",\"productSchemeInfo\":{\"insurancePeriod\":\"10\",\"insurancePeriodType\":\"Y\",\"paymentType\":\"Y\",\"policyBeginDate\":\"2022-06-28 00:00:00\",\"policyEndDate\":\"2032-06-21 00:00:00\",\"productName\":\"航空意外责任\",\"schemeCode\":\"2025955130993844\",\"schemeCoverage\":100000000,\"schemeName\":\"100万\",\"schemePremium\":103900},\"renewalInfo\":{\"paymentFrequency\":\"1\",\"paymentFrequencyType\":\"Y\",\"paymentPeriod\":\"5\",\"paymentPeriodType\":\"Y\"},\"riskList\":[{\"deductibleAmount\":0,\"dutyCoverage\":100000000,\"riskExplain\":\"身故及全残-100万\",\"riskName\":\"身故及全残\",\"riskType\":\"1\"}],\"waitPeriodDays\":90}],\"renewInfo\":{}}]}}";
        // 签单
        // String content = "{\"header\":{\"comId\":\"470002\",\"licenseTag\":\"jintou\",\"requestType\":\"P02\",\"sendTime\":1655695783647,\"uuid\":\"1655695783959130048\",\"version\":\"1.2.1.0\"},\"body\":{\"orderTotalPayPrice\":116640,\"orderTotalPrice\":116640,\"payment\":{\"payAccountId\":\"\",\"payAmount\":116640,\"payBankNo\":\"\",\"payId\":\"18650062206070933140874803295\",\"payTime\":\"2022-06-28 09:33:15\",\"payType\":1},\"policyList\":[{\"productSchemeList\":[{\"productCode\":\"012B2700\"}]}],\"proposalList\":[{\"policyId\":\"16556957839591300480000\",\"policyPayPrice\":116640,\"policyPrice\":116640,\"proposalNo\":\"120077000000120\"}]}}";
        // 退保试算
        // String content = "{\"header\":{\"version\":\"1.2.2.5\",\"requestType\":\"P101\",\"uuid\":\"1655695783959130048\",\"comId\":\"470002\",\"sendTime\":1656399353282},\"body\":{\"policyId\":\"16556957839591300480000\",\"policyNo\":\"888050001638466\",\"applyTime\":\"2022-06-28 11:11:11\"}}";
        // 续期信息查询
        // String content = "{\"header\":{\"version\":\"1.2.2.5\",\"requestType\":\"P21\",\"uuid\":\"1655695783959130048\",\"comId\":\"470002\",\"sendTime\":1656399353282},\"body\":{\"orderId\":\"1589524506153\",\"policyNo\":\"888050001638466\",\"renewalPeriod\":\"202207\"}}";
        // 退保
        // String content = "{\"header\":{\"version\":\"1.2.2.5\",\"requestType\":\"P102\",\"uuid\":\"1655695783959130048\",\"comId\":\"470002\",\"sendTime\":1656399353282},\"body\":{\"policyId\":\"16556957839591300480000\",\"policyNo\":\"888050001638466\",\"surrenderAmount\":116640,\"surrenderChannel\":\"0\",\"accountingDate \":\"2022-06-28 16:43:00\",\"surrenderApplyId\":\"123456789\",\"bankCardNo\":\"6227002190466716191\",\"bankAccountName\":\"王洪涛\",\"bankNo\":\"105\",\"surrenderLockType\":\"0\",\"antiMoneyLaunder\":0}}";

        String base64String = Base64.getEncoder().encodeToString(content.getBytes());
        System.out.println(base64String);

        String aesString = AesUtil.encrypt(base64String, password);
        String signString = Md5Util.digestByMd5(base64String + md5Key);
        System.out.println("参数：" + aesString);
        System.out.println("签名：" + signString);
        //
        // Map<String, Object> paramsMap = new HashMap<>();
        // paramsMap.put("data", aesString);
        // paramsMap.put("sign", signString);
        // String params = JSON.toJSONString(paramsMap);
        // System.out.println("请求参数：" + params);
        // // 核保
        // // String url = "https://dscapitest.huaguilife.cn/dsc/thirdSepc/i/5055/tjjtInsure";
        // // 签单
        // // String url = "https://dscapitest.huaguilife.cn/dsc/thirdSepc/i/5055/tjjtSign";
        // // 退保试算
        // // String url = "https://dscapitest.huaguilife.cn/dsc/thirdSepc/i/5055/tjjtEdorTrial";
        // // 退保
        // String url = "https://dscapitest.huaguilife.cn/dsc/thirdSepc/i/5055/tjjtEdorCT";
        // // 续期扣费结果通知
        // // String url = "https://dscapitest.huaguilife.cn/dsc/thirdSepc/i/5055/tjjtRePayResultNotice";
        // // 续期申请
        // // String url = "https://dscapitest.huaguilife.cn/dsc/thirdSepc/i/5055/tjjtReApply";
        // // 续期查询
        // // String url = "https://dscapitest.huaguilife.cn/dsc/thirdSepc/i/5055/tjjtQueryReInfo";
        // // 撤单
        // // String url = "https://dscapitest.huaguilife.cn/dsc/thirdSepc/i/5055/tjjtRemove";
        //
        // // 发请求
        // String result = HttpUtils.postFromJson(url, params);
        //
        // JSONObject jsonObject = JSON.parseObject(result);
        // String data = (String) jsonObject.get("data");
        // String decrypt = AesUtil.decrypt(data, password);
        // byte[] decode = Base64.getDecoder().decode(decrypt);
        // String out = new String(decode);
        // System.out.println("响应参数解密后：" + out);
    }
}
