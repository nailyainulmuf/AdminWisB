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
import com.example.windows10.adminwisatabawean.Model.GetHomestay;
import com.example.windows10.adminwisatabawean.Rest.ApiClient;
import com.example.windows10.adminwisatabawean.Rest.ApiInterface;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LayarEditHomestay extends AppCompatActivity {
    ImageView mPhotoUrl ;
    EditText edtIdHomestay , edtNama , edtFasilitas , edtHarga,edtContact,  edtIdLokasi ;
    TextView tvMessage ;
    Context mContext ;
    Button btUpdate , btDelete , btBack , btPhotoUrl ;
    String pathImage = "" ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layar_edit_homestay);

        mContext = getApplicationContext ();
        mPhotoUrl = (ImageView) findViewById(R.id.imgPhotoId);
        edtIdHomestay = (EditText) findViewById(R.id.edtIdHomestay);
        edtNama = (EditText) findViewById(R.id.edtNamaHomestay);
        edtFasilitas = (EditText) findViewById(R.id.edtFasilitasHome);
        edtHarga = (EditText) findViewById(R.id.edtAddHargaHomestay);
        edtContact = (EditText) findViewById(R.id.edtAddContactHomestay);
        edtIdLokasi = (EditText) findViewById(R.id.edtAddIdLokasi);

        tvMessage = (TextView) findViewById(R.id.tvMessage);

        btUpdate = (Button) findViewById(R.id.btUpdate);
        btDelete = (Button) findViewById(R.id.btDelete);
        btBack = (Button) findViewById(R.id.btBack);
        btPhotoUrl = (Button) findViewById(R.id.btPhotoId);
        Intent mIntent = getIntent ();
        edtIdHomestay . setText ( mIntent . getStringExtra ( "id_homestay" ));
        edtNama . setText ( mIntent . getStringExtra ( "nama" ));
        edtFasilitas . setText ( mIntent . getStringExtra ( "fasilitas" ));
        edtHarga . setText ( mIntent . getStringExtra ( "harga" ));
        edtContact . setText ( mIntent . getStringExtra ( "contact" ));
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
                if ((! pathImage . contains ( "upload/" + edtIdHomestay . getText (). toString ()))
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
                RequestBody reqIdHomestay =
                        MultipartBody . create ( MediaType . parse ( "multipart/form-data" ),
                                ( edtIdHomestay . getText (). toString (). isEmpty ())?
                                        "" : edtIdHomestay . getText (). toString ());
                RequestBody reqNama =
                        MultipartBody . create ( MediaType . parse ( "multipart/form-data" ),
                                ( edtNama . getText (). toString (). isEmpty ())?
                                        "" : edtNama . getText (). toString ());
                RequestBody reqFasilitas =
                        MultipartBody . create ( MediaType . parse ( "multipart/form-data" ),
                                ( edtFasilitas . getText (). toString (). isEmpty ())?
                                        "" : edtFasilitas . getText (). toString ());
                RequestBody reqHarga =
                        MultipartBody . create ( MediaType . parse ( "multipart/form-data" ),
                                ( edtHarga . getText (). toString (). isEmpty ())?
                                        "" : edtHarga . getText (). toString ());
                RequestBody reqContact =
                        MultipartBody . create ( MediaType . parse ( "multipart/form-data" ),
                                ( edtContact . getText (). toString (). isEmpty ())?
                                        "" : edtContact . getText (). toString ());
                RequestBody reqIdLokasi =
                        MultipartBody . create ( MediaType . parse ( "multipart/form-data" ),
                                ( edtIdLokasi . getText (). toString (). isEmpty ())?
                                        "" : edtIdLokasi . getText (). toString ());
                RequestBody reqAction =
                        MultipartBody . create ( MediaType . parse ( "multipart/form-data" ),
                                "update" );
                Call<GetHomestay> callUpdate = mApiInterface . putHomestay ( body ,
                        reqIdHomestay, reqNama ,
                        reqFasilitas , reqHarga, reqContact, reqIdLokasi , reqAction );
                callUpdate . enqueue ( new Callback< GetHomestay >() {
                    @Override
                    public void onResponse ( Call < GetHomestay > call , Response< GetHomestay >
                            response ) {
//Log.d("Update Retrofit ", response.body().getStatus());
                        if ( response . body (). getStatus (). equals ( "failed" )){
                            tvMessage . setText ( "Retrofit Update \n Status = " +
                                    response . body ()
                                            . getStatus ()+ " \n " +
                                    "Message = " + response . body (). getMessage ()+ " \n " );
                        } else {
                            String detail = " \n " + "id_homestay = " + response . body (). getResult (). get ( 0 ). getIdHomestay ()+ " \n " +
                                    "nama = " + response . body (). getResult (). get ( 0 ). getNama ()+ " \n " +
                                    "fasilitas = " + response . body (). getResult (). get ( 0 ). getFasilitas ()+ " \n " +
                                    "harga = " + response . body (). getResult (). get ( 0 ). getHarga ()+ " \n " +
                                    "contact = " + response . body (). getResult (). get ( 0 ). getContact ()+ " \n " +
                                    "id_lokasi = " + response . body (). getResult (). get ( 0 ). getIdLokasi ()+ " \n " +
                                    "photo_url = " + response . body (). getResult (). get ( 0 ). getPhotoUrl () + " \n " ; tvMessage . setText ( "Retrofit Update \n Status = " + response . body (). getStatus ()+ " \n " +
                                    "Message = " + response . body (). getMessage ()+ detail );
                        }
                    }
                    @Override
                    public void onFailure ( Call < GetHomestay > call , Throwable t ) {
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
                RequestBody reqIdHomestay =
                        MultipartBody . create ( MediaType . parse ( "multipart/form-data" ),
                                ( edtIdHomestay . getText (). toString (). isEmpty ())?
                                        "" : edtIdHomestay . getText (). toString ());
                RequestBody reqAction =
                        MultipartBody . create ( MediaType . parse ( "multipart/form-data" ),
                                "delete" );
                Call < GetHomestay > callDelete =
                        mApiInterface . deleteHomestay ( reqIdHomestay , reqAction );
                callDelete . enqueue ( new Callback < GetHomestay >() {
                    @Override
                    public void onResponse ( Call < GetHomestay > call , Response < GetHomestay >
                            response ) {
                        tvMessage . setText ( "Retrofit Delete \n Status = " + response . body ()
                                . getStatus () + " \n " +
                                "Message = " + response . body (). getMessage ()+ " \n " );
                    }
                    @Override
                    public void onFailure ( Call < GetHomestay > call , Throwable t ) {
                        tvMessage . setText ( "Retrofit Delete \n Status = " +
                                t . getMessage ());
                    }
                });
            }
        });
        btBack . setOnClickListener ( new View . OnClickListener () {
            @Override
            public void onClick ( View view ) {
                Intent tempIntent = new Intent ( mContext , LayarListHomestay . class );
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
