package com.example.windows10.adminwisatabawean;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PageAdmin extends AppCompatActivity {
    private Button btn_wisata, btn_homestay, btn_lokasi, btn_kategori, btn_galeri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_admin);

        Intent mIntent = getIntent();

        TextView username = (TextView) findViewById(R.id.username);
        username.setText(mIntent.getStringExtra("username"));

        btn_wisata = (Button) findViewById(R.id.btn_wisata);
        btn_homestay = (Button) findViewById(R.id.btn_homestay);
        btn_lokasi = (Button) findViewById(R.id.btn_lokasi);
        btn_kategori = (Button) findViewById(R.id.btn_kategori);
        btn_galeri = (Button) findViewById(R.id.btn_galeri);
        btn_wisata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PageAdmin.this, MainActivity.class);
                startActivity(intent);
            }
        });
        btn_homestay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PageAdmin.this, LayarListHomestay.class);
                startActivity(intent);
            }
        });
        btn_lokasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PageAdmin.this, LayarListLokasi.class);
                startActivity(intent);
            }
        });
        btn_kategori.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PageAdmin.this, LayarListKategori.class);
                startActivity(intent);
            }
        });
        btn_galeri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PageAdmin.this, LayarListGaleri.class);
                startActivity(intent);
            }
        });
    }

    public void button_onClick(View view){
        SharedPreferences set = this.getSharedPreferences("key", Context.MODE_PRIVATE );
        SharedPreferences . Editor editor = set.edit();
        editor.clear();
        editor.commit();
        finish();

        Intent i = new Intent(this.getApplicationContext(), LayarLogin.class);
    }
}
