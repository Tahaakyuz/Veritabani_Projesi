package com.example.test;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import static com.example.test.HelloApplication.connection;

public class girisController {

    @FXML
    private Button button_Giris;

    @FXML
    private TextField text_Kad;

    @FXML
    private TextField text_Sifre;


    @FXML
    void giris(ActionEvent event) throws SQLException {
        String kullanici_ad = text_Kad.getText();
        String sifre = text_Sifre.getText();
        System.out.println("Dogru");
        try {
            HelloApplication.openDatabaseConnection();
            if (connection != null) {
                String sql = "SELECT * FROM asistan";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    String k_ad = resultSet.getString("ad");
                    String k_soyad = resultSet.getString("soyad");
                    String k_sifre = resultSet.getString("sifre");
                    int asistan_ID = resultSet.getInt("asistan_ID");
                    UserSession.getInstance().setUserId(asistan_ID);
                    if (kullanici_ad.equals(k_ad + k_soyad) && sifre.equals(k_sifre)) {
                        HelloApplication.loadScene(event,"hello-view.fxml");
                        return;
                    }
                    else System.out.println("Sifre Yanlış");
                }
                sql = "Select * From takimLideri";
                PreparedStatement ps1 = connection.prepareStatement(sql);
                resultSet = ps1.executeQuery();
                if (resultSet.next()) {
                    String k_ad = resultSet.getString("ad");
                    String k_soyad = resultSet.getString("soyad");
                    String k_sifre = resultSet.getString("sifre");
                    if (kullanici_ad.equals(k_ad + k_soyad) && sifre.equals(k_sifre)) {
                        HelloApplication.loadScene(event,"TakimLideri.fxml");
                        return;
                    }
                    else System.out.println("Sifre Yanlış");

                }

            } else {
                System.out.println("Hata1");
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            HelloApplication.closeDatabaseConnection();
        }
    }
}


