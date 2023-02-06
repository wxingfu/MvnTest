package com.wxf.test;

/*
 *
 * @author weixf
 * @date 2022-09-21
 */
public enum HandlerEnum {
    REPORT(1, "蚂蚁保报案同步"),
    REPORT_CANCEL(2, "蚂蚁保报案撤销"),
    REPORT_REJECT(3, "蚂蚁保报案不予受理"),
    REGISTER(4, "蚂蚁保立案同步"),
    END_CASE(5, "蚂蚁保结案同步"),
    REJECT_COMPENSATE(6, "蚂蚁保结案拒赔");

    private int code;
    private String desc;

    HandlerEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
