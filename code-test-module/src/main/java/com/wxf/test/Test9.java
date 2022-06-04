package com.wxf.test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test9 {
    public static void main(String[] args) throws ParseException {
        // String cp = "20000000";
        // BigDecimal mCountPrem = new BigDecimal(cp);
        // System.out.println(mCountPrem.toBigInteger());

        // Instant now = Instant.now();
        // System.out.println(now.toEpochMilli());


        String jsonStr = "{\n" +
                "    \"header\": {\n" +
                "        \"version\": \"1.2.0.0\",\n" +
                "        \"requestType\": \"P01\",\n" +
                "        \"uuid\": \"1234567890123456789\",\n" +
                "        \"comId\": \"001\",\n" +
                "        \"sendTime\": 1573110795\n" +
                "    },\n" +
                "    \"body\": {\n" +
                "        \"orderTotalPrice\": 10000,\n" +
                "        \"orderTotalPayPrice\": 10000,\n" +
                "        \"holderInfo\": {\n" +
                "            \"holderType\": 1,\n" +
                "            \"holderName\": \"测试\",\n" +
                "            \"holderCardType\": 1,\n" +
                "            \"holderCardNo\": \"110101199003072666\",\n" +
                "            \"holderProvinceId\": \"110100\",\n" +
                "            \"holderCityId\": \"110101\",\n" +
                "            \"holderAreaId\": \"110118\",\n" +
                "            \"holderAddress\": \"北京通州区科创十一街18号\",\n" +
                "            \"holderEmail\": \"123@qq.com\",\n" +
                "            \"holderCardBeginDate\": \"2015-01-01\",\n" +
                "            \"holderCardEndDate\": \"2025-01-01\",\n" +
                "            \"holderCardTremType\": 1,\n" +
                "            \"holderSex\": 1,\n" +
                "            \"holderBirthday\": \"1990-03-07\",\n" +
                "            \"holderMobile\": \"13578900356\",\n" +
                "            \"holderHeight\": \"170\",\n" +
                "            \"holderWeight\": \"80\",\n" +
                "            \"isInsured\": 0,\n" +
                "            \"holderJobCode\": \"123\",\n" +
                "            \"holderJobLevel\": \"1\",\n" +
                "            \"holderAnnualIncome\": \"10\",\n" +
                "            \"holderIncomeStream\": 1,\n" +
                "            \"holderCardFrontUrl\": \"http://baidu.com/pic/123.jpg\",\n" +
                "            \"holderCardBackUrl\": \"http://baidu.com/pic/321.jpg\",\n" +
                "            \"nationality\": 1,\n" +
                "            \"companyInfo\": {\n" +
                "                \"businessScope\": \"\",\n" +
                "                \"legalPersonName\": \"\",\n" +
                "                \"legalPersonCardType\": 1,\n" +
                "                \"legalPersonCardNo\": \"\",\n" +
                "                \"legalPersonCardTremType\": 1,\n" +
                "                \"legalPersonCardBeginDate\": \"\",\n" +
                "                \"legalPersonCardEndDate\": \"\",\n" +
                "                \"certigierName\": \"\",\n" +
                "                \"certigierCardType\": 1,\n" +
                "                \"certigierCardNo\": \"\",\n" +
                "                \"certigierCardTremType\": 1,\n" +
                "                \"certigierCardBeginDate\": \"\",\n" +
                "                \"certigierCardEndDate\": \"\",\n" +
                "                \"benefitOwnerName\": \"\",\n" +
                "                \"benefitOwnerAddress\": \"\",\n" +
                "                \"benefitOwnerCardType\": 1,\n" +
                "                \"benefitOwnerCardNo\": \"\",\n" +
                "                \"benefitOwnerCardTremType\": 1,\n" +
                "                \"benefitOwnerCardBeginDate\": \"\",\n" +
                "                \"benefitOwnerCardEndDate\": \"\"\n" +
                "            }\n" +
                "        },\n" +
                "        \"policyList\": [\n" +
                "            {\n" +
                "                \"policyInfo\": {\n" +
                "                    \"policyId\": \"123efsadsa\",\n" +
                "                    \"policyPrice\": 100000000,\n" +
                "                    \"policyPayPrice\": 100000000,\n" +
                "                    \"insuredNum\": 1,\n" +
                "                    \"bankNo\": \"ICBC\",\n" +
                "                    \"cardId\": \"123\",\n" +
                "                    \"returnBankNo\": \"\",\n" +
                "                    \"returnCardNo\": \"\",\n" +
                "                    \"uwMedicalNo\": \"dfwefwegjroejldsfl1231231\",\n" +
                "                    \"isTransferInsurance\": 0,\n" +
                "                    \"originalInsuranceEndDate\": \"\",\n" +
                "                    \"antiMoneyLaunder\": 1,\n" +
                "                    \"underwriteProvinceId\": \"10010\",\n" +
                "                    \"underwriteCityId\": \"10011\"\n" +
                "                },\n" +
                "                \"productSchemeList\": [\n" +
                "                    {\n" +
                "                        \"productCode\": \"012B0700\",\n" +
                "                        \"productSchemeInfo\": {\n" +
                "                            \"schemePremium\": 10000,\n" +
                "                            \"schemeCoverage\": 100000000,\n" +
                "                            \"insurancePeriodType\": \"M\",\n" +
                "                            \"insurancePeriod\": \"120\",\n" +
                "                            \"policyBeginDate\": \"2022-05-17 12:00:00\",\n" +
                "                            \"policyEndDate\": \"2032-05-16 12:00:00\"\n" +
                "                        },\n" +
                "                        \"renewalInfo\": {\n" +
                "                            \"paymentPeriod\": \"1\",\n" +
                "                            \"paymentPeriodType\": \"U\",\n" +
                "                            \"paymentFrequencyType\": \"\",\n" +
                "                            \"paymentFrequency\": \"\"\n" +
                "                        },\n" +
                "                        \"riskList\": [\n" +
                "                            {\n" +
                "                                \"riskCode\": \"\",\n" +
                "                                \"riskName\": \"\",\n" +
                "                                \"dutyCoverage\": 1000000\n" +
                "                            }\n" +
                "                        ],\n" +
                "                        \"otherInfo\": {\n" +
                "                            \"petType\": \"\",\n" +
                "                            \"petPictureUrl\": \"\",\n" +
                "                            \"petName\": \"\",\n" +
                "                            \"petCastrate\": \"\",\n" +
                "                            \"petBirthday\": \"\",\n" +
                "                            \"petSpecies\": \"\",\n" +
                "                            \"phoneBrand\": \"\",\n" +
                "                            \"phoneModel\": \"\",\n" +
                "                            \"eID\": \"\",\n" +
                "                            \"licenseNo\": \"\",\n" +
                "                            \"houseProvince\": \"\",\n" +
                "                            \"houseArea\": \"\",\n" +
                "                            \"houseCity\": \"\",\n" +
                "                            \"houseAddress\": \"\",\n" +
                "                            \"petMedicareCardNo\": \"\"\n" +
                "                        },\n" +
                "                        \"guaranteePlan\": \"\",\n" +
                "                        \"isGive\": 1,\n" +
                "                        \"isWaitPeriod\": 1,\n" +
                "                        \"receivingCycle\": \"Y\",\n" +
                "                        \"receivingAge\": 10,\n" +
                "                        \"productPrice\": \"10000\",\n" +
                "                        \"mainInsuranceFlag\": 1,\n" +
                "                        \"receivingEndPeriod\": 10,\n" +
                "                        \"receivingEndPeriodType\": \"A\",\n" +
                "                        \"waitPeriodDays\": 1\n" +
                "                    }\n" +
                "                ],\n" +
                "                \"insuredInfoList\": [\n" +
                "                    {\n" +
                "                        \"insuredType\": 1,\n" +
                "                        \"insuredName\": \"被保人姓名\",\n" +
                "                        \"insuredNum\": 1,\n" +
                "                        \"insuredPrice\": 100000000,\n" +
                "                        \"insuredCardType\": 1,\n" +
                "                        \"insuredCardNo\": \"被保人证件号\",\n" +
                "                        \"insuredCardBeginDate\": \"YYYY-MM-dd\",\n" +
                "                        \"insuredCardEndDate\": \"YYYY-MM-dd\",\n" +
                "                        \"insuredCardTremType\": 1,\n" +
                "                        \"insuredEmail\": \"被保人邮箱\",\n" +
                "                        \"insuredSex\": 1,\n" +
                "                        \"insuredBirthday\": \"YYYY-MM-dd\",\n" +
                "                        \"insuredMobile\": \"被保人手机号\",\n" +
                "                        \"insuredRelation\": 0,\n" +
                "                        \"insuredJobCode\": \"被保人职业代码\",\n" +
                "                        \"insuredJobLevel\": \"被保人职业等级\",\n" +
                "                        \"insuredMedical\": 1,\n" +
                "                        \"insuredHeight\": \"\",\n" +
                "                        \"insuredWeight\": \"\",\n" +
                "                        \"insuredProvinceId\": \"\",\n" +
                "                        \"insuredCityId\": \"\",\n" +
                "                        \"insuredAreaId\": \"\",\n" +
                "                        \"insuredAddress\": \"\",\n" +
                "                        \"smokingHistory\": 0,\n" +
                "                        \"nation\": \"\",\n" +
                "                        \"insuredAnnualIncome\": \"\",\n" +
                "                        \"insuredIncomeStream\": 1,\n" +
                "                        \"heightOfBirth\": 1000,\n" +
                "                        \"weightOfBirth\": 800,\n" +
                "                        \"benefitType\": 2,\n" +
                "                        \"benefitInfoList\": [\n" +
                "                            {\n" +
                "                                \"benefitType\": 1,\n" +
                "                                \"benefitNo\": \"\",\n" +
                "                                \"benefitName\": \"\",\n" +
                "                                \"benefitCardType\": 1,\n" +
                "                                \"benefitCardNo\": \"\",\n" +
                "                                \"benefitBirthday\": \"\",\n" +
                "                                \"benefitSex\": 1,\n" +
                "                                \"benefitMobile\": \"\",\n" +
                "                                \"residentProvince\": \"\",\n" +
                "                                \"residentCity\": \"\",\n" +
                "                                \"residentArea\": \"\",\n" +
                "                                \"benefitAddress\": \"\",\n" +
                "                                \"benefitRelation\": 1,\n" +
                "                                \"benefitSeq\": \"\",\n" +
                "                                \"benefitScale\": 20,\n" +
                "                                \"benefitCardEndDate\": \"\",\n" +
                "                                \"benefitCardBeginDate\": \"\",\n" +
                "                                \"benefitCardTremType\": 1,\n" +
                "                                \"nationality\": 1,\n" +
                "                                \"benefitJobCode\": \"\",\n" +
                "                                \"benefitJobLevel\": \"\",\n" +
                "                                \"companyInfo\": {\n" +
                "                                    \"businessScope\": \"\",\n" +
                "                                    \"legalPersonName\": \"\",\n" +
                "                                    \"legalPersonCardType\": 1,\n" +
                "                                    \"legalPersonCardNo\": \"\",\n" +
                "                                    \"legalPersonCardTremType\": 1,\n" +
                "                                    \"legalPersonCardBeginDate\": \"\",\n" +
                "                                    \"legalPersonCardEndDate\": \"\",\n" +
                "                                    \"certigierName\": \"\",\n" +
                "                                    \"certigierCardType\": 1,\n" +
                "                                    \"certigierCardNo\": \"\",\n" +
                "                                    \"certigierCardTremType\": 1,\n" +
                "                                    \"certigierCardBeginDate\": \"\",\n" +
                "                                    \"certigierCardEndDate\": \"\",\n" +
                "                                    \"benefitOwnerName\": \"\",\n" +
                "                                    \"benefitOwnerAddress\": \"\",\n" +
                "                                    \"benefitOwnerCardType\": 1,\n" +
                "                                    \"benefitOwnerCardNo\": \"\",\n" +
                "                                    \"benefitOwnerCardTremType\": 1,\n" +
                "                                    \"benefitOwnerCardBeginDate\": \"\",\n" +
                "                                    \"benefitOwnerCardEndDate\": \"\"\n" +
                "                                }\n" +
                "                            }\n" +
                "                        ],\n" +
                "                        \"userId\": \"\",\n" +
                "                        \"businessContactName\": \"\",\n" +
                "                        \"businessContactTelephone\": \"\",\n" +
                "                        \"nationality\": 1,\n" +
                "                        \"companyInfo\": {\n" +
                "                            \"businessScope\": \"\",\n" +
                "                            \"legalPersonName\": \"\",\n" +
                "                            \"legalPersonCardType\": 1,\n" +
                "                            \"legalPersonCardNo\": \"\",\n" +
                "                            \"legalPersonCardTremType\": 1,\n" +
                "                            \"legalPersonCardBeginDate\": \"\",\n" +
                "                            \"legalPersonCardEndDate\": \"\",\n" +
                "                            \"certigierName\": \"\",\n" +
                "                            \"certigierCardType\": 1,\n" +
                "                            \"certigierCardNo\": \"\",\n" +
                "                            \"certigierCardTremType\": 1,\n" +
                "                            \"certigierCardBeginDate\": \"\",\n" +
                "                            \"certigierCardEndDate\": \"\",\n" +
                "                            \"benefitOwnerName\": \"\",\n" +
                "                            \"benefitOwnerAddress\": \"\",\n" +
                "                            \"benefitOwnerCardType\": 1,\n" +
                "                            \"benefitOwnerCardNo\": \"\",\n" +
                "                            \"benefitOwnerCardTremType\": 1,\n" +
                "                            \"benefitOwnerCardBeginDate\": \"\",\n" +
                "                            \"benefitOwnerCardEndDate\": \"\"\n" +
                "                        }\n" +
                "                    }\n" +
                "                ],\n" +
                "                \"renewInfo\": {\n" +
                "                    \"historyPolicyId\": \"\",\n" +
                "                    \"historyPolicyNo\": \"\",\n" +
                "                    \"renewalType\": 1\n" +
                "                },\n" +
                "                \"targetList\": [\n" +
                "                    {\n" +
                "                        \"targetCode\": \"\",\n" +
                "                        \"targetName\": \"\",\n" +
                "                        \"targetNum\": 1,\n" +
                "                        \"targetPrice\": 100000000,\n" +
                "                        \"targetType\": \"\",\n" +
                "                        \"ext\": {\n" +
                "                            \"auctionProvinceName\": \"\",\n" +
                "                            \"auctionCityName\": \"\",\n" +
                "                            \"earnestMoney\": 100000000,\n" +
                "                            \"targetCategoryId\": \"\",\n" +
                "                            \"targetCategoryName\": \"\",\n" +
                "                            \"auctionStarTime\": \"\",\n" +
                "                            \"auctionEndTime\": \"\",\n" +
                "                            \"startingrice\": 10000,\n" +
                "                            \"disposeUnit\": \"\",\n" +
                "                            \"earnestMoneyRate\": 10,\n" +
                "                            \"mobileBrand\": \"\",\n" +
                "                            \"mobileModel\": \"\",\n" +
                "                            \"mobilePic\": \"\"\n" +
                "                        }\n" +
                "                    }\n" +
                "                ]\n" +
                "            }\n" +
                "        ],\n" +
                "        \"channelType\": \"JT\",\n" +
                "        \"expandInfo\": {}\n" +
                "    }\n" +
                "}";

        JSONObject jsonObject = JSONObject.parseObject(jsonStr);


        String date = "2022-05-26 11:39:13";
        Date datetime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);
        Long timestamp = datetime.getTime();
        System.out.println(timestamp);

        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(timestamp));
        System.out.println(time);


        time = "12:00:00";
        Date timeFormat = new SimpleDateFormat("HH:mm:ss").parse(time);
        Long timestamp2 = timeFormat.getTime();
        System.out.println(timestamp2);

        time = new SimpleDateFormat("HH:mm:ss").format(new Date(timestamp2));
        System.out.println(time);


        // time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(1640426071066L));
        time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(1653536353000L));
        System.out.println(time);
    }

    public static StringBuffer getAllKey(JSONObject jsonObject) {
        StringBuffer stringBuffer = new StringBuffer();
        for (String key : jsonObject.keySet()) {
            stringBuffer.append(key).append(" , ");
            if (jsonObject.get(key) instanceof JSONObject) {
                JSONObject innerObject = (JSONObject) jsonObject.get(key);
                stringBuffer.append(getAllKey(innerObject));
            } else if (jsonObject.get(key) instanceof JSONArray) {
                JSONArray innerObject = (JSONArray) jsonObject.get(key);
                stringBuffer.append(getAllKey(innerObject));
            }
        }
        return stringBuffer;
    }

    public static StringBuffer getAllKey(JSONArray jsonArray) {
        StringBuffer stringBuffer = new StringBuffer();
        if (jsonArray != null ) {
            for (Object key : jsonArray) {
                if (key instanceof JSONObject) {
                    JSONObject innerObject = (JSONObject) key;
                    stringBuffer.append(getAllKey(innerObject));
                } else if (key instanceof JSONArray) {
                    JSONArray innerObject = (JSONArray) key;
                    stringBuffer.append(getAllKey(innerObject));
                }
            }
        }
        return stringBuffer;
    }


    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("-?[0-9]+.?[0-9]+");
        Matcher isNum = pattern.matcher(str);
        return isNum.matches();
    }
}
