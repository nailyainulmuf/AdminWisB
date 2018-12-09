package com.example.windows10.adminwisatabawean;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.windows10.adminwisatabawean.Model.GetHomestay;
import com.example.windows10.adminwisatabawean.Model.GetWisata;
import com.example.windows10.adminwisatabawean.Rest.ApiClient;
import com.example.windows10.adminwisatabawean.Rest.ApiInterface;
import com.squareup.picasso.Picasso;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LayarInsertHomestay extends AppCompatActivity {
    Context mContext ;
    ImageView mImageView ;
    Button btAddPhotoId , btAddBack , btAddData;
    EditText edtAddNamaHomestay ,edtAddFasilitas, edtAddHarga, edtAddContact ;
    EditText edtAddLokasiHomestay ;
    TextView tvAddMessage ;
    String imagePath = "" ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layar_insert_homestay);

        mContext = getApplicationContext ();
        mImageView = ( ImageView ) findViewById ( R . id . imgAddPhotoId );
        btAddPhotoId = ( Button ) findViewById ( R . id . btAddPhotoId );
        edtAddNamaHomestay = ( EditText ) findViewById ( R . id . edtAddNamaHomestay );
        edtAddFasilitas = ( EditText ) findViewById ( R . id . edtAddFasilitasHomestay );
        edtAddHarga = ( EditText ) findViewById ( R . id . edtAddHargaHomestay );
        edtAddContact = ( EditText ) findViewById ( R . id . edtAddContactHomestay );
        edtAddLokasiHomestay = ( EditText ) findViewById ( R . id . edtAddLokasiHomestay );
        btAddData = ( Button ) findViewById ( R . id . btAddData );
        btAddBack = ( Button ) findViewById ( R . id . btAddBack );
        tvAddMessage = ( TextView ) findViewById ( R . id . tvAddMessage );
        btAddData . setOnClickListener ( new View. OnClickListener () {
            @Override
            public void onClick ( View view ) {
                ApiInterface mApiInterface =
                        ApiClient. getClient (). create ( ApiInterface . class );
                MultipartBody. Part body = null ;
                if (! imagePath . isEmpty ()){
// Buat file dari image yang dipilih
                    File file = new File ( imagePath );
                    // Buat RequestBody instance dari file
                    RequestBody requestFile =
                            RequestBody . create ( MediaType. parse ( "image/jpg" ), file );
// MultipartBody.Part digunakan untuk mendapatkan nama file
                    body = MultipartBody . Part . createFormData ( "photo_url" , file . getName (),
                            requestFile );
                }
                RequestBody reqNama =
                        MultipartBody . create ( MediaType . parse ( "multipart/form-data" ),
                                ( edtAddNamaHomestay . getText (). toString (). isEmpty ())? "" : edtAddNamaHomestay . getText (). toString ());
                RequestBody reqFasilitas =
                        MultipartBody . create ( MediaType . parse ( "multipart/form-data" ),
                                ( edtAddNamaHomestay . getText (). toString (). isEmpty ())? "" : edtAddFasilitas . getText (). toString ());
                RequestBody reqHarga =
                        MultipartBody . create ( MediaType . parse ( "multipart/form-data" ),
                                ( edtAddNamaHomestay . getText (). toString (). isEmpty ())? "" : edtAddHarga . getText (). toString ());
                RequestBody reqContact =
                        MultipartBody . create ( MediaType . parse ( "multipart/form-data" ),
                                ( edtAddNamaHomestay . getText (). toString (). isEmpty ())? "" : edtAddContact . getText (). toString ());
                RequestBody reqIdLokasi =
                        MultipartBody . create ( MediaType . parse ( "multipart/form-data" ),
                                ( edtAddNamaHomestay . getText (). toString (). isEmpty ())? "" : edtAddLokasiHomestay . getText (). toString ());
                RequestBody reqAction =
                        MultipartBody . create ( MediaType . parse ( "multipart/form-data" ),
                                "insert" );
                Call<GetHomestay> mHomestayCall = mApiInterface.postHomestay ( body , reqNama , reqFasilitas , reqHarga , reqContact, reqIdLokasi, reqAction );
                mHomestayCall.enqueue ( new Callback<GetHomestay>() {
                    @Override
                    public void onResponse(Call<GetHomestay> call, Response<GetHomestay> response) {
// Log.d("Insert Retrofit",response.body().getMessage());
                        if (response.body().getStatus().equals("failed" )){
                            tvAddMessage.setText ("Retrofit Insert \n Status = " + response.body().getStatus()+ " \n " + "Message = " + response . body (). getMessage ()+ " \n " );
                        } else {
                            String detail = " \n " + "id_homestay = " + response . body (). getResult (). get ( 0 ). getIdHomestay ()+ " \n " +
                                    "nama = " + response . body (). getResult (). get ( 0 ). getNama ()+ " \n " +
                                    "fasilitas = " + response . body (). getResult (). get ( 0 ). getFasilitas ()+ " \n " +
                                    "harga = " + response . body (). getResult (). get ( 0 ). getHarga()+ " \n " +
                                    "contact = " + response . body (). getResult (). get ( 0 ). getContact()+ " \n " +
                                    "id_lokasi = " + response . body (). getResult (). get ( 0 ). getIdLokasi ()+ " \n " +
                                    "photo_url = " + response . body (). getResult (). get ( 0 ). getPhotoUrl () + " \n " ;
                            tvAddMessage . setText ( "Retrofit Insert \n Status = " + response . body (). getStatus ()+ " \n " +
                                    "Message = " + response . body (). getMessage ()+ detail );
                        }
                    }
                    @Override
                    public void onFailure ( Call < GetHomestay > call , Throwable t ) {
// Log.d("Insert Retrofit", t.getMessage());
                        tvAddMessage . setText ( "Retrofit Insert Failure \n Status = " +
                                t . getMessage
                                        ());
                    }
                });
            }
        });
        btAddBack . setOnClickListener ( new View . OnClickListener () {
            @Override
            public void onClick ( View view ) {
                Intent intent = new Intent ( mContext , LayarListHomestay . class );
                startActivity ( intent );
            }
        });
        btAddPhotoId . setOnClickListener ( new View . OnClickListener () {
            @Override
            public void onClick ( View view ) {
                final Intent galleryIntent = new Intent ();
                galleryIntent . setType ( "image/*" );
                galleryIntent . setAction ( Intent . ACTION_PICK );
                Intent intentChoose = Intent . createChooser (
                        galleryIntent ,
                        "Pilih foto untuk di-upload" );
                startActivityForResult ( intentChoose , 10 );
            }
        });
    }
    @Override
    protected void onActivityResult ( int requestCode , int resultCode , Intent data ) {
        super . onActivityResult ( requestCode , resultCode , data );
        if ( resultCode == RESULT_OK && requestCode == 10 ){
            if ( data == null ){
                Toast. makeText ( mContext , "Foto gagal di-load" ,
                        Toast . LENGTH_LONG ). show ();
                return ;
            }
            Uri selectedImage = data . getData ();
            String [] filePathColumn = { MediaStore. Images . Media . DATA };
            Cursor cursor = getContentResolver (). query ( selectedImage , filePathColumn ,
                    null , null , null );
            if ( cursor != null ) {
                cursor . moveToFirst ();
                int columnIndex = cursor . getColumnIndex ( filePathColumn [ 0 ]);
                imagePath = cursor . getString ( columnIndex );
                Picasso. with ( mContext ). load ( new File ( imagePath )). fit (). into ( mImageView );
// Glide.with(mContext).load(new File(imagePath)).into(mImageView);
                cursor . close ();
            } else {
                Toast . makeText ( mContext , "Foto gagal di-load" ,
                        Toast . LENGTH_LONG ). show ();
            }
        }
    }
}
