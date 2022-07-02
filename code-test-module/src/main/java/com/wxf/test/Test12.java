package com.wxf.test;


import cn.hutool.json.JSONObject;
import cn.hutool.json.XML;

/*
 *
 * @author weixf
 * @date 2022-06-28
 */
public class Test12 {

    public static void main(String[] args) {

        // String id_18 = "^[1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}(\\d|([Xx]))";
        // String id_15 = "^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{2}\\d";
        // String id_valid = "(" + id_18 + ")" + "|" + "(" + id_15 + ")";
        // Pattern pattern = Pattern.compile(id_valid);
        // Matcher matcher = pattern.matcher("91234520120131123X");
        // System.out.println(matcher.matches());

        String xml = "<?xml version=\"1.0\" encoding=\"GBK\"?>\n" +
                "<TranData>\n" +
                "\t<Head>\n" +
                "\t\t<Flag>000000</Flag>\n" +
                "\t\t<Desc>成功</Desc>\n" +
                "\t\t<TranNo>1640425963071131068</TranNo>\n" +
                "\t\t<TranDate>1656490402664</TranDate>\n" +
                "\t\t<Version>1.2.2.5</Version>\n" +
                "\t\t<RequestType>P01</RequestType>\n" +
                "\t\t<ComId>hg</ComId>\n" +
                "\t</Head>\n" +
                "\t<Body>\n" +
                "\t\t<ContNo>120077000000122</ContNo>\n" +
                "\t\t<ProposalPrtNo>120077000000122</ProposalPrtNo>\n" +
                "\t\t<Prem>25751</Prem>\n" +
                "\t\t<HealthImageNo/>\n" +
                "\t\t<TranNo>1640425963071131068</TranNo>\n" +
                "\t\t<TranDate>20220629</TranDate>\n" +
                "\t\t<TranTime>161035</TranTime>\n" +
                "\t\t<orderId>1640425963071131068</orderId>\n" +
                "\t\t<JDong>\n" +
                "\t\t\t<policyList>\n" +
                "\t\t\t\t<policyInfo>\n" +
                "\t\t\t\t\t<policyId>16404259630711310340034</policyId>\n" +
                "\t\t\t\t\t<proposalNo>120077000000122</proposalNo>\n" +
                "\t\t\t\t\t<policyPrice>25751</policyPrice>\n" +
                "\t\t\t\t\t<policyPayPrice>25751</policyPayPrice>\n" +
                "\t\t\t\t\t<policyNum>1</policyNum>\n" +
                "\t\t\t\t\t<proposalEffectiveDate/>\n" +
                "\t\t\t\t</policyInfo>\n" +
                "\t\t\t\t<insuredInfoList>\n" +
                "\t\t\t\t\t<insuredInfo>\n" +
                "\t\t\t\t\t\t<insuredRelation>00</insuredRelation>\n" +
                "\t\t\t\t\t\t<insuredNum>1</insuredNum>\n" +
                "\t\t\t\t\t\t<isSuccess>true</isSuccess>\n" +
                "\t\t\t\t\t\t<failCode>000000</failCode>\n" +
                "\t\t\t\t\t\t<failReason>成功</failReason>\n" +
                "\t\t\t\t\t</insuredInfo>\n" +
                "\t\t\t\t</insuredInfoList>\n" +
                "\t\t\t</policyList>\n" +
                "\t\t\t<orderTotalPrice>25751</orderTotalPrice>\n" +
                "\t\t\t<orderTotalPayPrice>25751</orderTotalPayPrice>\n" +
                "\t\t\t<insuranceNum>1</insuranceNum>\n" +
                "\t\t</JDong>\n" +
                "\t</Body>\n" +
                "</TranData>";

        JSONObject jsonObject = XML.toJSONObject(xml);

        System.out.println(jsonObject.toString());
    }
}
