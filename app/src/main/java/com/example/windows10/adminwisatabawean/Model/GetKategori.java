package com.example.windows10.adminwisatabawean.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetKategori {
    @SerializedName( "status" )
    String status ;
    @SerializedName ( "result" )
    List< Kategori > listDataKategori ;
    @SerializedName ( "message" )
    String message ;
    public void setStatus ( String status ) {
        this . status = status ;
    }
    public String getMessage () {
        return message ;
    }
    public void setMessage ( String message ) {
        this . message = message ;
    }
    public List < Kategori > getListDataKategori () {
        return listDataKategori ;
    }
    public void setListDataKategori ( List < Kategori > listDataKategori ) {
        this . listDataKategori = listDataKategori ;
    }
}
