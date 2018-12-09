package com.example.windows10.adminwisatabawean.Model;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;
public class GetHomestay {
    @SerializedName( "status" )
    private String status ;
    @SerializedName ( "result" )
    private List< Homestay > result = new ArrayList< Homestay >();
    @SerializedName ( "message" )
    private String message ;
    public GetHomestay () {}
    public String getStatus () {
        return status ;
    }
    public void setStatus ( String status ) {
        this . status = status ;
    }
    public List< Homestay > getResult () {
        return result ;
    }
    public void setResult ( List < Homestay > result ) {
        this . result = result ;
    }
    public String getMessage () {
        return message ;
    }
    public void setMessage ( String message ) {
        this . message = message ;
    }
}
