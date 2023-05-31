package com.weixf.component;

import com.weixf.context.MyContext2;
import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.NodeComponent;

@LiteflowComponent("c")
public class CCmp extends NodeComponent {

    @Override
    public void process() {
        System.out.println("...执行了C组件...");
        Object requestData = this.getRequestData();
        System.out.println(requestData);
        MyContext2 contextBean = this.getContextBean(MyContext2.class);
        contextBean.setUsername("CCCCCC");
        System.out.println("...执行了C组件...");
    }
}
