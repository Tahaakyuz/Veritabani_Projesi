package com.example.test;

import java.util.Date;

public class Itiraz_Listesi {
    private String asistan;
    private Integer sicilNo;
    private String itirazAciklamasi;
    private Date itiraz_tarih; // Changed to Date type
    private String durumlar;

    public Itiraz_Listesi(String asistan, Integer sicilNo, String itirazAciklamasi, Date itiraz_tarih, String durumlar) {
        this.asistan = asistan;
        this.sicilNo = sicilNo;
        this.itirazAciklamasi = itirazAciklamasi;
        this.itiraz_tarih = itiraz_tarih;
        this.durumlar = durumlar;
    }

    // Getters
    public String getAsistan() {
        return asistan;
    }

    public Integer getSicilNo() { // Corrected method name
        return sicilNo;
    }

    public String getItirazAciklamasi() { // Corrected method name
        return itirazAciklamasi;
    }

    public Date getItiraz_tarih() { // Corrected method name
        return itiraz_tarih;
    }

    public String getDurumlar() {
        return durumlar;
    }
}
