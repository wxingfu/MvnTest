package com.weixf.j2ee.data_access_object;

import java.util.List;

public interface StudentDao {

    List<Student> getAllStudents();

    Student getStudent(int rollNo);

    void updateStudent(Student student);

    void deleteStudent(Student student);

}
