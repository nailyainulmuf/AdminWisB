package com.example.windows10.adminwisatabawean.Model;
import com.google.gson.annotations.SerializedName;
public class Wisata {
    @SerializedName( "id_wisata" )
    private String idWisata ;
    @SerializedName ( "nama_wisata" )
    private String namaWisata ;
    @SerializedName ( "deskripsi" )
    private String deskripsi ;
    @SerializedName ( "id_kategori" )
    private String idKategori ;
    @SerializedName ( "nama_kategori" )
    private String namaKategori ;
    @SerializedName ( "id_lokasi" )
    private String idLokasi ;
    @SerializedName ( "alamat" )
    private String alamat ;
    @SerializedName ( "photo_url" )
    private String photoUrl ;
    private String action ;
    public Wisata ( String idWisata , String namaWisata , String deskripsi, String idKategori, String namaKategori,
                    String alamat , String idLokasi , String
            photoUrl , String action ) {
        this . idWisata = idWisata ;
        this . namaWisata = namaWisata ;
        this . deskripsi = deskripsi ;
        this . idKategori = idKategori ;
        this . namaKategori = namaKategori ;
        this . idLokasi = idLokasi ;
        this . alamat = alamat ;
        this . photoUrl = photoUrl ;
        this . action = action ;
    }
    public String getIdWisata () {
        return idWisata ;
    }
    public void setIdWisata ( String idWisata ) {
        this . idWisata = idWisata ;
    }
    public String getNamaWisata () {
        return namaWisata ;
    }
    public void setNamaWisata ( String namaWisata ) {
        this . namaWisata = namaWisata ;
    }
    public String getDeskripsi () {
        return deskripsi ;
    }
    public void setDeskripsi ( String deskripsi ) {
        this . deskripsi = deskripsi ;
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
