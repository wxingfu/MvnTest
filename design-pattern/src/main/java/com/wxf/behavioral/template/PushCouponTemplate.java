package com.wxf.behavioral.template;

/*
 *
 * @author weixf
 * @date 2022-08-23
 */
public class PushCouponTemplate extends AbstractPushTemplate {
    @Override
    protected void execute(int customerId, String shopName) {
        System.out.println("会员:" + customerId + ",你好，" + shopName + "送您一张优惠券");
    }
}
