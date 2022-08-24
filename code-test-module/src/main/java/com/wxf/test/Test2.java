package com.wxf.test;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author wxf
 */
public class Test2 {


    public static void main(String[] args) throws IOException, JDOMException {
        SAXBuilder saxBuilder = new SAXBuilder();
        Document document = saxBuilder.build(Files.newInputStream(Paths.get("C:\\Users\\weixf\\Desktop\\new 3.xml")));
        Element rootElement = document.getRootElement();
        Element body = rootElement.getChild("Body");

        Element renewContReturns = body.getChild("RenewContReturns");
        List<Element> children = renewContReturns.getChildren("RenewContReturn");
        for (Element child : children) {
            System.out.println(child.getChildText("ActualPayPrem"));
        }

        // System.out.println(rootElement.getText());

        // BigDecimal mDuePayPrem = new BigDecimal(0);
        // for (int i = 1; i <= 3; i++) {
        //     mDuePayPrem = mDuePayPrem.add(new BigDecimal(""));
        // }
        // String a = mDuePayPrem.toString();
        // System.out.println(a);
    }
}
