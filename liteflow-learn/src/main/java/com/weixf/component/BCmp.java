package com.weixf.component;

import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.NodeComponent;

@LiteflowComponent(id = "b", name = "B")
public class BCmp extends NodeComponent {

    @Override
    public void process() {
        System.out.println("执行了B组件...");
    }
}
