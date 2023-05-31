package com.weixf.component;

import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.NodeSwitchComponent;

@LiteflowComponent(id = "b2", name = "B2")
public class B2Cmp extends NodeSwitchComponent {

    @Override
    public String processSwitch() throws Exception {
        Object requestData = this.getRequestData();
        System.out.println(requestData);
        System.out.println("执行了B2组件...");
        if ("".equals(requestData)) {
            return "t1";
        } else {
            return "t3";
        }
    }

}