package com.wxf.observer;

/*
 *
 * @author weixf
 * @date 2022-08-23
 */
public class StockObserver implements Observer {
    @Override
    public void notify(String orderNo) {
        System.out.println("订单 " + orderNo + " 已通知库房发货！");
    }
}
