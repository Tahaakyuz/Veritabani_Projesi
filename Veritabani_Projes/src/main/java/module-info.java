module com.example.test {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;
    requires com.microsoft.sqlserver.jdbc;


    opens com.example.test to javafx.fxml;
    exports com.example.test;
}