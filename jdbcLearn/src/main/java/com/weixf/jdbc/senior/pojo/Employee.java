package com.weixf.jdbc.senior.pojo;

import com.weixf.jdbc.senior.anntation.Column;
import com.weixf.jdbc.senior.anntation.Table;

// 类名就是数据库表的t_后面的单词全写
@Table(name = "t_emp")
public class Employee {

    @Column(name = "emp_id")
    private Integer empId;// emp_id

    @Column(name = "emp_name")
    private String empName;// emp_name

    private Double empSalary;// emp_salary

    private Integer empAge;// emp_age

    public Employee() {
    }

    public Employee(Integer empId, String empName, Double empSalary, Integer empAge) {
        this.empId = empId;
        this.empName = empName;
        this.empSalary = empSalary;
        this.empAge = empAge;
    }

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public Double getEmpSalary() {
        return empSalary;
    }

    public void setEmpSalary(Double empSalary) {
        this.empSalary = empSalary;
    }

    public Integer getEmpAge() {
        return empAge;
    }

    public void setEmpAge(Integer empAge) {
        this.empAge = empAge;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empId=" + empId +
                ", empName='" + empName + '\'' +
                ", empSalary=" + empSalary +
                ", empAge=" + empAge +
                '}';
    }
}
