package com.weixf.component;

import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.NodeComponent;

@LiteflowComponent(id = "l", name = "L")
public class LCmp extends NodeComponent {

    @Override
    public void process() {
        System.out.println("执行了L组件...");
    }
}
