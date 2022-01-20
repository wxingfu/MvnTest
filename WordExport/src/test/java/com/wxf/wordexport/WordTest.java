package com.wxf.wordexport;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.data.Pictures;
import com.deepoove.poi.data.Tables;
import com.deepoove.poi.data.style.BorderStyle;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class WordTest {

    public final static String base = "F:\\wordExport\\";
    public final static String templatePath = base + "templates\\";
    public final static String imgPath = base + "images\\";
    public final static String markdownPath = base + "markdown\\";
    public final static String outPath = base;

    @Test
    public void test1() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "餐边柜");
        map.put("type", "7901#");
        map.put("logo", Pictures.ofLocal(imgPath + "yxj-logo.jpg").create());
        map.put("series", "奢华");
        map.put("standards", "120cm*190cm");
        // BorderStyle borderStyle = BorderStyle.builder().withType(XWPFTable.XWPFBorderType.NONE).build();
        // TableRenderData tableRenderData = Tables.of(new String[][]{
        //         new String[]{"\n￥14000"}
        // }).autoWidth().border(borderStyle).create();
        //
        // map.put("price", tableRenderData)；
        map.put("price", "14000");

        XWPFTemplate compile = XWPFTemplate.compile(templatePath + "怡欣居标价牌.docx");
        XWPFTemplate template = compile.render(map);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(outPath + "7901#.docx");
            template.writeAndClose(fileOutputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2() {
        Map<String, Object> map = new HashMap<>();


        BorderStyle build = BorderStyle.builder().withType(XWPFTable.XWPFBorderType.NONE).build();
        // map.put("price", Tables.of({new String[][]{
        //         new String[]{""},
        //         new String[]{"￥14000"}
        // }).border(build).create());

        map.put("price", Tables.of(new String[][]{
                new String[]{"\n￥14000"}
        }).autoWidth().border(build).create());

        XWPFTemplate compile = XWPFTemplate.compile(templatePath + "怡欣居标价.docx");
        XWPFTemplate template = compile.render(map);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(outPath + "怡欣居标价.docx");
            template.writeAndClose(fileOutputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
