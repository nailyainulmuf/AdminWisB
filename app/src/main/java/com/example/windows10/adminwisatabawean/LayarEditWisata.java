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

import com.bumptech.glide.Glide;
import com.example.windows10.adminwisatabawean.Model.GetWisata;
import com.example.windows10.adminwisatabawean.Rest.ApiClient;
import com.example.windows10.adminwisatabawean.Rest.ApiInterface;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LayarEditWisata extends AppCompatActivity {
    ImageView mPhotoUrl ;
    EditText edtIdWisata , edtNama , edtDeskripsi , edtIdKategori, edtIdLokasi ;
    TextView tvMessage ;
    Context mContext ;
    Button btUpdate , btDelete , btBack , btPhotoUrl ;
    String pathImage = "" ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layar_edit_wisata);

        mContext = getApplicationContext ();
        mPhotoUrl = (ImageView) findViewById(R.id.imgPhotoId);
        edtIdWisata = (EditText) findViewById(R.id.edtIdWisata);
        edtNama = (EditText) findViewById(R.id.edtNamaWisata);
        edtDeskripsi = (EditText) findViewById(R.id.edtAddDeskripsiWisata);
        edtIdKategori = (EditText) findViewById(R.id.edtKategoriWisata);
        edtIdLokasi = (EditText) findViewById(R.id.edtAddLokasiWisata);

        tvMessage = (TextView) findViewById(R.id.tvMessage);

        btUpdate = (Button) findViewById(R.id.btUpdate);
        btDelete = (Button) findViewById(R.id.btDelete);
        btBack = (Button) findViewById(R.id.btBack);
        btPhotoUrl = (Button) findViewById(R.id.btPhotoId);
        Intent mIntent = getIntent ();
        edtIdWisata . setText ( mIntent . getStringExtra ( "id_wisata" ));
        edtNama . setText ( mIntent . getStringExtra ( "nama_wisata" ));
        edtDeskripsi . setText ( mIntent . getStringExtra ( "deskripsi" ));
        edtIdKategori . setText ( mIntent . getStringExtra ( "id_kategori" ));
        edtIdLokasi . setText ( mIntent . getStringExtra ( "id_lokasi" ));
// if (mIntent.getStringExtra("photo_url").length()>0) Picasso.with(mContext).load
// (ApiClient.BASE_URL + mIntent.getStringExtra("photo_url")).into(mPhotoUrl);
// else Picasso.with(mContext).load(R.drawable.photoid).into(mPhotoUrl);
        if ( mIntent . getStringExtra ( "photo_url" ) != null )
            Glide. with ( mContext ). load ( ApiClient.BASE_URL + mIntent . getStringExtra ( "photo_url" )). into ( mPhotoUrl );
        else
            Glide . with ( mContext ). load ( R . drawable . backwis ). into ( mPhotoUrl );
        pathImage = mIntent . getStringExtra ( "photo_url" );
        setListener ();
    }
    private void setListener () {
        final ApiInterface mApiInterface =
                ApiClient . getClient (). create ( ApiInterface . class );
        btUpdate . setOnClickListener ( new View. OnClickListener () {
            @Override
            public void onClick ( View view ) {
                MultipartBody. Part body = null ;
//dicek apakah image sama dengan yang ada di server atau berubah
//jika sama dikirim lagi jika berbeda akan dikirim ke server diubah ( "upload/"))
                if ((! pathImage . contains ( "upload/" + edtIdWisata . getText (). toString ()))
                        &&
                        ( pathImage . length ()> 0 )){
//File creating from selected URL
                    File file = new File ( pathImage );
// create RequestBody instance from file
                    RequestBody requestFile = RequestBody . create (
                            MediaType. parse ( "multipart/form-data" ), file );
// MultipartBody.Part is used to send also the actual file name
                    body = MultipartBody . Part . createFormData ( "photo_url" , file . getName (),
                            requestFile );
                }
                RequestBody reqIdWisata =
                        MultipartBody . create ( MediaType . parse ( "multipart/form-data" ),
                                ( edtIdWisata . getText (). toString (). isEmpty ())?
                                        "" : edtIdWisata . getText (). toString ());
                RequestBody reqNamaWisata =
                        MultipartBody . create ( MediaType . parse ( "multipart/form-data" ),
                                ( edtNama . getText (). toString (). isEmpty ())?
                                        "" : edtNama . getText (). toString ());
                RequestBody reqDeskripsi =
                        MultipartBody . create ( MediaType . parse ( "multipart/form-data" ),
                                ( edtDeskripsi . getText (). toString (). isEmpty ())?
                                        "" : edtDeskripsi . getText (). toString ());
                RequestBody reqIdKategori =
                        MultipartBody . create ( MediaType . parse ( "multipart/form-data" ),
                                ( edtIdKategori . getText (). toString (). isEmpty ())?
                                        "" : edtIdKategori . getText (). toString ());
                RequestBody reqIdLokasi =
                        MultipartBody . create ( MediaType . parse ( "multipart/form-data" ),
                                ( edtIdLokasi . getText (). toString (). isEmpty ())?
                                        "" : edtIdLokasi . getText (). toString ());
                RequestBody reqAction =
                        MultipartBody . create ( MediaType . parse ( "multipart/form-data" ),
                                "update" );
                Call<GetWisata> callUpdate = mApiInterface . putWisata ( body ,
                        reqIdWisata , reqNamaWisata ,
                        reqDeskripsi , reqIdKategori, reqIdLokasi , reqAction );
                callUpdate . enqueue ( new Callback< GetWisata >() {
                    @Override
                    public void onResponse ( Call < GetWisata > call , Response< GetWisata >
                            response ) {
//Log.d("Update Retrofit ", response.body().getStatus());
                        if ( response . body (). getStatus (). equals ( "failed" )){
                            tvMessage . setText ( "Retrofit Update \n Status = " +
                                    response . body ()
                                            . getStatus ()+ " \n " +
                                    "Message = " + response . body (). getMessage ()+ " \n " );
                        } else {
                            String detail = " \n " + "id_wisata = " + response . body (). getResult (). get ( 0 ). getIdWisata ()+ " \n " +
                                    "nama_wisata = " + response . body (). getResult (). get ( 0 ). getNamaWisata ()+ " \n " +
                                    "deskripsi = " + response . body (). getResult (). get ( 0 ). getDeskripsi ()+ " \n " +
                                    "id_kategori = " + response . body (). getResult (). get ( 0 ). getIdKategori ()+ " \n " +
                                    "id_lokasi = " + response . body (). getResult (). get ( 0 ). getIdLokasi ()+ " \n " +
                                    "photo_url = " + response . body (). getResult (). get ( 0 ). getPhotoUrl () + " \n " ; tvMessage . setText ( "Retrofit Update \n Status = " + response . body (). getStatus ()+ " \n " +
                                    "Message = " + response . body (). getMessage ()+ detail );
                        }
                    }
                    @Override
                    public void onFailure ( Call < GetWisata > call , Throwable t ) {
//Log.d("Update Retrofit ", t.getMessage());
                        tvMessage . setText ( "Retrofit Update \n Status = " +
                                t . getMessage ());
                    }
                });
            }
        });
        btDelete . setOnClickListener ( new View . OnClickListener () {
            @Override
            public void onClick ( View view ) {
                RequestBody reqIdWisata =
                        MultipartBody . create ( MediaType . parse ( "multipart/form-data" ),
                                ( edtIdWisata . getText (). toString (). isEmpty ())?
                                        "" : edtIdWisata . getText (). toString ());
                RequestBody reqAction =
                        MultipartBody . create ( MediaType . parse ( "multipart/form-data" ),
                                "delete" );
                Call < GetWisata > callDelete =
                        mApiInterface . deleteWisata ( reqIdWisata , reqAction );
                callDelete . enqueue ( new Callback < GetWisata >() {
                    @Override
                    public void onResponse ( Call < GetWisata > call , Response < GetWisata >
                            response ) {
                        tvMessage . setText ( "Retrofit Delete \n Status = " + response . body ()
                                . getStatus () + " \n " +
                                "Message = " + response . body (). getMessage ()+ " \n " );
                    }
                    @Override
                    public void onFailure ( Call < GetWisata > call , Throwable t ) {
                        tvMessage . setText ( "Retrofit Delete \n Status = " +
                                t . getMessage ());
                    }
                });
            }
        });
        btBack . setOnClickListener ( new View . OnClickListener () {
            @Override
            public void onClick ( View view ) {
                Intent tempIntent = new Intent ( mContext , MainActivity . class );
                startActivity ( tempIntent );
            }
        });
        btPhotoUrl . setOnClickListener ( new View . OnClickListener () {
            @Override
            public void onClick ( View view ) {
                final Intent galleryIntent = new Intent ();
                galleryIntent . setType ( "image/*" );
                galleryIntent . setAction ( Intent . ACTION_PICK );
                Intent intentChoose = Intent . createChooser ( galleryIntent , "Pilih foto untuk " + "di-upload" );
                startActivityForResult ( intentChoose , 10 );
            }
        });
    }
    @Override
    protected void onActivityResult ( int requestCode , int resultCode , Intent data ) {
        super . onActivityResult ( requestCode , resultCode , data );
        if ( resultCode == RESULT_OK && requestCode == 10 ) {
            if ( data == null ) {
                Toast. makeText ( mContext , "Foto gagal di-load" , Toast . LENGTH_LONG ). show ();
                return ;
            }
            Uri selectedImage = data . getData ();
            String [] filePathColumn = { MediaStore. Images . Media . DATA };
            Cursor cursor = getContentResolver (). query ( selectedImage , filePathColumn ,
                    null , null , null );
            if ( cursor != null ) {
                cursor . moveToFirst ();
                int columnIndex = cursor . getColumnIndex ( filePathColumn [ 0 ]);
                pathImage = cursor . getString ( columnIndex );
//Picasso.with(mContext).load(new File(imagePath)).fit().into(mImageView);
                Glide . with ( mContext ). load ( new File ( pathImage )). into ( mPhotoUrl );
                cursor . close ();
            } else {
                Toast . makeText ( mContext , "Foto gagal di-load" , Toast . LENGTH_LONG ). show ();
            }
        }
    }
}
