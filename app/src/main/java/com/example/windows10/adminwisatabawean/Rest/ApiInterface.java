package com.example.windows10.adminwisatabawean.Rest;

import com.example.windows10.adminwisatabawean.Model.GetGaleri;
import com.example.windows10.adminwisatabawean.Model.GetHomestay;
import com.example.windows10.adminwisatabawean.Model.GetKategori;
import com.example.windows10.adminwisatabawean.Model.GetLokasi;
import com.example.windows10.adminwisatabawean.Model.GetWisata;
import com.example.windows10.adminwisatabawean.Model.PostPutDelKategori;
import com.example.windows10.adminwisatabawean.Model.PostPutDelLokasi;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;

public interface ApiInterface {
    @GET( "wisata/all" )
    Call<GetWisata> getWisata ();
    @Multipart
    @POST( "wisata/all" )
    Call < GetWisata > postWisata (
            @Part MultipartBody. Part file ,
            @Part ( "nama_wisata" ) RequestBody nama_wisata ,
            @Part ( "deskripsi" ) RequestBody deskripsi ,
            @Part ( "id_kategori" ) RequestBody id_kategori ,
            //@Part ( "nama_kategori" ) RequestBody nama_kategori ,
            @Part ( "id_lokasi" ) RequestBody id_lokasi ,
           // @Part ( "alamat" ) RequestBody alamat ,
            @Part ( "action" ) RequestBody action
    );
    @Multipart
    @POST ( "wisata/all" )
    Call < GetWisata > putWisata (
            @Part MultipartBody . Part file ,
            @Part ( "id_wisata" ) RequestBody idWisata ,
            @Part ( "nama_wisata" ) RequestBody namaWisata ,
            @Part ( "deskripsi" ) RequestBody deskripsi ,
            @Part ( "id_kategori" ) RequestBody idKategori ,
            @Part ( "id_lokasi" ) RequestBody idLokasi ,
            @Part ( "action" ) RequestBody action
    );
    @Multipart
    @POST ( "wisata/all" )
    Call < GetWisata > deleteWisata (
            @Part ( "id_wisata" ) RequestBody idWisata ,
            @Part ( "action" ) RequestBody action );

    @GET( "homestay/all" )
    Call<GetHomestay> getHomestay ();
    @Multipart
    @POST( "homestay/all" )
    Call < GetHomestay > postHomestay (
            @Part MultipartBody. Part file ,
            @Part ( "nama" ) RequestBody nama ,
            @Part ( "fasilitas" ) RequestBody fasilitas ,
            @Part ( "harga" ) RequestBody harga ,
            @Part ( "contact" ) RequestBody contact ,
            @Part ( "id_lokasi" ) RequestBody idLokasi ,
            //@Part ( "alamat" ) RequestBody alamat ,
            @Part ( "action" ) RequestBody action
    );
    @Multipart
    @POST ( "homestay/all" )
    Call < GetHomestay > putHomestay (
            @Part MultipartBody . Part file ,
            @Part ( "id_homestay" ) RequestBody idHomestay ,
            @Part ( "nama" ) RequestBody nama ,
            @Part ( "fasilitas" ) RequestBody fasilitas ,
            @Part ( "harga" ) RequestBody harga ,
            @Part ( "contact" ) RequestBody contact ,
            @Part ( "id_lokasi" ) RequestBody idLokasi ,
            // @Part ( "alamat" ) RequestBody alamat ,
            @Part ( "action" ) RequestBody action
    );
    @Multipart
    @POST ( "homestay/all" )
    Call < GetHomestay > deleteHomestay (
            @Part ( "id_homestay" ) RequestBody idHomestay ,
            @Part ( "action" ) RequestBody action );

    @GET( "galeri/all" )
    Call<GetGaleri> getGaleri ();
    @Multipart
    @POST( "galeri/all" )
    Call < GetGaleri > postGaleri (
            @Part MultipartBody. Part file ,
            @Part ( "judul" ) RequestBody judul ,
            @Part ( "tgl" ) RequestBody tgl ,
            @Part ( "action" ) RequestBody action
    );
    @Multipart
    @POST ( "galeri/all" )
    Call < GetGaleri > putGaleri (
            @Part MultipartBody . Part file ,
            @Part ( "id_galeri" ) RequestBody idGaleri ,
            @Part ( "judul" ) RequestBody judul ,
            @Part ( "tgl" ) RequestBody tgl ,
            @Part ( "action" ) RequestBody action
    );
    @Multipart
    @POST ( "galeri/all" )
    Call < GetGaleri > deleteGaleri (
            @Part ( "id_galeri" ) RequestBody idGaleri ,
            @Part ( "action" ) RequestBody action );

    @GET ( "lokasi/index" )
    Call <GetLokasi> getLokasi ();
    @FormUrlEncoded
    @POST ( "lokasi/index" )
    Call <PostPutDelLokasi> postLokasi
            (@Field( "id_lokasi" ) String idLokasi , @Field ( "nama_lokasi" ) String
                    idNamaLokasi ,
             @Field ( "alamat" ) String alamat );
    @FormUrlEncoded
    @PUT( "lokasi/index" )
    Call <PostPutDelLokasi> putLokasi (
           @Field( "id_lokasi" ) String idLokasi , @Field ( "nama_lokasi" ) String
            idNamaLokasi ,
           @Field ( "alamat" ) String alamat );
    @FormUrlEncoded
    @HTTP( method = "DELETE" , path = "lokasi/index" , hasBody = true )
    Call <PostPutDelLokasi> deleteLokasi ( @Field ( "id_lokasi" ) String idLokasi );

    @GET ( "kategori/index" )
    Call <GetKategori> getKategori ();
    @FormUrlEncoded
    @POST ( "kategori/index" )
    Call <PostPutDelKategori> postKategori
            (@Field( "id_kategori" ) String idKategori , @Field ( "nama_kategori" ) String
                    namaKategori) ;
    @FormUrlEncoded
    @PUT( "kategori/index" )
    Call <PostPutDelKategori> putKategori (
            @Field( "id_kategori" ) String idKategori , @Field ( "nama_kategori" ) String
            namaKategori) ;
    @FormUrlEncoded
    @HTTP( method = "DELETE" , path = "kategori/index" , hasBody = true )
    Call <PostPutDelKategori> deleteKategori ( @Field ( "id_kategori" ) String idkategori );
}
