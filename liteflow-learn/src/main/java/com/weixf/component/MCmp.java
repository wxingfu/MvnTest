package com.weixf.component;

import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.NodeComponent;

@LiteflowComponent(id = "m", name = "M")
public class MCmp extends NodeComponent {

    @Override
    public void process() {
        System.out.println("执行了M组件...");
    }
}
