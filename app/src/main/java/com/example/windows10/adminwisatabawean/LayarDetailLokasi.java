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

public class LayarDetailLokasi extends AppCompatActivity {
    EditText edtIdLokasi , edtNama , edtAlamat;
    Button btUpdate , btDelete , btBack ;
    TextView tvMessage ;
    ApiInterface mApiInterface ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layar_detail_lokasi);

        edtIdLokasi = (EditText) findViewById(R.id.edtIdLokasi);
        edtNama = ( EditText ) findViewById ( R . id . edtNamaLokasi );
        edtAlamat = ( EditText ) findViewById ( R . id . edtAlamat);
        tvMessage = ( TextView ) findViewById ( R . id . tvMessage2 );

        btUpdate = ( Button ) findViewById ( R . id . btUpdate2 );
        btDelete = ( Button ) findViewById ( R . id . btDelete2 );
        btBack = ( Button ) findViewById ( R . id . btBack );

        Intent mIntent = getIntent();
        edtIdLokasi.setText(mIntent.getStringExtra("id_lokasi" ));
        edtNama.setText(mIntent . getStringExtra ( "nama_lokasi" ));
        edtAlamat.setText( mIntent . getStringExtra ( "alamat" ));

        mApiInterface = ApiClient. getClient (). create ( ApiInterface . class );
        btUpdate . setOnClickListener ( new View. OnClickListener () {
            @Override
            public void onClick ( View view ) {
                Call<PostPutDelLokasi> updateLokasiCall =
                        mApiInterface . putLokasi (
                                edtIdLokasi . getText (). toString (),
                                edtNama . getText (). toString (),
                                edtAlamat . getText (). toString ());
                updateLokasiCall . enqueue ( new Callback< PostPutDelLokasi >() {
                    @Override
                    public void onResponse ( Call < PostPutDelLokasi > call ,
                                             Response< PostPutDelLokasi > response ) {
                        tvMessage . setText ( " Retrofit Update: " +
                                " \n " + " Status Update : " + response . body (). getStatus ()
                                +
                                " \n " + " Message Update : " +
                                response . body (). getMessage ());
                    }
                    @Override
                    public void onFailure ( Call < PostPutDelLokasi > call , Throwable t ) {
                        tvMessage . setText ( "Retrofit Update: \n Status Update :" +
                                t . getMessage ());
                    }
                });
            }
        });
        btDelete . setOnClickListener ( new View . OnClickListener () {
            @Override
            public void onClick ( View view ) {
                if (! edtIdLokasi . getText (). toString (). trim (). isEmpty ()){
                    Call < PostPutDelLokasi > deleteLokasi =
                            mApiInterface . deleteLokasi ( edtIdLokasi . getText (). toString ());
                    deleteLokasi . enqueue ( new Callback < PostPutDelLokasi >() {
                        @Override
                        public void onResponse ( Call < PostPutDelLokasi > call ,
                                                 Response < PostPutDelLokasi > response ) {
                            tvMessage . setText ( " Retrofit Delete: " +
                                    " \n " + " Status Delete : "
                                    + response . body (). getStatus () +
                                    " \n " + " Message Delete : " +
                                    response . body (). getMessage ());
                        }
                        @Override
                        public void onFailure (Call<PostPutDelLokasi> call , Throwable
                                t ) {
                            tvMessage . setText ( "Retrofit Delete: \n Status Delete :" +
                                    t . getMessage ());
                        }
                    });
                } else {
                    tvMessage . setText ( "id_lokasi harus diisi" );
                }
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
