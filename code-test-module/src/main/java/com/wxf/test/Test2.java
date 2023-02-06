package com.wxf.test;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

/**
 * @author wxf
 */
public class Test2 {


    public static void main(String[] args) throws IOException, JDOMException {
        // SAXBuilder saxBuilder = new SAXBuilder();
        // Document document = saxBuilder.build(Files.newInputStream(Paths.get("C:\\Users\\weixf\\Desktop\\回销.xml")));
        // Element rootElement = document.getRootElement();
        // Element body = rootElement.getChild("Body");
        //
        // Element Images = body.getChild("Images");
        // List<Element> children = Images.getChildren("Image");
        // int size = children.size();
        // if (children.size() == 0) {
        //     System.out.println(size);
        // }
        // for (Element child : children) {
        //     System.out.println(child.getChildText("ImageName"));
        // }

        File file = new File("E:\\MyWork\\files\\temp\\GWL\\1.XML");
        SAXBuilder sax = new SAXBuilder();
        Document doc = sax.build(Files.newInputStream(file.toPath()));
        Element rootElement = doc.getRootElement();
        Element entities = rootElement.getChild("entities");
        List<Element> entitiesChildren = entities.getChildren();
        for (Element entitie : entitiesChildren) {
            Element eleNativeCharNames = entitie.getChild("nativeCharNames");
            if (eleNativeCharNames != null) {
                List<Element> eleNativeCharNameChildren = eleNativeCharNames.getChildren("nativeCharName");
                for (Element eleNativeCharName : eleNativeCharNameChildren) {
                    String str = eleNativeCharName.getText();
                    System.out.println("----------------------------");
                    System.out.println(str);
                    System.out.println(convert(str));
                    System.out.println(string2Unicode(str));
                    System.out.println(unicode2String(convert(str)));
                    System.out.println(unicode2String(string2Unicode(str)));
                    System.out.println("----------------------------");
                }
            }
        }

        // Element renewContReturns = body.getChild("RenewContReturns");
        // List<Element> children = renewContReturns.getChildren("RenewContReturn");
        // for (Element child : children) {
        //     System.out.println(child.getChildText("ActualPayPrem"));
        // }

        // System.out.println(rootElement.getText());

        // BigDecimal mDuePayPrem = new BigDecimal(0);
        // for (int i = 1; i <= 3; i++) {
        //     mDuePayPrem = mDuePayPrem.add(new BigDecimal(""));
        // }
        // String a = mDuePayPrem.toString();
        // System.out.println(a);
    }


    // 字符串转换unicode
    public static String string2Unicode(String string) {
        StringBuilder unicode = new StringBuilder();
        for (int i = 0; i < string.length(); i++) {
            // 取出每一个字符
            char c = string.charAt(i);
            // 转换为unicode
            unicode.append("\\u").append(Integer.toHexString(c));
        }
        return unicode.toString();
    }

    // unicode 转字符串
    public static String unicode2String(String unicode) {
        StringBuilder string = new StringBuilder();
        String[] hex = unicode.split("\\\\u");
        for (int i = 1; i < hex.length; i++) {
            // 转换出每一个代码点
            int data = Integer.parseInt(hex[i], 16);
            // 追加成string
            string.append((char) data);
        }
        return string.toString();
    }

    public static String convert(String str) {
        str = (str == null ? "" : str);
        String tmp;
        StringBuilder sb = new StringBuilder(1000);
        char c;
        int i, j;
        sb.setLength(0);
        for (i = 0; i < str.length(); i++) {
            c = str.charAt(i);
            sb.append("\\u");
            j = (c >>> 8); //取出高8位
            tmp = Integer.toHexString(j);
            if (tmp.length() == 1) {
                sb.append("0");
            }
            sb.append(tmp);
            j = (c & 0xFF); //取出低8位
            tmp = Integer.toHexString(j);
            if (tmp.length() == 1) {
                sb.append("0");
            }
            sb.append(tmp);

        }
        return (new String(sb));
    }
}
