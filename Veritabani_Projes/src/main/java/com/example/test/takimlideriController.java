package com.example.test;

import com.example.test.HelloApplication;
import com.example.test.UserSession;
import com.example.test.itirazGormeListesi;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.event.ActionEvent; // Düzeltme: javafx.event paketini import etmek

import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import static com.example.test.HelloApplication.connection;

public class takimlideriController {

    private ObservableList<itirazGormeListesi> itirazGormeListesiData = FXCollections.observableArrayList();

    @FXML
    private TableColumn<itirazGormeListesi, String> Asistan;

    @FXML
    private TableColumn<itirazGormeListesi, String> Durumlar;

    @FXML
    private Button b_cagri;

    @FXML
    private TableColumn<itirazGormeListesi, String> itirazAciklamasi;

    @FXML
    private TableColumn<itirazGormeListesi, Date> itiraz_tarihi;

    @FXML
    private TableColumn<itirazGormeListesi, Integer> prim_ID;

    @FXML
    private TableColumn<itirazGormeListesi, Integer> sicilNo;

    @FXML
    private TableView<itirazGormeListesi> view_Itiraz;

    String Vasistan;
    int Vprim_ID;
    int VsicilNo;
    String VitirazAciklamasi;
    Date Vitiraz_tarihi;
    String Vdurum;

    @FXML
    void initialize() {
        Asistan.setCellValueFactory(new PropertyValueFactory<>("asistan"));
        prim_ID.setCellValueFactory(new PropertyValueFactory<>("prim_ID"));
        sicilNo.setCellValueFactory(new PropertyValueFactory<>("sicilNo"));
        itirazAciklamasi.setCellValueFactory(new PropertyValueFactory<>("itirazAciklamasi"));
        itiraz_tarihi.setCellValueFactory(new PropertyValueFactory<>("itiraz_tarihi"));
        Durumlar.setCellValueFactory(new PropertyValueFactory<>("durum"));
        view_Itiraz.setItems(itirazGormeListesiData);
    }

    public takimlideriController() throws SQLException, ClassNotFoundException {
        HelloApplication.openDatabaseConnection();
        try {
            System.out.println(UserSession.getInstance().getUserId());
            System.out.println("Bağlantı kuruldu.");
            if (connection != null && !connection.isClosed()) {
                String sql = "Select * from Itiraz_Listesi";
                PreparedStatement ps1 = connection.prepareStatement(sql);
                ResultSet resultSet = ps1.executeQuery();
                while (resultSet.next()) {
                    Vasistan = resultSet.getString("Asistan");
                    Vprim_ID = resultSet.getInt("prim_ID");
                    VsicilNo = resultSet.getInt("sicilNo");
                    VitirazAciklamasi = resultSet.getString("itirazAcıklamasi");
                    Vitiraz_tarihi = resultSet.getDate("itiraz_tarih");
                    Vdurum = resultSet.getString("durumlar");
                    itirazGormeListesiData.add(new itirazGormeListesi(Vasistan, Vprim_ID, VsicilNo, VitirazAciklamasi, new java.sql.Date(Vitiraz_tarihi.getTime()), Vdurum));
                }
            }
        } catch (SQLException e) {
            System.out.println("Veritabanına bağlanırken hata oluştu: " + e.getMessage());
            e.printStackTrace();
        }
    }



    @FXML
    void cagri(ActionEvent event) {
        // Buraya çağrı işlevselliğini ekleyin
    }

    @FXML
    void itiraz(ActionEvent event) {
        // Buraya itiraz işlevselliğini ekleyin
    }
}
