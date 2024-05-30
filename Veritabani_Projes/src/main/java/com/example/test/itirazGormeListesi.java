package com.example.test;

import java.sql.Date;

public class itirazGormeListesi {
    private String asistan;
    private int prim_ID;
    private int sicilNo;
    private String itirazAciklamasi;
    private Date itiraz_tarihi;
    private String durum;

    // Constructor
    public itirazGormeListesi(String asistan, int prim_ID, int sicilNo, String itirazAciklamasi, Date itiraz_tarihi, String durum) {
        this.asistan = asistan;
        this.prim_ID = prim_ID;
        this.sicilNo = sicilNo;
        this.itirazAciklamasi = itirazAciklamasi;
        this.itiraz_tarihi = itiraz_tarihi;
        this.durum = durum;
    }

    // Getter methods
    public String getAsistan() {
        return asistan;
    }

    public int getPrim_ID() {
        return prim_ID;
    }

    public int getSicilNo() {
        return sicilNo;
    }

    public String getItirazAciklamasi() {
        return itirazAciklamasi;
    }

    public Date getItiraz_tarihi() {
        return itiraz_tarihi;
    }

    public String getDurum() {
        return durum;
    }
}
