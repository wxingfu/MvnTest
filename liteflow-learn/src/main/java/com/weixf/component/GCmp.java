package com.weixf.component;

import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.NodeSwitchComponent;

@LiteflowComponent(id = "g", name = "G")
public class GCmp extends NodeSwitchComponent {

    @Override
    public String processSwitch() throws Exception {
        Object requestData = this.getRequestData();
        System.out.println(requestData);
        System.out.println("执行了G组件...");
        if ("".equals(requestData)) {
            return "t1";
        } else {
            return "t2";
        }
    }
}
