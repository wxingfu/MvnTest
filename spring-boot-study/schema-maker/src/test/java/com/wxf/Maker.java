package com.wxf;

import com.wxf.config.Config;
import com.wxf.maker.MakeDB;
import com.wxf.maker.MakeDBSet;
import com.wxf.maker.MakeSchema;
import com.wxf.maker.MakeSet;
import com.wxf.pdm.PDM;
import com.wxf.pdm.Parser;
import com.wxf.table.Convert;
import com.wxf.table.Schema;
import com.wxf.utility.ComparePDM;
import com.wxf.utility.DBConst;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.lang.annotation.Target;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.wxf.config.Config.*;

/**
 * @author weixf
 * @since 2022-01-21
 */
@Slf4j
@SpringBootTest
public class Maker {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void test(){
        DataSource dataSource = jdbcTemplate.getDataSource();
        System.out.println(dataSource);
        // Connection connection = dataSource.getConnection();
    }


    @Test
    public void maker() throws Exception {
        log.info("<Start>");
        // Maker maker = new Maker();
        // maker.setPDMFileName("D:\\MyWork\\Schema\\LifeInsurancePersonal.pdm");
        Config.setPDMFileName("D:\\MyWork\\schema\\LifeInsurancePersonal.pdm");
        // maker.setPDMFileName("D:\\MyWork\\schema\\关爱联系人备份表(7).pdm");
        Config.setDBUserName("lis");
        Config.setOPFlag("DB");
        // maker.setOPFlag("ALL");
        Config.setSchemaOutputPATH("D:\\MyWork\\schema\\makfile\\");
        Config.setOneTableOprOutputPATH("D:\\MyWork\\schema\\makfile\\");
        Config.setPackageName("com.sinosoft.lis");
        Config.setTableName("");
        createJavaFile();
        log.info("<Over>");
    }


    private void validate() throws Exception {
        if (PDMFileName == null || "".equals(PDMFileName) || !(new File(PDMFileName)).exists()) {
            throw new Exception("PDM文件名为空或文件不存在:" + PDMFileName);
        }

        if (schemaOutputPATH == null || "".equals(schemaOutputPATH)) {
            throw new Exception("输出路径不能为空:" + schemaOutputPATH);
        }

        if (packageName == null && !Pattern.matches("[a-zA-Z\\.]+", packageName)) {
            throw new Exception("Package Name Error:" + packageName);
        }

        // if (OPFlag == null || "".equals(OPFlag) || (!"DB".equals(OPFlag) && !"OPR".equals(OPFlag) && !"ALL".equals(OPFlag)))
        if (!"DB".equals(OPFlag) && !"OPR".equals(OPFlag) && !"ALL".equals(OPFlag)) {
            throw new Exception("操作类型不合法：" + OPFlag);
        }

        if (DBUserName == null || "".equals(DBUserName)) {
            throw new Exception("数据库用户名不能为空：" + DBUserName);
        }
    }

    /**
     * 比较PDM和数据库有哪些不一致（数据类型判断依赖于JdbcUrl里的定义）<br/>
     * DB2的PDM的检查没有严格经过测试
     *
     * @param pdmFileName String
     * @throws Exception
     */
    public static void checkPDM(String pdmFileName) throws Exception {
        Parser tPDMParser = new Parser();
        tPDMParser.setAllowErrorInPDM(true);
        tPDMParser.setParserForeignKey(true);
        tPDMParser.setPrimaryKeyIsNull(false);
        log.info("start read PDM file");
        PDM tPDM = tPDMParser.readPDMFile(pdmFileName);
        log.info("end read PDM file");
        Convert tConvertPDM = new Convert();
        tConvertPDM.setAllowErrorInPDM(true);
        tConvertPDM.setDBMSType(DBConst.DB_Oracle);
        Schema tSchema = tConvertPDM.ConvertPDM(tPDM);
        checkDBType(tSchema);
        ComparePDM tComparePDM = new ComparePDM();
        tComparePDM.compareSchema(tSchema);
    }

    /**
     * 创建OR程序
     *
     * @throws Exception
     */
    public void createJavaFile() throws Exception {
        log.info(" validate at " + getTimestamp());
        validate();
        // 在连接池中建立一个连接
        log.info(" getConnection at " + getTimestamp());
        // DBConn con = null;
        // con = DBConnPool.getConnection();
        // if (con == null) {
        //     log.error("Connect failed!");
        //     System.exit(0);
        // }
        // con.close();
        // 解析PDM文件
        log.info("PDMParser at " + getTimestamp());
        Parser tPDMParser = new Parser();
        tPDMParser.setAllowErrorInPDM(false); // PDM上不能有错误
        tPDMParser.setParserForeignKey(false); // 生成Schema类不需要外键
        tPDMParser.setPrimaryKeyIsNull(false); // 生成Schema类必须有主键(推荐)
        PDM tPDM = tPDMParser.readPDMFile(PDMFileName);
        if (tPDM == null) {
            throw new Exception("解析PDM文件出错");
        }
        // 将PDM格式转换成MEM(内存)格式
        log.info("ConvertPDM at " + getTimestamp());
        Convert tConvertPDM = new Convert();
        tConvertPDM.setDBMSType(DBConst.DB_Oracle);
        tConvertPDM.setAllowErrorInPDM(false); // 转换过程不允许跳过错误
        tConvertPDM.setAllowJavaType(false); // 转换时使用基本类型
        tConvertPDM.setAllowJavaMath(false); // 转换时不使用java.math类型
        Schema tSchema = tConvertPDM.ConvertPDM(tPDM);
        // 检查PDM的数据库类型
        checkDBType(tSchema);
        // 删除以前生成的文件
        log.info("initOutputDir at " + getTimestamp());
        initOutputDir();
        // 循环处理Schema中的每个表
        // 删除以前生成的文件
        log.info(" MakeFile at " + getTimestamp());
        for (int i = 0; i < tSchema.getTabNum(); i++) {
            log.info("<Table " + (i + 1) + "> " + tSchema.getTable(i).getCode() + "(" + tSchema.getTable(i).getName() + ")");
            // Begin
            log.debug("Begin at " + getTimestamp());
            // 获得数据库的信息并作一些简单的检查
            log.debug("getDBInfo at " + getTimestamp());
            tSchema.getTable(i).setDBType(DBConst.DB_Oracle);
            tSchema.getTable(i).setJDBCVendor(DBConst.Vendor_Oracle);

            tSchema.getTable(i).getDBInfo(DBUserName.toUpperCase());

            tSchema.getTable(i).setLargeObj(false); // 对有大对象字段时不让生成某些函数
            tSchema.getTable(i).setLargeObjInPK(false); // 对有大对象主键字段时不让生成某些函数
            if ("DB".equals(OPFlag) || "ALL".equals(OPFlag)) {
                // 生成Schema.java
                log.debug("MakeSchema at " + getTimestamp());
                MakeSchema t1 = new MakeSchema(tSchema.getName());
                t1.setUserInfo(true); // 输出创建者个人信息
                t1.create(tSchema.getTable(i));
                // 生成DB.java
                log.debug("MakeDB at " + getTimestamp());
                MakeDB t2 = new MakeDB(tSchema.getName());
                t2.setUserInfo(true); // 输出创建者个人信息
                t2.isMultiCloseConn(false);
                t2.create(tSchema.getTable(i));
                // 生成Set.java
                log.debug("MakeSet at " + getTimestamp());
                MakeSet t3 = new MakeSet(tSchema.getName());
                t3.setUserInfo(true); // 输出创建者个人信息
                t3.create(tSchema.getTable(i));
                // 生成DBSet.java
                log.debug("MakeDBSet at " + getTimestamp());
                MakeDBSet t4 = new MakeDBSet(tSchema.getName());
                t4.setUserInfo(true); // 输出创建者个人信息
                t4.isMultiCloseConn(false);
                t4.create(tSchema.getTable(i));
            }
            // if ("OPR".equals(OPFlag) || "ALL".equals(OPFlag))// 创建单表操作文件
            // {
            //     // 生成单表操作的Input.jsp文件
            //     log.info("单表操作" + tSchema.getName() + "Input.jsp");
            //     MakeInput t5 = new MakeInput(tSchema.getName());
            //     t5.create(tSchema.getTable(i));
            //     // 生成单表操作的Init.jsp文件
            //     MakeInit t6 = new MakeInit(tSchema.getName());
            //     t6.create(tSchema.getTable(i));
            //     // 生成单表操作的js文件
            //     MakeJS t7 = new MakeJS(tSchema.getName());
            //     t7.create(tSchema.getTable(i));
            //     // 生成单表操作的save.jsp文件
            //     MakeSave t8 = new MakeSave(tSchema.getName());
            //     t8.create(tSchema.getTable(i));
            //     // 生成单表操作的OPUI.java文件
            //     MakeUI t9 = new MakeUI(tSchema.getName());
            //     t9.setUserInfo(true);
            //     t9.create(tSchema.getTable(i));
            //     // 生成单表操作的OPBL.java文件
            //     MakeBL t10 = new MakeBL(tSchema.getName());
            //     t10.setUserInfo(true);
            //     t10.create(tSchema.getTable(i));
            //
            // }
            // Over
            log.debug("End at " + getTimestamp());
        }
    }

    public static void checkDBType(Schema schema) {
        Pattern pc = Pattern.compile("\\s*(microsoft\\s+sql\\s+server|ibm\\s+db2\\s+udb|oracle)\\s+.*");
        Matcher m = pc.matcher(schema.getDBMSName().toLowerCase());
        if (!m.matches()) {
            try {
                throw new Exception("你正在处理的数据库为" + schema.getDBMSName() + "，为不支持的数据库类型，目前仅支持：Oracle,DB2 UDB,SQL Server");
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(0);
            }
        }
    }

    public static String getTimestamp() {
        String pattern = "yyyy-MM-dd HH:mm:ss SSS";
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        Date today = new Date();
        return df.format(today);
    }

    private void initOutputDir() throws Exception {
        File schemaOutputdir = new File(schemaOutputPATH);
        File oneTableOprOutputdir = new File(oneTableOprOutputPATH);
        if (schemaOutputdir.exists()) {
            deleteDirectory(schemaOutputdir);
        } else if (!schemaOutputdir.mkdirs()) {
            throw new IllegalArgumentException("创建输出目录" + schemaOutputPATH + "失败。");
        }
    }

    /**
     * 递归删除目录及下面的所有文件
     *
     * @param dir File
     * @throws IOException
     */
    private static void deleteDirectory(File dir) throws IOException {
        if (dir == null || !dir.isDirectory()) {
            throw new IllegalArgumentException("Argument " + dir + " is not a directory. ");
        } else {
            File[] entries = dir.listFiles();
            int sz = entries.length;
            for (int i = 0; i < sz; i++) {
                if (entries[i].isDirectory()) {
                    deleteDirectory(entries[i]);
                } else {
                    log.debug("     正在删除文件:" + entries[i].getPath());
                    entries[i].delete();
                }
            }
        }
    }


}
