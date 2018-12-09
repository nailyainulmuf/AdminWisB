package com.example.windows10.adminwisatabawean;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import java.util.Calendar;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.karumi.dexter.Dexter;

import com.bumptech.glide.Glide;
import com.example.windows10.adminwisatabawean.Model.GetGaleri;
import com.example.windows10.adminwisatabawean.Rest.ApiClient;
import com.example.windows10.adminwisatabawean.Rest.ApiInterface;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LayarEditGaleri extends AppCompatActivity {
    ImageView mPhotoUrl ;
    EditText edtIdGaleri , edtJudul , edtTgl;
    TextView tvMessage ;
    Context mContext ;
    Button btUpdate , btDelete , btBack , btPhotoUrl ;
    String pathImage = "" ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layar_edit_galeri);

        mContext = getApplicationContext ();
        mPhotoUrl = (ImageView) findViewById(R.id.imgPhotoId);
        edtIdGaleri = (EditText) findViewById(R.id.edtIdGaleri);
        edtJudul = (EditText) findViewById(R.id.edtJudul);
        edtTgl = (EditText) findViewById(R.id.edtTanggal);

        tvMessage = (TextView) findViewById(R.id.tvMessage);

        btUpdate = (Button) findViewById(R.id.btUpdate);
        btDelete = (Button) findViewById(R.id.btDelete);
        btBack = (Button) findViewById(R.id.btBack);
        btPhotoUrl = (Button) findViewById(R.id.btPhotoId);
        Intent mIntent = getIntent ();
        edtIdGaleri . setText ( mIntent . getStringExtra ( "id_galeri" ));
        edtJudul . setText ( mIntent . getStringExtra ( "judul" ));
        edtTgl . setText ( mIntent . getStringExtra ( "tgl" ));

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
                if ((! pathImage . contains ( "upload/" + edtIdGaleri . getText (). toString ()))
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
                RequestBody reqIdGaleri =
                        MultipartBody . create ( MediaType . parse ( "multipart/form-data" ),
                                ( edtIdGaleri . getText (). toString (). isEmpty ())?
                                        "" : edtIdGaleri . getText (). toString ());
                RequestBody reqJudul =
                        MultipartBody . create ( MediaType . parse ( "multipart/form-data" ),
                                ( edtJudul . getText (). toString (). isEmpty ())?
                                        "" : edtJudul . getText (). toString ());
                RequestBody reqTgl =
                        MultipartBody . create ( MediaType . parse ( "multipart/form-data" ),
                                ( edtTgl . getText (). toString (). isEmpty ())?
                                        "" : edtTgl . getText (). toString ());
                RequestBody reqAction =
                        MultipartBody . create ( MediaType . parse ( "multipart/form-data" ),
                                "update" );
                Call<GetGaleri> callUpdate = mApiInterface . putGaleri ( body ,
                        reqIdGaleri , reqJudul ,
                        reqTgl , reqAction );
                callUpdate . enqueue ( new Callback< GetGaleri >() {
                    @Override
                    public void onResponse ( Call < GetGaleri > call , Response< GetGaleri >
                            response ) {
//Log.d("Update Retrofit ", response.body().getStatus());
                        if ( response . body (). getStatus (). equals ( "failed" )){
                            tvMessage . setText ( "Retrofit Update \n Status = " +
                                    response . body ()
                                            . getStatus ()+ " \n " +
                                    "Message = " + response . body (). getMessage ()+ " \n " );
                        } else {
                            String detail = " \n " + "id_galeri = " + response . body (). getResult (). get ( 0 ). getIdGaleri ()+ " \n " +
                                    "judul = " + response . body (). getResult (). get ( 0 ). getJudul ()+ " \n " +
                                    "tgl = " + response . body (). getResult (). get ( 0 ). getTgl ()+ " \n " +
                                    "photo_url = " + response . body (). getResult (). get ( 0 ). getPhotoUrl () + " \n " ; tvMessage . setText ( "Retrofit Update \n Status = " + response . body (). getStatus ()+ " \n " +
                                    "Message = " + response . body (). getMessage ()+ detail );
                        }
                    }
                    @Override
                    public void onFailure ( Call < GetGaleri > call , Throwable t ) {
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
                RequestBody reqIdGaleri =
                        MultipartBody . create ( MediaType . parse ( "multipart/form-data" ),
                                ( edtIdGaleri . getText (). toString (). isEmpty ())?
                                        "" : edtIdGaleri . getText (). toString ());
                RequestBody reqAction =
                        MultipartBody . create ( MediaType . parse ( "multipart/form-data" ),
                                "delete" );
                Call < GetGaleri > callDelete =
                        mApiInterface . deleteGaleri ( reqIdGaleri, reqAction );
                callDelete . enqueue ( new Callback < GetGaleri >() {
                    @Override
                    public void onResponse ( Call < GetGaleri > call , Response < GetGaleri >
                            response ) {
                        tvMessage . setText ( "Retrofit Delete \n Status = " + response . body ()
                                . getStatus () + " \n " +
                                "Message = " + response . body (). getMessage ()+ " \n " );
                    }
                    @Override
                    public void onFailure ( Call < GetGaleri > call , Throwable t ) {
                        tvMessage . setText ( "Retrofit Delete \n Status = " +
                                t . getMessage ());
                    }
                });
            }
        });
        btBack . setOnClickListener ( new View . OnClickListener () {
            @Override
            public void onClick ( View view ) {
                Intent tempIntent = new Intent ( mContext , LayarListGaleri. class );
                startActivity ( tempIntent );
            }
        });
        btPhotoUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mintaPermissions();
            }
        });
    }

    private void mintaPermissions() {
        Dexter.withActivity(this)
                .withPermissions(
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        // Cek apakah semua permission yang diperlukan sudah diijinkan
                        if (report.areAllPermissionsGranted()) {
                            Toast.makeText(getApplicationContext(),
                                    "Semua permissions diijinkan!", Toast.LENGTH_SHORT).show();
                            tampilkanFotoDialog();
                        }

                        // Cek apakah ada permission yang tidak diijinkan
                        if (report.isAnyPermissionPermanentlyDenied()) {
                            // Info user untuk mengubah setting permission
                            tampilkanSettingsDialog();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {

                    }

                }).
                withErrorListener(new PermissionRequestErrorListener() {
                    @Override
                    public void onError(DexterError error) {
                        Toast.makeText(getApplicationContext(),
                                "Error occurred! ", Toast.LENGTH_SHORT).show();
                    }
                })
                .onSameThread()
                .check();
    }
    private void tampilkanFotoDialog(){
        AlertDialog.Builder fotoDialog = new AlertDialog.Builder(this);
        fotoDialog.setTitle("Select Action");

        // Isi opsi dialog
        String[] fotoDialogItems = {
                "Pilih foto dari gallery",
                "Ambil dari kamera" };

        fotoDialog.setItems(fotoDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int pilihan) {
                        switch (pilihan) {
                            case 0:
                                pilihDariGallery();
                                break;
                            case 1:
                                ambilDariCamera();
                                break;
                        }
                    }
                });
        fotoDialog.show();
    }

    public void pilihDariGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(galleryIntent, 13);
    }

    private void ambilDariCamera() {
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);

        startActivityForResult(cameraIntent, 16);
    }

    private void tampilkanSettingsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(LayarEditGaleri.this);
        builder.setTitle("Butuh Permission");
        builder.setMessage("Aplikasi ini membutuhkan permission khusus, mohon ijin.");
        builder.setPositiveButton("BUKA SETTINGS", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                bukaSettings();
            }
        });
        builder.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();

    }

    private void bukaSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        startActivityForResult(intent, 101);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_CANCELED) {
            return;
        }

        // Jika request berasal dari Gallery
        if (requestCode == 13) {
            if (data != null) {
                Uri contentURI = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                    pathImage = simpanImage(bitmap);
                    Toast.makeText(mContext, "Foto berhasil di-load!", Toast.LENGTH_SHORT).show();

                    Glide.with(mContext).load(new File(pathImage)).into(mPhotoUrl);

                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(mContext, "Foto gagal di-load!", Toast.LENGTH_SHORT).show();
                }
            }

            // Jika request dari Camera
        } else if (requestCode == 16) {
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            pathImage = simpanImage(thumbnail);
            Toast.makeText(mContext, "Foto berhasil di-load dari Camera!", Toast.LENGTH_SHORT)
                    .show();

            Glide.with(mContext).load(new File(pathImage)).into(mPhotoUrl);
        }

    }

    public String simpanImage(Bitmap myBitmap) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();

        // Kualitas gambar yang disimpan
        myBitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes);

        // Buat object direktori file
        File lokasiImage = new File(
                Environment.getExternalStorageDirectory() + "/praktikum");

        // Buat direktori untuk penyimpanan
        if (!lokasiImage.exists()) {
            lokasiImage.mkdirs();
        }

        try {
            // Untuk penamaan file
            File f = new File(lokasiImage, Calendar.getInstance()
                    .getTimeInMillis() + ".jpg");
            f.createNewFile();

            // Operasi file
            FileOutputStream fo = new FileOutputStream(f);
            fo.write(bytes.toByteArray());
            MediaScannerConnection.scanFile(this,
                    new String[]{f.getPath()},
                    new String[]{"image/jpeg"}, null);
            fo.close();

            Log.d("PRAKTIKUM", "File tersimpan di --->" + f.getAbsolutePath());

            // Return file
            return f.getAbsolutePath();

        } catch (IOException e1) {
            Log.d("PRAKTIKUM", "erroraaaaa");
            e1.printStackTrace();
        }
        return "";
    }
}
