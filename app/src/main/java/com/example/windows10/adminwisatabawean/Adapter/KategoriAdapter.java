package com.example.windows10.adminwisatabawean.Adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.windows10.adminwisatabawean.LayarDetailKategori;
import com.example.windows10.adminwisatabawean.LayarDetailLokasi;
import com.example.windows10.adminwisatabawean.Model.Kategori;
import com.example.windows10.adminwisatabawean.Model.Lokasi;
import com.example.windows10.adminwisatabawean.R;


import java.util.List;

public class KategoriAdapter extends RecyclerView. Adapter < KategoriAdapter.KategoriViewHolder> {
    List<Kategori> mKategoriList;
    public KategoriAdapter(List<Kategori> kategoriList) {
        mKategoriList = kategoriList;
    }

    @Override
    public KategoriAdapter.KategoriViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_kategori, parent, false);
        KategoriAdapter.KategoriViewHolder mViewHolder = new KategoriAdapter.KategoriViewHolder(mView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(KategoriAdapter.KategoriViewHolder holder, final int position) {

        holder . tvNamaKategori . setText ( mKategoriList . get ( position ). getNamaKategori ());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(view.getContext(), LayarDetailKategori.class);
                mIntent.putExtra("id_kategori", mKategoriList.get(position).getIdKategori());
                mIntent.putExtra("nama_kategori", mKategoriList.get(position).getNamaKategori());
                view.getContext().startActivity(mIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mKategoriList.size();
    }

    public class KategoriViewHolder extends RecyclerView.ViewHolder {
        public TextView tvNamaKategori;

        public KategoriViewHolder(View itemView) {
            super(itemView);

            tvNamaKategori = (TextView) itemView.findViewById(R.id.tvNamaKategoriContent);

        }
    }
}
