package com.wxf.spring;

import com.wxf.spring.pdm.*;
import com.wxf.spring.utils.CommonUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@SpringBootTest
public class MyTest {

    @Autowired
    private Configuration configuration;

    @Test
    public void executeAll() throws Exception {
        createSchema();
        createSet();
        createDbSet();
        createDb();
    }

    @Test
    public void createSchema() throws Exception {
        Template schemaTemplate = configuration.getTemplate("schema.ftl");
        try {
            Parser parser = new Parser();
            PDM pdm = parser.pdmParser("D:\\MyWork\\Schema\\NoRealBlcDtl.pdm");
            // PDM pdm = parser.pdmParser("D:\\MyWork\\Schema\\LifeInsurancePersonal.pdm");

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
                ArrayList<PDMColumn> newPKList = convertPDM.ConvertColumnType(code, pkList);
                // System.out.println(pkList);
                ArrayList<PDMColumn> columns = table.getColumns();
                // System.out.println(columns);
                ArrayList<PDMColumn> newColumns = convertPDM.ConvertColumnType(code, columns);
                // System.out.println(newColumns);
                // System.out.println(newColumns.size());
                Date date = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss  SSS");
                SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy");

                Map<String, Object> schemaMap = new HashMap<>();
                schemaMap.put("javaVmName", properties.getProperty("java.vm.name"));
                schemaMap.put("javaVmVersion", properties.getProperty("java.vm.version"));
                schemaMap.put("javaVmVendor", properties.getProperty("java.vm.vendor"));
                schemaMap.put("osName", properties.getProperty("os.name"));
                schemaMap.put("osArch", properties.getProperty("os.arch"));
                schemaMap.put("userName", properties.getProperty("user.name"));
                schemaMap.put("userCountry", properties.getProperty("user.country"));

                schemaMap.put("currentYear", dateFormat2.format(date));
                schemaMap.put("tableName", code);
                schemaMap.put("dataBase", dataBaseName);
                schemaMap.put("createDateTime", dateFormat.format(date));
                schemaMap.put("tableColumns", newColumns);
                schemaMap.put("columnNum", newColumns.size());
                schemaMap.put("pkList", newPKList);
                schemaMap.put("pkNum", newPKList.size());
                CommonUtil.printFile(schemaMap, schemaTemplate, "D:\\MyWork\\Schema\\", code + "Schema.java");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createSet() throws IOException {
        Template setTemplate = configuration.getTemplate("set.ftl");
        try {
            Parser parser = new Parser();
            PDM pdm = parser.pdmParser("D:\\MyWork\\Schema\\NoRealBlcDtl.pdm");
            // PDM pdm = parser.pdmParser("D:\\MyWork\\Schema\\LifeInsurancePersonal.pdm");
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

                Date date = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
                SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy");

                Map<String, Object> setMap = new HashMap<>();
                setMap.put("javaVmName", properties.getProperty("java.vm.name"));
                setMap.put("javaVmVersion", properties.getProperty("java.vm.version"));
                setMap.put("javaVmVendor", properties.getProperty("java.vm.vendor"));
                setMap.put("osName", properties.getProperty("os.name"));
                setMap.put("osArch", properties.getProperty("os.arch"));
                setMap.put("userName", properties.getProperty("user.name"));
                setMap.put("userCountry", properties.getProperty("user.country"));

                setMap.put("currentYear", dateFormat2.format(date));
                setMap.put("tableName", code);
                setMap.put("dataBase", dataBaseName);
                setMap.put("createDateTime", dateFormat.format(date));
                CommonUtil.printFile(setMap, setTemplate, "D:\\MyWork\\Schema\\", code + "Set.java");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createDbSet() throws IOException {
        Template setTemplate = configuration.getTemplate("dbset.ftl");
        try {
            Parser parser = new Parser();
            PDM pdm = parser.pdmParser("D:\\MyWork\\Schema\\NoRealBlcDtl.pdm");
            // PDM pdm = parser.pdmParser("D:\\MyWork\\Schema\\LifeInsurancePersonal.pdm");
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
                ArrayList<PDMKey> keys = table.getKeys();
                PDMKey primaryKey = table.getPrimaryKey();
                ArrayList<PDMColumn> pkList = primaryKey.getColumns();
                ArrayList<PDMColumn> newPKList = convertPDM.ConvertColumnType(code, pkList);
                // System.out.println(pkList);
                ArrayList<PDMColumn> columns = table.getColumns();
                ArrayList<PDMColumn> newColumns = convertPDM.ConvertColumnType(code, columns);
                // System.out.println(newColumns);

                Date date = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
                SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy");

                Map<String, Object> dbSetMap = new HashMap<>();
                dbSetMap.put("javaVmName", properties.getProperty("java.vm.name"));
                dbSetMap.put("javaVmVersion", properties.getProperty("java.vm.version"));
                dbSetMap.put("javaVmVendor", properties.getProperty("java.vm.vendor"));
                dbSetMap.put("osName", properties.getProperty("os.name"));
                dbSetMap.put("osArch", properties.getProperty("os.arch"));
                dbSetMap.put("userName", properties.getProperty("user.name"));
                dbSetMap.put("userCountry", properties.getProperty("user.country"));

                dbSetMap.put("currentYear", dateFormat2.format(date));
                dbSetMap.put("tableName", code);
                dbSetMap.put("dataBase", dataBaseName);
                dbSetMap.put("createDateTime", dateFormat.format(date));
                dbSetMap.put("tableColumns", newColumns);
                dbSetMap.put("columnNum", newColumns.size());
                dbSetMap.put("pkList", newPKList);
                dbSetMap.put("pkNum", newPKList.size());

                String PKWhereClause = ConvertPDM.getPKWhereClause(pkList);
                String InsertColumnClause = convertPDM.getInsertColumnClause(newColumns);
                String UpdateColumnClause = convertPDM.getUpdateColumnClause(newColumns);
                dbSetMap.put("PKWhereClause", PKWhereClause);
                dbSetMap.put("InsertColumnClause", InsertColumnClause);
                dbSetMap.put("UpdateColumnClause", UpdateColumnClause);

                CommonUtil.printFile(dbSetMap, setTemplate, "D:\\MyWork\\Schema\\", code + "DBSet.java");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createDb() throws IOException {
        Template setTemplate = configuration.getTemplate("db.ftl");
        try {
            Parser parser = new Parser();
            PDM pdm = parser.pdmParser("D:\\MyWork\\Schema\\NoRealBlcDtl.pdm");
            // PDM pdm = parser.pdmParser("D:\\MyWork\\Schema\\LifeInsurancePersonal.pdm");
            String dataBaseName = pdm.getName();
            Properties properties = System.getProperties();
            ConvertPDM convertPDM = new ConvertPDM();
            convertPDM.setDBMSType(DBConst.DB_Oracle);
            convertPDM.setAllowErrorInPDM(false);
            convertPDM.setAllowJavaType(false);
            convertPDM.setAllowJavaMath(false);

            ArrayList<PDMTable> tables = pdm.getTables();
            for (PDMTable table : tables) {
                String code = table.getCode();
                ArrayList<PDMKey> keys = table.getKeys();
                PDMKey primaryKey = table.getPrimaryKey();
                ArrayList<PDMColumn> pkList = primaryKey.getColumns();
                ArrayList<PDMColumn> newPKList = convertPDM.ConvertColumnType(code, pkList);
                // System.out.println(pkList);
                ArrayList<PDMColumn> columns = table.getColumns();
                ArrayList<PDMColumn> newColumns = convertPDM.ConvertColumnType(code, columns);
                // System.out.println(newColumns);

                Date date = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
                SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy");

                Map<String, Object> dbSetMap = new HashMap<>();
                dbSetMap.put("javaVmName", properties.getProperty("java.vm.name"));
                dbSetMap.put("javaVmVersion", properties.getProperty("java.vm.version"));
                dbSetMap.put("javaVmVendor", properties.getProperty("java.vm.vendor"));
                dbSetMap.put("osName", properties.getProperty("os.name"));
                dbSetMap.put("osArch", properties.getProperty("os.arch"));
                dbSetMap.put("userName", properties.getProperty("user.name"));
                dbSetMap.put("userCountry", properties.getProperty("user.country"));

                dbSetMap.put("currentYear", dateFormat2.format(date));
                dbSetMap.put("tableName", code);
                dbSetMap.put("dataBase", dataBaseName);
                dbSetMap.put("createDateTime", dateFormat.format(date));
                dbSetMap.put("tableColumns", newColumns);
                dbSetMap.put("columnNum", newColumns.size());
                dbSetMap.put("pkList", newPKList);
                dbSetMap.put("pkNum", newPKList.size());

                String PKWhereClause = ConvertPDM.getPKWhereClause(pkList);
                String InsertColumnClause = convertPDM.getInsertColumnClause(newColumns);
                String UpdateColumnClause = convertPDM.getUpdateColumnClause(newColumns);
                dbSetMap.put("PKWhereClause", PKWhereClause);
                dbSetMap.put("InsertColumnClause", InsertColumnClause);
                dbSetMap.put("UpdateColumnClause", UpdateColumnClause);

                CommonUtil.printFile(dbSetMap, setTemplate, "D:\\MyWork\\Schema\\", code + "DB.java");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
