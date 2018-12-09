package com.example.windows10.adminwisatabawean.Model;
import com.google.gson.annotations.SerializedName;
public class Lokasi {
    @SerializedName( "id_lokasi" )
    private String idLokasi ;
    @SerializedName ( "nama_lokasi" )
    private String namaLokasi ;
    @SerializedName ( "alamat" )
    private String alamat ;

    private String action ;
    public Lokasi ( String idLokasi , String namaLokasi , String alamat, String action ) {
        this . idLokasi = idLokasi ;
        this . namaLokasi = namaLokasi ;
        this . alamat = alamat ;
        this . action = action ;
    }
    public String getIdLokasi () {
        return idLokasi ;
    }
    public void setIdLokasi ( String idLokasi ) {
        this . idLokasi = idLokasi ;
    }
    public String getNamaLokasi () {
        return namaLokasi ;
    }
    public void setNamaLokasi ( String namaLokasi ) {
        this . namaLokasi = namaLokasi ;
    }
    public String getAlamat () {
        return alamat ;
    }
    public void setAlamat ( String alamat ) {
        this . alamat = alamat;
    }

}
