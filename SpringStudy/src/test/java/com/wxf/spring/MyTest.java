package com.wxf.spring;

import com.wxf.spring.pdm.*;
import com.wxf.spring.utils.CommonUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.*;

@SpringBootTest
public class MyTest {

    @Test
    public void test1() {

        int a = 10 + (10 >>> 1);
        System.out.println(a);
    }

    @Autowired
    private Configuration configuration;

    @Test
    public void createSchema() throws Exception {
        Template template = configuration.getTemplate("schema.ftl");
        try {
            Parser parser = new Parser();
            // PDM pdm = parser.pdmParser("D:\\MyWork\\Schema\\LDOnluser.pdm");
            PDM pdm = parser.pdmParser("D:\\MyWork\\Schema\\NoRealBlcDtl.pdm");

            String dataBaseName = pdm.getName();

            Properties properties = System.getProperties();

            ConvertPDM convertPDM = new ConvertPDM();
            convertPDM.setDBMSType(DBConst.DB_Oracle);
            convertPDM.setAllowErrorInPDM(false); // 转换过程不允许跳过错误
            convertPDM.setAllowJavaType(false); // 转换时使用基本类型
            convertPDM.setAllowJavaMath(false); // 转换时不使用java.math类型

            ArrayList<PDMTable> tables = pdm.getTables();
            for (PDMTable table : tables) {
                String code = table.getCode();
                // String name = table.getName();

                ArrayList<PDMKey> keys = table.getKeys();
                // System.out.println(keys);
                PDMKey primaryKey = table.getPrimaryKey();
                // System.out.println(primaryKey);
                ArrayList<PDMColumn> pkList = primaryKey.getColumns();
                // System.out.println(pkList);

                ArrayList<PDMColumn> columns = table.getColumns();
                // System.out.println(columns);
                ArrayList<PDMColumn> newColumns = convertPDM.ConvertColumnType(code, columns);
                // System.out.println(newColumns);
                // System.out.println(newColumns.size());

                Map<String, Object> map = new HashMap<>();
                map.put("javaVmName", properties.getProperty("java.vm.name"));
                map.put("javaVmVersion", properties.getProperty("java.vm.version"));
                map.put("javaVmVendor", properties.getProperty("java.vm.vendor"));
                map.put("osName", properties.getProperty("os.name"));
                map.put("osArch", properties.getProperty("os.arch"));
                map.put("userName", properties.getProperty("user.name"));
                map.put("userCountry", properties.getProperty("user.country"));

                map.put("className", code);
                map.put("dataBase", dataBaseName);
                Date date = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                map.put("createDateTime", dateFormat.format(date));
                map.put("tableColumns", newColumns);
                map.put("columnNum", newColumns.size());
                map.put("pkList", pkList);
                map.put("pkNum", pkList.size());

                // System.out.println(map);
                CommonUtil.printFile(map, template, "D:\\MyWork\\Schema\\", code + "Schema.java");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
