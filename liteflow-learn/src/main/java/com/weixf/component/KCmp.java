package com.weixf.component;

import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.NodeComponent;

@LiteflowComponent(id = "k", name = "K")
public class KCmp extends NodeComponent {

    @Override
    public void process() {
        System.out.println("执行了K组件...");
    }
}
