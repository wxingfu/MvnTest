package com.weixf.component;

import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.NodeComponent;

@LiteflowComponent(id = "j", name = "J")
public class JCmp extends NodeComponent {

    @Override
    public void process() {
        System.out.println("执行了J组件...");
    }
}
