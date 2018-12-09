package com.example.windows10.adminwisatabawean.Adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.windows10.adminwisatabawean.LayarDetailLokasi;
import com.example.windows10.adminwisatabawean.Model.Lokasi;
import com.example.windows10.adminwisatabawean.R;

import java.util.List;

public class LokasiAdapter extends RecyclerView. Adapter < LokasiAdapter.LokasiViewHolder> {
    List<Lokasi> mLokasiList;

    public LokasiAdapter(List<Lokasi> lokasiList) {
        mLokasiList = lokasiList;
    }

    @Override
    public LokasiViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_lokasi, parent, false);
        LokasiViewHolder mViewHolder = new LokasiViewHolder(mView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(LokasiViewHolder holder, final int position) {
       // holder . tvIdLokasi . setText ( mLokasiList . get ( position ). getIdLokasi ());
        holder . tvNamaLokasi . setText ( mLokasiList . get ( position ). getNamaLokasi ());
        holder . tvAlamat . setText ( mLokasiList . get ( position ). getAlamat ());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(view.getContext(), LayarDetailLokasi.class);
                mIntent.putExtra("id_lokasi", mLokasiList.get(position).getIdLokasi());
                mIntent.putExtra("nama_lokasi", mLokasiList.get(position).getNamaLokasi());
                mIntent.putExtra("alamat", mLokasiList.get(position).getAlamat());
                view.getContext().startActivity(mIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mLokasiList.size();
    }

    public class LokasiViewHolder extends RecyclerView.ViewHolder {
        public TextView tvIdLokasi, tvNamaLokasi, tvAlamat;

        public LokasiViewHolder(View itemView) {
            super(itemView);
            //tvIdLokasi = (TextView) itemView.findViewById(R.id.tvIdLokasi2);
            tvNamaLokasi = (TextView) itemView.findViewById(R.id.tvNamaLokasiContent);
            tvAlamat = (TextView) itemView.findViewById(R.id.tvAlamatContent);

        }
    }
}
