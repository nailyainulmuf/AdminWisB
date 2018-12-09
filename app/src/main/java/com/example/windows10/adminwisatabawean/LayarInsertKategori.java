package com.example.windows10.adminwisatabawean;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.windows10.adminwisatabawean.Model.PostPutDelKategori;
import com.example.windows10.adminwisatabawean.Rest.ApiClient;
import com.example.windows10.adminwisatabawean.Rest.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LayarInsertKategori extends AppCompatActivity {
    EditText edtNamaKategori , edtIdKategori;
    Button btAddData , btBack ;
    TextView tvMessage ;
    ApiInterface mApiInterface ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layar_insert_kategori);

        edtIdKategori = ( EditText ) findViewById ( R . id . edtAddIdKategori );
        edtNamaKategori = ( EditText ) findViewById ( R . id . edtAddNamakategori );
        tvMessage = ( TextView ) findViewById ( R . id . tvAddMessage);

        btAddData = ( Button ) findViewById ( R . id . btAddData );
        btBack = ( Button ) findViewById ( R . id . btAddBack );
        mApiInterface = ApiClient. getClient (). create ( ApiInterface . class );
        btAddData . setOnClickListener ( new View. OnClickListener () {
            @Override
            public void onClick ( View view ) {
                Call<PostPutDelKategori> postKategoriCall =
                        mApiInterface . postKategori (
                                edtIdKategori . getText (). toString (),
                                edtNamaKategori . getText (). toString ());
                postKategoriCall . enqueue ( new Callback< PostPutDelKategori >() {
                    @Override
                    public void onResponse ( Call < PostPutDelKategori > call ,
                                             Response< PostPutDelKategori > response ) {
                        tvMessage . setText ( " Retrofit Insert: " +
                                " \n " + " Status Insert : " +
                                response . body (). getStatus () +
                                " \n " + " Message Insert : " +
                                response . body (). getMessage ());
                    }
                    @Override
                    public void onFailure ( Call < PostPutDelKategori > call , Throwable t ) {
                        tvMessage . setText ( "Retrofit Insert: \n Status Insert :" +
                                t . getMessage ());
                    }
                });
            }
        });
        btBack . setOnClickListener ( new View. OnClickListener () {
            @Override
            public void onClick ( View view ) {
                Intent mIntent = new Intent ( getApplicationContext (), LayarListKategori . class );
                startActivity ( mIntent );
            }
        });
    }
}
