package com.weixf.component;

import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.NodeComponent;

@LiteflowComponent(id = "i", name = "I")
public class ICmp extends NodeComponent {

    @Override
    public void process() {
        System.out.println("执行了I组件...");
    }
}
