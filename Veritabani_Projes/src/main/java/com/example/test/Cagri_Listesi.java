package com.example.test;

import java.sql.Date;

public class Cagri_Listesi {
    private String asistan;
    private String musteri;
    private String tarih;
    private String s_basla;
    private String s_bitis;
    private String durum;
    private String konu;

    public Cagri_Listesi(String asistan, String musteri, String tarih, String s_basla, String s_bitis, String durum, String konu) {
        this.asistan = asistan;
        this.musteri = musteri;
        this.tarih = tarih;
        this.s_basla = s_basla;
        this.s_bitis = s_bitis;
        this.durum = durum;
        this.konu = konu;
    }

    public Cagri_Listesi(String asistan, int primId, int sicilNo, String itirazAcıklamasi, Date itirazTarih, String durumlar) {
    }

    // Getters
    public String getAsistan() { return asistan; }
    public String getMusteri() { return musteri; }
    public String getTarih() { return tarih; }
    public String getS_basla() { return s_basla; }
    public String getS_bitis() { return s_bitis; }
    public String getDurum() { return durum; }
    public String getKonu() { return konu; }
}
