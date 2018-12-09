package com.example.windows10.adminwisatabawean.Model;

import com.google.gson.annotations.SerializedName;

public class Kategori {
    @SerializedName( "id_kategori" )
    private String idKategori ;
    @SerializedName ( "nama_kategori" )
    private String namaKategori ;


    private String action ;
    public Kategori ( String idKategori , String namaKategori, String action ) {
        this . idKategori = idKategori ;
        this . namaKategori = namaKategori ;
        this . action = action ;
    }
    public String getIdKategori () {
        return idKategori ;
    }
    public void setIdKategori ( String idKategori ) {
        this . idKategori = idKategori ;
    }
    public String getNamaKategori () {
        return namaKategori ;
    }
    public void setNamaKategori ( String namaKategori ) {
        this . namaKategori = namaKategori ;
    }

}
