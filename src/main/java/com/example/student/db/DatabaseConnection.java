package com.example.student.db;

import com.example.student.dto.Student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseConnection {
    private static DatabaseConnection instance;
    private Connection connection;
    private String url = "jdbc:mysql://localhost:3306/studentDB";
    private String username = "root";
    private String password = "";

    private DatabaseConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            throw new SQLException(e);
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static DatabaseConnection getInstance() throws SQLException {
        if (instance == null) {
            instance = new DatabaseConnection();
        } else if (instance.getConnection().isClosed()) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public static Student getStudentById(int studentID) {
        Student student = null;
        try {
            Connection connection = getInstance().getConnection();
            String query = "SELECT * FROM students WHERE studentID = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, studentID);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                student = new Student();
                student.setStudentID(resultSet.getInt("studentID"));
                student.setName(resultSet.getString("name"));
                student.setEmail(resultSet.getString("email"));
                student.setCourse(resultSet.getString("course"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

    public static boolean updateStudent(Student student) {
        try {
            Connection connection = getInstance().getConnection();
            String query = "UPDATE students SET name = ?, email = ?, course = ? WHERE studentID = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, student.getName());
            statement.setString(2, student.getEmail());
            statement.setString(3, student.getCourse());
            statement.setInt(4, student.getStudentID());
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}