package com.example.windows10.adminwisatabawean.Model;

import com.google.gson.annotations.SerializedName;

public class PostPutDelKategori {
    @SerializedName( "status" )
    String status ;
    @SerializedName ( "result" )
    Kategori mKategori ;
    @SerializedName ( "message" )
    String message ;
    public String getStatus () {
        return status ;
    }
    public void setStatus ( String status ) {
        this . status = status ;
    }
    public String getMessage () {
        return message ;
    }
    public void setMessage ( String message )
    {
        this . message = message ;
    }
    public Kategori getKategori () {
        return mKategori ;
    }
    public void setKategori ( Kategori kategori ) {
        mKategori = kategori ;
    }
}
