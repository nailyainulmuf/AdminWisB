package com.example.windows10.adminwisatabawean.Adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.windows10.adminwisatabawean.LayarEditWisata;
import com.example.windows10.adminwisatabawean.Model.Wisata;
import com.example.windows10.adminwisatabawean.R;
import com.example.windows10.adminwisatabawean.Rest.ApiClient;

import java.util.List;

public class WisataAdapter extends RecyclerView. Adapter < WisataAdapter.WisataViewHolder> {
    List<Wisata> listWisata ;
    public WisataAdapter ( List < Wisata > listWisata ) {
        this . listWisata = listWisata ;
    }
    @Override
    public WisataAdapter . WisataViewHolder onCreateViewHolder ( ViewGroup parent ,
                                                                   int viewType ) {
        View view =
                LayoutInflater. from ( parent . getContext ()). inflate ( R. layout . list_wisata , parent ,
                        false );
        WisataViewHolder mHolder = new WisataViewHolder ( view );
        return mHolder ;
    }
    @Override
    public void onBindViewHolder ( WisataAdapter . WisataViewHolder holder , final
    int position ) {
        holder . tvNama . setText ( listWisata . get ( position ). getNamaWisata ());
        holder . tvDeskripsi . setText ( listWisata . get ( position ). getDeskripsi ());
        holder . tvAlamat . setText ( listWisata . get ( position ). getAlamat ());
        holder . tvKategori . setText ( listWisata . get ( position ). getNamaKategori ());

        if ( listWisata . get ( position ). getPhotoUrl () != null ){
//Picasso.with(holder.itemView.getContext()).load(ApiClient.BASE_URL+listPembeli.get(position).getPhotoId())
// .into(holder.mPhotoURL);
            Glide. with ( holder . itemView . getContext ()). load ( ApiClient.BASE_URL + listWisata . get
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
                Intent in = new Intent(view.getContext(), LayarEditWisata.class);
                in.putExtra("id_wisata",listWisata.get(position).getIdWisata());
                in.putExtra("nama_wisata",listWisata.get(position).getNamaWisata());
                in.putExtra("deskripsi",listWisata.get(position).getDeskripsi());
                in.putExtra("id_kategori",listWisata.get(position).getIdKategori());
                in.putExtra("id_lokasi",listWisata.get(position).getIdLokasi());
                in.putExtra("photo_url",listWisata.get(position).getPhotoUrl());
                view.getContext().startActivity(in);
            }
        });
    }
    @Override
    public int getItemCount () {
        return listWisata . size ();
    }
    public class WisataViewHolder extends RecyclerView . ViewHolder {
        ImageView mPhotoURL ;
        TextView tvNama , tvDeskripsi , tvAlamat , tvKategori ;
        public WisataViewHolder ( View itemView ) {
            super ( itemView );
            mPhotoURL = ( ImageView ) itemView . findViewById ( R . id . imgWisata );
            tvNama = ( TextView ) itemView . findViewById ( R . id . tvNama );
            tvDeskripsi = ( TextView ) itemView . findViewById ( R . id . tvDeskripsiContent );
            tvAlamat = ( TextView ) itemView . findViewById ( R . id . tvAlamatContent );
            tvKategori = ( TextView ) itemView . findViewById ( R . id . tvKategori );
        }
    }
}
