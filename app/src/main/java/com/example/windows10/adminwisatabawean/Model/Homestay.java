package com.example.windows10.adminwisatabawean.Model;
import com.google.gson.annotations.SerializedName;
public class Homestay {
    @SerializedName( "id_homestay" )
    private String idHomestay ;
    @SerializedName ( "nama" )
    private String nama ;
    @SerializedName ( "fasilitas" )
    private String fasilitas ;
    @SerializedName ( "harga" )
    private String harga ;
    @SerializedName ( "contact" )
    private String contact ;
    @SerializedName ( "id_lokasi" )
    private String idLokasi ;
    @SerializedName ( "alamat" )
    private String alamat ;
    @SerializedName ( "photo_url" )
    private String photoUrl ;
    private String action ;
    public Homestay ( String idHomestay , String nama , String fasilitas, String harga, String contact,
                    String alamat , String idLokasi , String
                            photoUrl , String action ) {
        this . idHomestay = idHomestay ;
        this . nama = nama ;
        this . fasilitas = fasilitas ;
        this . harga = harga ;
        this . contact = contact ;
        this . idLokasi = idLokasi ;
        this . alamat = alamat ;
        this . photoUrl = photoUrl ;
        this . action = action ;
    }
    public String getIdHomestay () {
        return idHomestay ;
    }
    public void setIdHomestay ( String idHomestay ) {
        this . idHomestay = idHomestay ;
    }
    public String getNama () {
        return nama ;
    }
    public void setNama ( String nama ) {
        this . nama = nama ;
    }
    public String getFasilitas () {
        return fasilitas ;
    }
    public void setFasilitas ( String fasilitas ) {
        this . fasilitas = fasilitas ;
    }
    public String getHarga () {
        return harga ;
    }
    public void setHarga ( String harga ) {
        this . harga = harga ;
    }
    public String getContact () {
        return contact ;
    }
    public void setContact ( String contact ) {
        this . contact = contact ;
    }
    public String getIdLokasi () {
        return idLokasi ;
    }
    public void setIdLokasi ( String idLokasi ) {
        this . idLokasi = idLokasi ;
    }
    public String getAlamat () {
        return alamat ;
    }
    public void setAlamat ( String alamat ) {
        this . alamat = alamat ;
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
