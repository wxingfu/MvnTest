package com.weixf.component;

import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.NodeComponent;

@LiteflowComponent(id = "d", name = "D")
public class DCmp extends NodeComponent {

    @Override
    public void process() {
        System.out.println("执行了D组件...");
    }
}
