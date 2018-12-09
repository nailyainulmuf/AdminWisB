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

import com.example.windows10.adminwisatabawean.Adapter.GaleriAdapter;
import com.example.windows10.adminwisatabawean.Model.Galeri;
import com.example.windows10.adminwisatabawean.Model.GetGaleri;
import com.example.windows10.adminwisatabawean.Rest.ApiClient;
import com.example.windows10.adminwisatabawean.Rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LayarListGaleri extends AppCompatActivity {
    RecyclerView mRecyclerView ;
    RecyclerView.Adapter mAdapter ;
    RecyclerView.LayoutManager mLayoutManager ;
    Context mContext ;
    ApiInterface mApiInterface ;
    Button btGet, btAddData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layar_list_galeri);

        mContext = getApplicationContext ();
        mRecyclerView = ( RecyclerView ) findViewById ( R . id . recyclerView );
        mLayoutManager = new LinearLayoutManager( mContext );
        mRecyclerView . setLayoutManager ( mLayoutManager );
        btAddData = ( Button ) findViewById ( R . id . btAddData);
        btGet = ( Button ) findViewById ( R . id . btGet );
        btGet.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick ( View view ) {
                mApiInterface = ApiClient.getClient().create (ApiInterface.class );
                Call<GetGaleri> mGaleriCall = mApiInterface.getGaleri();
                mGaleriCall.enqueue ( new Callback<GetGaleri>() {
                    @Override
                    public void onResponse(Call<GetGaleri > call , Response<GetGaleri> response ) {
                        Log.d("Get Galeri" , response . body (). getStatus ());
                        List<Galeri> listGaleri = response . body (). getResult ();
                        mAdapter = new GaleriAdapter( listGaleri );
                        mRecyclerView . setAdapter ( mAdapter );
                    }
                    @Override
                    public void onFailure ( Call < GetGaleri > call , Throwable t ) {
                        Log.d ( "Get Galeri" , t . getMessage ());
                    }
                });
            }
        });
        btAddData.setOnClickListener ( new View . OnClickListener () {
            @Override
            public void onClick ( View view ) {
                Intent intent = new Intent ( mContext , LayarInsertGaleri . class );
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
