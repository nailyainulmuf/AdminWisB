package com.example.windows10.adminwisatabawean;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.windows10.adminwisatabawean.Model.PostPutDelLokasi;
import com.example.windows10.adminwisatabawean.Rest.ApiClient;
import com.example.windows10.adminwisatabawean.Rest.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LayarInsertLokasi extends AppCompatActivity {
    EditText edtNamaLokasi , edtAlamat, edtIdLokasi;
    Button btAddData , btBack ;
    TextView tvMessage ;
    ApiInterface mApiInterface ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layar_insert_lokasi);
        edtIdLokasi = ( EditText ) findViewById ( R . id . edtAddIdLokasi );
        edtNamaLokasi = ( EditText ) findViewById ( R . id . edtAddNamaLokasi );
        edtAlamat = ( EditText ) findViewById ( R . id . edtAddAlamatLokasi );
        tvMessage = ( TextView ) findViewById ( R . id . tvAddMessage);

        btAddData = ( Button ) findViewById ( R . id . btAddData );
        btBack = ( Button ) findViewById ( R . id . btAddBack );
        mApiInterface = ApiClient. getClient (). create ( ApiInterface . class );
        btAddData . setOnClickListener ( new View. OnClickListener () {
            @Override
            public void onClick ( View view ) {
                Call<PostPutDelLokasi> postLokasiCall =
                        mApiInterface . postLokasi (
                                edtIdLokasi . getText (). toString (),
                                edtNamaLokasi . getText (). toString (),
                                edtAlamat . getText (). toString ());
                postLokasiCall . enqueue ( new Callback< PostPutDelLokasi >() {
                    @Override
                    public void onResponse ( Call < PostPutDelLokasi > call ,
                                             Response< PostPutDelLokasi > response ) {
                        tvMessage . setText ( " Retrofit Insert: " +
                                " \n " + " Status Insert : " +
                                response . body (). getStatus () +
                                " \n " + " Message Insert : " +
                                response . body (). getMessage ());
                    }
                    @Override
                    public void onFailure ( Call < PostPutDelLokasi > call , Throwable t ) {
                        tvMessage . setText ( "Retrofit Insert: \n Status Insert :" +
                                t . getMessage ());
                    }
                });
            }
        });
        btBack . setOnClickListener ( new View. OnClickListener () {
            @Override
            public void onClick ( View view ) {
                Intent mIntent = new Intent ( getApplicationContext (), LayarListLokasi . class );
                startActivity ( mIntent );
            }
        });
    }
}
