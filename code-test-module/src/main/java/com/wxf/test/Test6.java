package com.wxf.test;

import java.util.HashMap;
import java.util.Map;


public class Test6 {

    private static final Map<HandlerEnum, HandlerStrategy> strategyMap =
            new HashMap<HandlerEnum, HandlerStrategy>();

    public static void main(String[] args) {
        strategyMap.put(HandlerEnum.REPORT, new Handler());

        HandlerStrategy strategy = strategyMap.get(HandlerEnum.REPORT);
        System.out.println(strategy);
    }
}
