package com.wxf.wordexport.entity.swagger;

import java.io.IOException;

public class SwaggerTest {

    public static void main(String[] args) throws IOException {
        SwaggerTest swagger = new SwaggerTest();
        swagger.testSwaggerToWord();
    }

    /**
     * Swagger Word文档，
     * 样式优雅且有着清晰完整的文档结构，
     * API列表需要循环展示，
     * 接口的请求参数需要循环展示，
     * 接口的返回值需要循环展示，
     * 数据类型支持锚点到具体的模型，
     * 模型支持代码块高亮展示。
     * <p>
     * 使用区块对标签完成所有循环功能，可以完美的支持有序和多级列表；
     * 表格使用 LoopRowTableRenderPolicy 插件的约定，可以非常方便的完成参数、返回值等表格的渲染；
     * 使用Spring表达式来支持丰富的条件判断；
     * 代码块高亮使用 HighlightRenderPolicy 插件
     */
    public void testSwaggerToWord() throws IOException {

    }
}
