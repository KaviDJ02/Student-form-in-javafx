module com.example.student {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;

    opens com.example.student to javafx.fxml;
    opens com.example.student.controller to javafx.fxml;
    exports com.example.student;
}