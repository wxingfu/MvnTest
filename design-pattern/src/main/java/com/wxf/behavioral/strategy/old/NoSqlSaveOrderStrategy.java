package com.wxf.behavioral.strategy.old;

/*
 *
 * @author weixf
 * @date 2022-08-23
 * 策略实现1
 */
public class NoSqlSaveOrderStrategy implements OrderService {
    @Override
    public void saveOrder(String orderNo) {
        System.out.println("order:" + orderNo + " save to nosql");
    }
}
