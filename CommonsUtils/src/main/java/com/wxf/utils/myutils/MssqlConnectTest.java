package com.wxf.utils.myutils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MssqlConnectTest {

    public static void main(String[] args) {
        // 1.准备数据库连接的基本数据
        /*用户名*/
        String username = "sa";
        /*密码*/
        String password = "ff514519";
        /*驱动包名*/
        String driverClass = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        /*连接地址*/
        String jdbcUrl = "jdbc:sqlserver://localhost:1433;databaseName=mybatis";

        try {
            // 2.加载驱动
            Class.forName(driverClass);
            // 3.获取数据库连接，填入连接地址、用户名、密码
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
            // 4.准备SQL语句，并且使用预编译SQL
            String sql = "select * from tbl_employee order by id offset 0 rows fetch next 10 rows only";
            PreparedStatement ps = connection.prepareStatement(sql);
            // 5.执行SQL语句，并获取结果集
            ResultSet resultSet = ps.executeQuery();
            // 6.获取列数
            ResultSetMetaData metaData = resultSet.getMetaData();
            int size = metaData.getColumnCount();
            // 7.将结果集读取出来
            List<List<Object>> list = new ArrayList<>();
            // 判断结果集是否为空
            while (resultSet.next()) {
                // 8.按列索引获取数据
                List<Object> objectList = new ArrayList<>();
                for (int i = 0; i < size; i++) {
                    Object object = resultSet.getObject(i + 1);
                    objectList.add(object);
                }
                list.add(objectList);
                // System.out.println(objectList);
            }
            System.out.println(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
