package com.example.windows10.adminwisatabawean.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class GetGaleri {
    @SerializedName( "status" )
    private String status ;
    @SerializedName ( "result" )
    private List< Galeri > result = new ArrayList< Galeri >();
    @SerializedName ( "message" )
    private String message ;
    public GetGaleri () {}
    public String getStatus () {
        return status ;
    }
    public void setStatus ( String status ) {
        this . status = status ;
    }
    public List< Galeri > getResult () {
        return result ;
    }
    public void setResult ( List < Galeri > result ) {
        this . result = result ;
    }
    public String getMessage () {
        return message ;
    }
    public void setMessage ( String message ) {
        this . message = message ;
    }
}
