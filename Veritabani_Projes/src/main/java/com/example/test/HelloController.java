package com.example.test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;

import static com.example.test.HelloApplication.connection;

public class HelloController {
    @FXML
    private TableColumn<Cagri_Listesi, String> Asistan;
    @FXML
    private TableColumn<Cagri_Listesi, String> Baslangic;
    @FXML
    private TableColumn<Cagri_Listesi, String> Bitis;
    @FXML
    private TableView<Cagri_Listesi> Cagri_Listesi;
    @FXML
    private TableColumn<Cagri_Listesi, String> Konu;
    @FXML
    private TableColumn<Cagri_Listesi, String> Musteri;
    @FXML
    private TableColumn<Cagri_Listesi, String> Tarih;


    @FXML
    private AnchorPane pItirazEtme;
    @FXML
    private Button button_itirazG;

    @FXML
    private TextArea tfield_Itiraz;

    @FXML
    private Button b_cagri;
    @FXML
    private Button b_itiraz;
    @FXML
    private Button b_prim;
    @FXML
    private AnchorPane pCagri;
    @FXML
    private AnchorPane pItiraz;
    @FXML
    private AnchorPane pPrim;

    @FXML
    private TableView<Itiraz_Listesi> Itiraz;

    @FXML
    private Button button_itiraz;

    @FXML
    private Button button_Cagri;
    

    @FXML
    private TableColumn<Itiraz_Listesi, String> iAciklama;

    @FXML
    private TableColumn<Itiraz_Listesi, String> iAsistan;

    @FXML
    private TableColumn<Itiraz_Listesi, Integer> iSicil;

    @FXML
    private TableColumn<Itiraz_Listesi, Date> iTarih;

    @FXML
    private TableColumn<Itiraz_Listesi, String> idurum;





    private ObservableList<Cagri_Listesi> cagriListesiData = FXCollections.observableArrayList();
    private ObservableList<Itiraz_Listesi> itirazListesiData = FXCollections.observableArrayList();
     //test

    int prim_ID;
    Date tarih;
    int primHaketme_ID;

    public HelloController() throws SQLException, ClassNotFoundException {
        // Verileri yükle
        loadDataFromDatabase();



    }

    @FXML
    void initialize() {
        Asistan.setCellValueFactory(new PropertyValueFactory<>("asistan"));
        Musteri.setCellValueFactory(new PropertyValueFactory<>("musteri"));
        Tarih.setCellValueFactory(new PropertyValueFactory<>("tarih"));
        Baslangic.setCellValueFactory(new PropertyValueFactory<>("s_basla"));
        Bitis.setCellValueFactory(new PropertyValueFactory<>("s_bitis"));
        Konu.setCellValueFactory(new PropertyValueFactory<>("konu"));

        Cagri_Listesi.setItems(cagriListesiData);


        iAsistan.setCellValueFactory(new PropertyValueFactory<>("asistan"));
        iSicil.setCellValueFactory(new PropertyValueFactory<>("sicilNo"));
        iAciklama.setCellValueFactory(new PropertyValueFactory<>("itirazAciklamasi"));
        iTarih.setCellValueFactory(new PropertyValueFactory<>("itiraz_tarih"));
        idurum.setCellValueFactory(new PropertyValueFactory<>("durumlar"));

        Itiraz.setItems(itirazListesiData);
    }



    @FXML
    void cagri(ActionEvent event) {
        TableVisiblity(1);

    }

    @FXML
    void itiraz(ActionEvent event) {
        TableVisiblity(3);
    }

    @FXML
    void prim(ActionEvent event) {
        TableVisiblity(2);
        TableVisiblity(2);
        // Prim butonu için işlev ekleyin

    }

    public void TableVisiblity(int GirisSayisi)
    {
        switch(GirisSayisi)
        {

            case 1:
                pCagri.setVisible(true);
                pItiraz.setVisible(false);
                pPrim.setVisible(false);
                pItirazEtme.setVisible(false);
                break;
            case 2:
                pCagri.setVisible(false);
                pItiraz.setVisible(true);
                pPrim.setVisible(false);
                pItirazEtme.setVisible(false);
                break;
            case 3:
                pCagri.setVisible(false);
                pItiraz.setVisible(false);
                pPrim.setVisible(true);
                pItirazEtme.setVisible(false);
                break;
            case 4:
                pCagri.setVisible(false);
                pItiraz.setVisible(false);
                pPrim.setVisible(false);
                pItirazEtme.setVisible(true);
                break;

        }
    }



    @FXML
    void itirazet(ActionEvent event) throws SQLException, ClassNotFoundException {
        TableVisiblity(4);

        try {
            HelloApplication.openDatabaseConnection();
            if (connection != null && !connection.isClosed()) { // Bağlantının kapalı olup olmadığını kontrol et
                String sql = "SELECT * FROM asistanPrimi WHERE asistan_ID = ?";
                PreparedStatement ps1 = connection.prepareStatement(sql);
                ps1.setInt(1, UserSession.getInstance().getUserId());
                ResultSet resultSet = ps1.executeQuery();
                System.out.println("Sorgu çalışıyor.");
                if (resultSet.next()) {
                    prim_ID = resultSet.getInt("prim_ID");
                    tarih = resultSet.getDate("yil");
                    primHaketme_ID = resultSet.getInt("primHaketme_ID");
                }
            }
            else {
                System.out.println("Veritabanına bağlantı sağlanamadı.");
            }
        } catch (SQLException e) {
            System.out.println("Veritabanına bağlanırken hata oluştu: " + e.getMessage());
            e.printStackTrace();
        }finally {
            try {
                HelloApplication.closeDatabaseConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }





    @FXML
    void itirazgonder(ActionEvent event) throws SQLException, ClassNotFoundException {


        try {
            HelloApplication.openDatabaseConnection();
            System.out.println(UserSession.getInstance().getUserId());
            System.out.println("Bağlantı kuruldu.");
            if (connection != null && !connection.isClosed()) { // Bağlantının kapalı olup olmadığını kontrol et
                String sql = "Insert into itirazlar(asistan_ID,prim_ID,itirazAcıklamasi,itirazDurum_ID,itiraz_tarih) Values (?,?,?,?,?) ";
                PreparedStatement ps1 = connection.prepareStatement(sql);
                ps1.setInt(1, UserSession.getInstance().getUserId());
                ps1.setInt(2,prim_ID);
                ps1.setString(3,tfield_Itiraz.getText());
                ps1.setInt(4,primHaketme_ID);
                ps1.setDate(5, java.sql.Date.valueOf(LocalDate.now()));
                int affectedRows = ps1.executeUpdate();

                if (affectedRows > 0) {
                    System.out.println("Sorgu çalışıyor.");
                }
            }
            else {
                System.out.println("Veritabanına bağlantı sağlanamadı.");
            }
        } catch (SQLException e) {
            System.out.println("Veritabanına bağlanırken hata oluştu: " + e.getMessage());
            e.printStackTrace();
        }finally {
            try {
                HelloApplication.closeDatabaseConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }



    }

    public void Yenicagri()
    {

    }
    public void loadDataFromDatabase() {
        try {
            HelloApplication.openDatabaseConnection();
            if (connection != null) {
                String sql = "SELECT * FROM Cagri_Listesi";
                PreparedStatement ps1 = connection.prepareStatement(sql);
                ResultSet resultSet = ps1.executeQuery();
                while (resultSet.next()) {
                    String asistan = resultSet.getString("Asistan");
                    String musteri = resultSet.getString("Musteri");
                    String tarih = resultSet.getString("gorusmeTarih");
                    String s_basla = resultSet.getString("baslamaSaati");
                    String s_bitis = resultSet.getString("bitisSaati");
                    String durum = resultSet.getString("durum");
                    String konu = resultSet.getString("konu");
                    cagriListesiData.add(new Cagri_Listesi(asistan, musteri, tarih, s_basla, s_bitis, durum, konu));
                }

                sql = "Select * from Itiraz_Listesi";
                PreparedStatement ps2= connection.prepareStatement(sql);
                resultSet = ps2.executeQuery();
                while (resultSet.next()) {
                    System.out.println("2. Sorguyu çekti");
                    String asistan = resultSet.getString("asistan");
                    int sicilNo = resultSet.getInt("sicilNo");
                    String itirazAciklamasi = resultSet.getString("itirazAcıklamasi");
                    Date itiraz_tarih = resultSet.getDate("itiraz_tarih");
                    String durum = resultSet.getString("durumlar");
                    itirazListesiData.add(new Itiraz_Listesi(asistan, sicilNo, itirazAciklamasi, itiraz_tarih, durum));
                }

            } else {
                System.out.println("Bağlantı yok");
            }
        } catch (SQLException e) {
            System.out.println("Veritabanına bağlanırken hata oluştu: " + e.getMessage());
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                HelloApplication.closeDatabaseConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
