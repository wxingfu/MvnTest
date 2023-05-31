package com.weixf.component;

import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.NodeComponent;

@LiteflowComponent(id = "n", name = "N")
public class NCmp extends NodeComponent {

    @Override
    public void process() {
        System.out.println("执行了N组件...");
    }
}
