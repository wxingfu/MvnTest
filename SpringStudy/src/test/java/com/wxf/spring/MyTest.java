package com.wxf.spring;

import com.wxf.spring.pdm.*;
import com.wxf.spring.service.LDMaxNoService;
import com.wxf.spring.utils.CommonUtil;
import freemarker.template.Configuration;
import lombok.extern.slf4j.Slf4j;
import oracle.jdbc.driver.OracleDriver;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.jdbc.support.rowset.SqlRowSetMetaData;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Slf4j
@SpringBootTest
public class MyTest {

    @Autowired
    private Configuration configuration;

    @Test
    public void executeAll() throws Exception {
        createSchema();
        // createSet();
        // createDbSet();
        // createDb();
    }


    public Map<String, Object> getSystemProperties() {
        Properties properties = System.getProperties();
        Map<String, Object> propMap = new HashMap<>();
        propMap.put("javaVmName", properties.getProperty("java.vm.name"));
        propMap.put("javaVmVersion", properties.getProperty("java.vm.version"));
        propMap.put("javaVmVendor", properties.getProperty("java.vm.vendor"));
        propMap.put("osName", properties.getProperty("os.name"));
        propMap.put("osArch", properties.getProperty("os.arch"));
        propMap.put("userName", properties.getProperty("user.name"));
        propMap.put("userCountry", properties.getProperty("user.country"));
        return propMap;
    }

    @Test
    public void createSchema() throws Exception {
        String rootPath = "D:\\MyWork\\Schema\\";
        try {
            Parser parser = new Parser();
            // PDM pdm = parser.pdmParser("D:\\MyWork\\Schema\\LDPrdDeploy.pdm");
            // PDM pdm = parser.pdmParser("D:\\MyWork\\Schema\\NoRealBlcDtl.pdm");
            PDM pdm = parser.pdmParser(rootPath + "LifeInsurancePersonal.pdm");

            String dataBaseName = pdm.getName();
            Map<String, Object> systemProperties = getSystemProperties();

            ConvertPDM convertPDM = new ConvertPDM();
            convertPDM.setDBMSType(DBConst.DB_Oracle);
            convertPDM.setAllowErrorInPDM(false); // 转换过程不允许跳过错误
            convertPDM.setAllowJavaType(false); // 转换时使用基本类型
            convertPDM.setAllowJavaMath(false); // 转换时不使用java.math类型

            ArrayList<PDMTable> tables = pdm.getTables();
            for (PDMTable table : tables) {

                String tableCode = table.getCode();
                PDMKey primaryKey = table.getPrimaryKey();

                ArrayList<PDMColumn> pkList = primaryKey.getColumns();
                ArrayList<PDMColumn> newPKList = convertPDM.ConvertColumnType(tableCode, pkList);

                ArrayList<PDMColumn> columns = table.getColumns();
                ArrayList<PDMColumn> newColumns = convertPDM.ConvertColumnType(tableCode, columns);

                String PKWhereClause = ConvertPDM.getPKWhereClause(pkList);
                String InsertColumnClause = convertPDM.getInsertColumnClause(newColumns);
                String UpdateColumnClause = convertPDM.getUpdateColumnClause(newColumns);

                Map<String, Object> dataMap = new HashMap<>();
                dataMap.put("sysProp", systemProperties);
                dataMap.put("tableName", tableCode);
                dataMap.put("dataBase", dataBaseName);
                dataMap.put("tableColumns", newColumns);
                dataMap.put("pkList", newPKList);
                dataMap.put("PKWhereClause", PKWhereClause);
                dataMap.put("InsertColumnClause", InsertColumnClause);
                dataMap.put("UpdateColumnClause", UpdateColumnClause);

                CommonUtil.printFile(dataMap, configuration.getTemplate("schema.ftl"), rootPath, tableCode + "Schema.java");
                CommonUtil.printFile(dataMap, configuration.getTemplate("set.ftl"), rootPath, tableCode + "Set.java");
                CommonUtil.printFile(dataMap, configuration.getTemplate("dbset.ftl"), rootPath, tableCode + "DBSet.java");
                CommonUtil.printFile(dataMap, configuration.getTemplate("db.ftl"), rootPath, tableCode + "DB.java");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


   /* @Test
    public void createSet() throws IOException {
        Template setTemplate = configuration.getTemplate("set.ftl");
        try {
            Parser parser = new Parser();
            // PDM pdm = parser.pdmParser("D:\\MyWork\\Schema\\NoRealBlcDtl.pdm");
            PDM pdm = parser.pdmParser("D:\\MyWork\\Schema\\LifeInsurancePersonal.pdm");

            String dataBaseName = pdm.getName();
            Map<String, Object> systemProperties = getSystemProperties();

            ConvertPDM convertPDM = new ConvertPDM();
            convertPDM.setDBMSType(DBConst.DB_Oracle);
            convertPDM.setAllowErrorInPDM(false); // 转换过程不允许跳过错误
            convertPDM.setAllowJavaType(false); // 转换时使用基本类型
            convertPDM.setAllowJavaMath(false); // 转换时不使用java.math类型

            ArrayList<PDMTable> tables = pdm.getTables();
            for (PDMTable table : tables) {
                String code = table.getCode();

                Map<String, Object> setMap = new HashMap<>();
                setMap.put("sysProp", systemProperties);

                setMap.put("tableName", code);
                setMap.put("dataBase", dataBaseName);
                CommonUtil.printFile(setMap, setTemplate, "D:\\MyWork\\Schema\\", code + "Set.java");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createDbSet() throws IOException {
        Template dbSetTemplate = configuration.getTemplate("dbset.ftl");
        try {
            Parser parser = new Parser();
            // PDM pdm = parser.pdmParser("D:\\MyWork\\Schema\\NoRealBlcDtl.pdm");
            PDM pdm = parser.pdmParser("D:\\MyWork\\Schema\\LifeInsurancePersonal.pdm");

            String dataBaseName = pdm.getName();
            Map<String, Object> systemProperties = getSystemProperties();

            ConvertPDM convertPDM = new ConvertPDM();
            convertPDM.setDBMSType(DBConst.DB_Oracle);
            convertPDM.setAllowErrorInPDM(false); // 转换过程不允许跳过错误
            convertPDM.setAllowJavaType(false); // 转换时使用基本类型
            convertPDM.setAllowJavaMath(false); // 转换时不使用java.math类型

            ArrayList<PDMTable> tables = pdm.getTables();
            for (PDMTable table : tables) {
                String code = table.getCode();
                PDMKey primaryKey = table.getPrimaryKey();
                ArrayList<PDMColumn> pkList = primaryKey.getColumns();
                ArrayList<PDMColumn> newPKList = convertPDM.ConvertColumnType(code, pkList);
                ArrayList<PDMColumn> columns = table.getColumns();
                ArrayList<PDMColumn> newColumns = convertPDM.ConvertColumnType(code, columns);

                Map<String, Object> dbSetMap = new HashMap<>();
                dbSetMap.put("sysProp", systemProperties);
                dbSetMap.put("tableName", code);
                dbSetMap.put("dataBase", dataBaseName);
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
                CommonUtil.printFile(dbSetMap, dbSetTemplate, "D:\\MyWork\\Schema\\", code + "DBSet.java");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createDb() throws IOException {
        Template dbTemplate = configuration.getTemplate("db.ftl");
        try {
            Parser parser = new Parser();
            // PDM pdm = parser.pdmParser("D:\\MyWork\\Schema\\NoRealBlcDtl.pdm");
            PDM pdm = parser.pdmParser("D:\\MyWork\\Schema\\LifeInsurancePersonal.pdm");
            String dataBaseName = pdm.getName();
            Map<String, Object> systemProperties = getSystemProperties();
            ConvertPDM convertPDM = new ConvertPDM();
            convertPDM.setDBMSType(DBConst.DB_Oracle);
            convertPDM.setAllowErrorInPDM(false);
            convertPDM.setAllowJavaType(false);
            convertPDM.setAllowJavaMath(false);

            ArrayList<PDMTable> tables = pdm.getTables();
            for (PDMTable table : tables) {
                String code = table.getCode();
                PDMKey primaryKey = table.getPrimaryKey();
                ArrayList<PDMColumn> pkList = primaryKey.getColumns();
                ArrayList<PDMColumn> newPKList = convertPDM.ConvertColumnType(code, pkList);
                ArrayList<PDMColumn> columns = table.getColumns();
                ArrayList<PDMColumn> newColumns = convertPDM.ConvertColumnType(code, columns);

                Map<String, Object> dbMap = new HashMap<>();
                dbMap.put("sysProp", systemProperties);

                dbMap.put("tableName", code);
                dbMap.put("dataBase", dataBaseName);
                dbMap.put("tableColumns", newColumns);
                dbMap.put("columnNum", newColumns.size());
                dbMap.put("pkList", newPKList);
                dbMap.put("pkNum", newPKList.size());

                String PKWhereClause = ConvertPDM.getPKWhereClause(pkList);
                String InsertColumnClause = convertPDM.getInsertColumnClause(newColumns);
                String UpdateColumnClause = convertPDM.getUpdateColumnClause(newColumns);
                dbMap.put("PKWhereClause", PKWhereClause);
                dbMap.put("InsertColumnClause", InsertColumnClause);
                dbMap.put("UpdateColumnClause", UpdateColumnClause);

                CommonUtil.printFile(dbMap, dbTemplate, "D:\\MyWork\\Schema\\", code + "DB.java");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/


    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Test
    public void test() throws SQLException {
        String sql = "select * from LDMaxNo where rownum <= 1";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
        SqlRowSetMetaData rowSetMetaData = rowSet.getMetaData();
        int columnCount = rowSetMetaData.getColumnCount();
        for (int i = 0; i < columnCount; i++) {
            String columnClassName = rowSetMetaData.getColumnClassName(i + 1);
            System.out.println(columnClassName);
            int columnType = rowSetMetaData.getColumnType(i + 1);
            String columnTypeName = rowSetMetaData.getColumnTypeName(i + 1);
            String columnName = rowSetMetaData.getColumnName(i + 1);
            System.out.println(columnType + " " + columnTypeName + " " + columnName);
        }
    }

    @Autowired
    private LDMaxNoService ldMaxNoService;

    @Test
    public void test3() {
        String no = ldMaxNoService.CreateMaxNo("aaaaaa", 10);
        System.out.println(no);
        log.error(no);
    }

    @Test
    public void test2() {
        Connection connect = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            Driver driver = new OracleDriver();
            DriverManager.deregisterDriver(driver);
            Properties pro = new Properties();
            pro.put("user", "test");
            pro.put("password", "ff514519");
            connect = driver.connect("jdbc:oracle:thin:@localhost:1521:orcl", pro);
            System.out.println(connect);

            PreparedStatement preState = connect.prepareStatement("select * from LDMaxNo where rownum <= 1");
            boolean execute = preState.execute();
            System.out.println(execute);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connect != null) connect.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
