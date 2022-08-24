package com.wxf.behavioral.strategy;

/*
 *
 * @author weixf
 * @date 2022-08-23
 * 策略容器
 */
public class OrderServiceStrategy {

    private final OrderService service;

    public OrderServiceStrategy(OrderService service) {
        this.service = service;
    }

    public void save(String orderNo) {
        this.service.saveOrder(orderNo);
    }
}
