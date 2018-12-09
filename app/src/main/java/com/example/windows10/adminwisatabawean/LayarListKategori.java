package com.example.windows10.adminwisatabawean;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.windows10.adminwisatabawean.Adapter.KategoriAdapter;
import com.example.windows10.adminwisatabawean.Model.GetKategori;
import com.example.windows10.adminwisatabawean.Model.Kategori;
import com.example.windows10.adminwisatabawean.Rest.ApiClient;
import com.example.windows10.adminwisatabawean.Rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LayarListKategori extends AppCompatActivity {
    Button btGet, btAddData;
    ApiInterface mApiInterface;
    Context mContext ;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layar_list_kategori);

        btGet = (Button) findViewById(R.id.btGet);
        btAddData = (Button) findViewById(R.id.btAddData);
        mContext = getApplicationContext ();
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        btAddData = ( Button ) findViewById ( R . id . btAddData);
        btGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<GetKategori> kategoriCall = mApiInterface.getKategori();
                kategoriCall.enqueue(new Callback<GetKategori>() {
                    @Override
                    public void onResponse(Call<GetKategori> call, Response<GetKategori>
                            response) {
                        List<Kategori> kategoriList = response.body().getListDataKategori();
                        Log.d("Retrofit Get", "Jumlah data kategori: " +
                                String.valueOf(kategoriList.size()));
                        mAdapter = new KategoriAdapter(kategoriList);
                        mRecyclerView.setAdapter(mAdapter);
                    }

                    @Override
                    public void onFailure(Call<GetKategori> call, Throwable t) {
// Log error
                        Log.e("Retrofit Get", t.toString());
                    }
                });
            }
        });
        btAddData.setOnClickListener ( new View . OnClickListener () {
            @Override
            public void onClick ( View view ) {
                Intent intent = new Intent ( mContext , LayarInsertKategori . class );
                startActivity ( intent );
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu (Menu menu ) {
        getMenuInflater ().inflate ( R.menu.menu_layout , menu );
        return super . onCreateOptionsMenu ( menu );
    }
    @Override
    public boolean onOptionsItemSelected (MenuItem item ){
        Intent mIntent;
        switch (item.getItemId()) {
            case R.id.menuPageAdmin:
                mIntent = new Intent(this, PageAdmin.class);
                startActivity(mIntent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
