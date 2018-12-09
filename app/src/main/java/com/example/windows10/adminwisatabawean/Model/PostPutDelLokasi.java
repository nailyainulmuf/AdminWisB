package com.example.windows10.adminwisatabawean.Model;
import com.google.gson.annotations. SerializedName ;
public class PostPutDelLokasi {
    @SerializedName ( "status" )
    String status ;
    @SerializedName ( "result" )
    Lokasi mLokasi ;
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
    public Lokasi getLokasi () {
        return mLokasi ;
    }
    public void setLokasi ( Lokasi lokasi ) {
        mLokasi = lokasi ;
    }
}
