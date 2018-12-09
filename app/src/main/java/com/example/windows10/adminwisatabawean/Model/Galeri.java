package com.example.windows10.adminwisatabawean.Model;

import com.google.gson.annotations.SerializedName;

public class Galeri {
    @SerializedName( "id_galeri" )
    private String idGaleri ;
    @SerializedName ( "judul" )
    private String judul ;
    @SerializedName ( "tgl" )
    private String tgl ;
    @SerializedName ( "photo_url" )
    private String photoUrl ;
    private String action ;
    public Galeri ( String idGaleri , String judul , String tgl, String photoUrl , String action ) {
        this . idGaleri = idGaleri ;
        this . judul = judul ;
        this . tgl = tgl ;
        this . photoUrl = photoUrl ;
        this . action = action ;
    }
    public String getIdGaleri () {
        return idGaleri ;
    }
    public void setIdGaleri ( String idGaleri ) {
        this . idGaleri = idGaleri ;
    }
    public String getJudul () {
        return judul ;
    }
    public void setJudul ( String judul ) {
        this . judul = judul ;
    }
    public String getTgl () {
        return tgl ;
    }
    public void setTgl ( String tgl ) {
        this . tgl = tgl ;
    }
    public String getPhotoUrl () {
        return photoUrl ;
    }
    public void setPhotoUrl ( String photoUrl ) {
        this . photoUrl = photoUrl ;
    }
    public String getAction () {
        return action ;
    }
    public void setAction ( String action ) {
        this . action = action ;
    }
}
