package com.weixf.component;

import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.NodeComponent;

@LiteflowComponent(id = "z", name = "Z")
public class ZCmp extends NodeComponent {

    @Override
    public void process() {
        System.out.println("执行了Z组件...");
    }
}
