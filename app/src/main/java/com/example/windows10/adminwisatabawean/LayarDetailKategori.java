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

public class LayarDetailKategori extends AppCompatActivity {
    EditText edtIdKategori , edtNama;
    Button btUpdate , btDelete , btBack ;
    TextView tvMessage ;
    ApiInterface mApiInterface ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layar_detail_kategori);

        edtIdKategori = (EditText) findViewById(R.id.edtIdKategori);
        edtNama = ( EditText ) findViewById ( R . id . edtNamaKategori );
        tvMessage = ( TextView ) findViewById ( R . id . tvMessage2 );

        btUpdate = ( Button ) findViewById ( R . id . btUpdate2 );
        btDelete = ( Button ) findViewById ( R . id . btDelete2 );
        btBack = ( Button ) findViewById ( R . id . btBack );

        Intent mIntent = getIntent();
        edtIdKategori.setText(mIntent.getStringExtra("id_kategori" ));
        edtNama.setText(mIntent . getStringExtra ( "nama_kategori" ));

        mApiInterface = ApiClient. getClient (). create ( ApiInterface . class );
        btUpdate . setOnClickListener ( new View. OnClickListener () {
            @Override
            public void onClick ( View view ) {
                Call<PostPutDelKategori> updateKategoriCall =
                        mApiInterface . putKategori (
                                edtIdKategori . getText (). toString (),
                                edtNama . getText (). toString ());
                updateKategoriCall . enqueue ( new Callback< PostPutDelKategori >() {
                    @Override
                    public void onResponse ( Call < PostPutDelKategori > call ,
                                             Response< PostPutDelKategori > response ) {
                        tvMessage . setText ( " Retrofit Update: " +
                                " \n " + " Status Update : " + response . body (). getStatus ()
                                +
                                " \n " + " Message Update : " +
                                response . body (). getMessage ());
                    }
                    @Override
                    public void onFailure ( Call < PostPutDelKategori > call , Throwable t ) {
                        tvMessage . setText ( "Retrofit Update: \n Status Update :" +
                                t . getMessage ());
                    }
                });
            }
        });
        btDelete . setOnClickListener ( new View . OnClickListener () {
            @Override
            public void onClick ( View view ) {
                if (! edtIdKategori . getText (). toString (). trim (). isEmpty ()){
                    Call < PostPutDelKategori > deleteKategori =
                            mApiInterface . deleteKategori ( edtIdKategori . getText (). toString ());
                    deleteKategori . enqueue ( new Callback < PostPutDelKategori >() {
                        @Override
                        public void onResponse ( Call < PostPutDelKategori > call ,
                                                 Response < PostPutDelKategori > response ) {
                            tvMessage . setText ( " Retrofit Delete: " +
                                    " \n " + " Status Delete : "
                                    + response . body (). getStatus () +
                                    " \n " + " Message Delete : " +
                                    response . body (). getMessage ());
                        }
                        @Override
                        public void onFailure (Call<PostPutDelKategori> call , Throwable
                                t ) {
                            tvMessage . setText ( "Retrofit Delete: \n Status Delete :" +
                                    t . getMessage ());
                        }
                    });
                } else {
                    tvMessage . setText ( "id_kategori harus diisi" );
                }
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
