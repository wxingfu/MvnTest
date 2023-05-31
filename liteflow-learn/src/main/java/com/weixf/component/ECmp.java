package com.weixf.component;

import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.NodeComponent;

@LiteflowComponent(id = "e", name = "E")
public class ECmp extends NodeComponent {

    @Override
    public void process() {
        System.out.println("执行了E组件...");
    }
}
