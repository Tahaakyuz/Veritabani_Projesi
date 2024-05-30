package com.example.test;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {


    public static Connection connection;


    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }




    public void init() throws Exception{
        openDatabaseConnection();
    }



    public void stop() throws Exception{
        closeDatabaseConnection();
    }

    public static void loadScene(ActionEvent event, String fxmlFile) throws IOException {
        URL fxmlLocation = HelloApplication.class.getResource(fxmlFile);
        if (fxmlLocation == null) {
            throw new IOException("FXML dosyası bulunamadı: " + fxmlFile);
        }
        FXMLLoader loader = new FXMLLoader(fxmlLocation);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();
    }





public static Connection openDatabaseConnection() throws SQLException, ClassNotFoundException {
        System.out.println("Database Açıldı");
        String connectionUrl = "jdbc:sqlserver://DESKTOP-V5VS053;databaseName=telekomünikasyonDB;integratedSecurity=true;encrypt=true;trustServerCertificate=true";
        connection = DriverManager.getConnection(connectionUrl);
        DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());

        if (connection != null) {

        } else {
            System.out.println("Veritabanına bağlanırken hata oluştu!");
        }
    return null;
}
    public static Connection getConnection() {
        return connection;
    }






    public static void closeDatabaseConnection() throws SQLException {
        // Veritabanı bağlantısını kapat
        if (connection != null && !connection.isClosed()) {
            connection.close();
            System.out.println("Veritabanı bağlantısı kapatıldı.");
        }
    }




public static void main(String[] args) {

        launch();

    }
}