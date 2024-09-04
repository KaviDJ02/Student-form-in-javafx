package com.example.student.model;

import com.example.student.db.DatabaseConnection;
import com.example.student.dto.Student;

import java.sql.SQLException;

public class StudentModel {

    public Student getStudent(int studentID) {
        return DatabaseConnection.getStudentById(studentID);
    }

    public boolean updateStudent(Student student) {
        return DatabaseConnection.updateStudent(student);
    }
}