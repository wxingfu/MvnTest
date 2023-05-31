package com.weixf.component;

import com.weixf.context.MyContext;
import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.NodeComponent;

@LiteflowComponent(id = "a", name = "A")
public class ACmp extends NodeComponent {

    @Override
    public void process() {
        System.out.println("....执行了A组件...");
        Object requestData = this.getRequestData();
        System.out.println(requestData);
        MyContext contextBean = this.getContextBean(MyContext.class);
        contextBean.setAge(100000);
        System.out.println("....执行了A组件...");
    }
}
