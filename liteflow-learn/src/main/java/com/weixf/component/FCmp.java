package com.weixf.component;

import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.NodeComponent;

@LiteflowComponent(id = "f", name = "F")
public class FCmp extends NodeComponent {

    @Override
    public void process() {
        System.out.println("执行了F组件...");
    }
}
