package com.wxf.word;

import com.deepoove.poi.policy.AbstractRenderPolicy;
import com.deepoove.poi.render.RenderContext;
import com.deepoove.poi.xwpf.BodyContainer;
import com.deepoove.poi.xwpf.BodyContainerFactory;
import org.apache.poi.xwpf.usermodel.IBody;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.util.List;

public class TextTableRenderPolicy extends AbstractRenderPolicy<Object> {
    @Override
    public void doRender(RenderContext<Object> context) throws Exception {

        System.out.println("-----------调用了doRender----------");
        XWPFRun run = context.getRun();
        BodyContainer bodyContainer = BodyContainerFactory.getBodyContainer(run);

        IBody target = bodyContainer.getTarget();
        XWPFDocument xwpfDocument = target.getXWPFDocument();
        List<XWPFParagraph> paragraphs = target.getParagraphs();
        for (XWPFParagraph paragraph : paragraphs) {
            String text = paragraph.getText();
        }


    }

    @Override
    protected void afterRender(RenderContext<Object> context) {
        // 清空标签
        clearPlaceholder(context, true);
    }
}
