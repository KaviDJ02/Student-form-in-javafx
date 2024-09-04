package com.example.student.controller;

import com.example.student.db.DatabaseConnection;
import com.example.student.dto.Student;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class StudentController {

    @FXML
    private TextField studentIDField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField courseField;
    @FXML
    private Label messageLabel;

    @FXML
    protected void onUpdateButtonClick() {
        try {
            int studentID = Integer.parseInt(studentIDField.getText());
            Student student = DatabaseConnection.getStudentById(studentID);

            if (student != null) {
                student.setName(nameField.getText());
                student.setEmail(emailField.getText());
                student.setCourse(courseField.getText());
                boolean success = DatabaseConnection.updateStudent(student);
                if (success) {
                    messageLabel.setText("Update successful!");
                } else {
                    messageLabel.setText("Update failed!");
                }
            } else {
                messageLabel.setText("Student not found!");
            }
        } catch (Exception e) {
            messageLabel.setText("Error updating student: " + e.getMessage());
        }
    }

    @FXML
    protected void onRetrieveButtonClick() {
        try {
            int studentID = Integer.parseInt(studentIDField.getText());
            Student student = DatabaseConnection.getStudentById(studentID);

            if (student != null) {
                nameField.setText(student.getName());
                emailField.setText(student.getEmail());
                courseField.setText(student.getCourse());
                messageLabel.setText("Student retrieved successfully!");
            } else {
                messageLabel.setText("Student not found!");
            }
        } catch (Exception e) {
            messageLabel.setText("Error retrieving student: " + e.getMessage());
        }
    }
}