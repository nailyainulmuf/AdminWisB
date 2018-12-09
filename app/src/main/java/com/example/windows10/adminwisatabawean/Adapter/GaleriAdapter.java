package com.example.windows10.adminwisatabawean.Adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.windows10.adminwisatabawean.LayarEditGaleri;
import com.example.windows10.adminwisatabawean.Model.Galeri;
import com.example.windows10.adminwisatabawean.R;
import com.example.windows10.adminwisatabawean.Rest.ApiClient;

import java.util.List;

public class GaleriAdapter extends RecyclerView. Adapter < GaleriAdapter.GaleriViewHolder> {
    List<Galeri> listGaleri ;
    public GaleriAdapter ( List < Galeri > listGaleri ) {
        this . listGaleri = listGaleri;
    }
    @Override
    public GaleriAdapter . GaleriViewHolder onCreateViewHolder ( ViewGroup parent ,
                                                                 int viewType ) {
        View view =
                LayoutInflater. from ( parent . getContext ()). inflate ( R. layout . list_galeri , parent ,
                        false );
        GaleriViewHolder mHolder = new GaleriViewHolder ( view );
        return mHolder ;
    }
    @Override
    public void onBindViewHolder ( GaleriAdapter . GaleriViewHolder holder , final
    int position ) {
        holder . tvJudul . setText ( listGaleri . get ( position ). getJudul ());
        holder . tvTanggal . setText ( listGaleri . get ( position ). getTgl ());


        if ( listGaleri . get ( position ). getPhotoUrl () != null ){
//Picasso.with(holder.itemView.getContext()).load(ApiClient.BASE_URL+listPembeli.get(position).getPhotoId())
// .into(holder.mPhotoURL);
            Glide. with ( holder . itemView . getContext ()). load ( ApiClient.BASE_URL + listGaleri . get
                    ( position ). getPhotoUrl ())
                    . into ( holder . mPhotoURL );
        } else {
//Picasso.with(holder.itemView.getContext()).load(R.drawable.photoid).into(holder
// .mPhotoURL);
            Glide . with ( holder . itemView . getContext ()). load ( R . drawable . backwis ). into ( holder
                    . mPhotoURL );
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(view.getContext(), LayarEditGaleri.class);
                in.putExtra("id_galeri",listGaleri.get(position).getIdGaleri());
                in.putExtra("judul",listGaleri.get(position).getJudul());
                in.putExtra("tgl",listGaleri.get(position).getTgl());
                in.putExtra("photo_url",listGaleri.get(position).getPhotoUrl());
                view.getContext().startActivity(in);
            }
        });
    }
    @Override
    public int getItemCount () {
        return listGaleri . size ();
    }
    public class GaleriViewHolder extends RecyclerView . ViewHolder {
        ImageView mPhotoURL ;
        TextView tvJudul , tvTanggal;
        public GaleriViewHolder ( View itemView ) {
            super ( itemView );
            mPhotoURL = ( ImageView ) itemView . findViewById ( R . id . imgWisata );
            tvJudul = ( TextView ) itemView . findViewById ( R . id . tvNama );
            tvTanggal = ( TextView ) itemView . findViewById ( R . id . tvTanggal );
        }
    }
}
