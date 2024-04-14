package com.weixf.jdbc.base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class JDBCInjection {
    public static void main(String[] args) throws Exception {
        // 1.注册驱动 （可以省略）

        // 2.获取连接对象
        Connection connection = DriverManager.getConnection("jdbc:mysql:///atguigu", "root", "atguigu");

        // 3.获取执行SQL语句对象
        Statement statement = connection.createStatement();

        System.out.println("请输入员工姓名：");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();

        // 4.编写SQL语句，并执行，接受返回的结果
        String sql = "SELECT emp_id,emp_name,emp_salary,emp_age FROM t_emp WHERE emp_name = '" + name + "'";
        ResultSet resultSet = statement.executeQuery(sql);

        // 5.处理结果：遍历resultSet
        while (resultSet.next()) {
            int empId = resultSet.getInt("emp_id");
            String empName = resultSet.getString("emp_name");
            double empSalary = resultSet.getDouble("emp_salary");
            int empAge = resultSet.getInt("emp_age");
            System.out.println(empId + "\t" + empName + "\t" + empSalary + "\t" + empAge);
        }

        // 6.释放资源
        resultSet.close();
        statement.close();
        connection.close();
    }
}
