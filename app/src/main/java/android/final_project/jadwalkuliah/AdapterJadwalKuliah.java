package android.final_project.jadwalkuliah;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Lu'lu' on 08/02/2018.
 */

public class AdapterJadwalKuliah extends RecyclerView.Adapter<AdapterJadwalKuliah.MatakuliahViewHolder> {
    Context mContext;
    JadwalKuliahHelper mDB;
    int id;
    private LayoutInflater mInflater;

    public AdapterJadwalKuliah(Context context, JadwalKuliahHelper db) {
        mInflater = LayoutInflater.from(context);
        mContext = context;
        mDB = db;
    }

    @Override
    public AdapterJadwalKuliah.MatakuliahViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.adapter_list_matakuliah, parent, false);
        return new MatakuliahViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(MatakuliahViewHolder holder, int position) {

        final JadwalKuliah current = mDB.query(position);
        holder.hari.setText(current.getHari());
        holder.jam.setText(current.getJamMulai());
        holder.matakuliah.setText(current.getJudulMatkul());
        holder.ruang.setText((current.getRuangan()));

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDetail(current.getmId());
            }
        });


    }

    public void getDetail(int id){
        Intent goToDetail = new Intent(mContext, DetailJadwal.class);
        goToDetail.putExtra("ID", id);
        mContext.startActivity(goToDetail);
    }

    @Override
    public int getItemCount() {
        return (int) mDB.count();
    }

    public class MatakuliahViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final TextView hari;
        public final TextView jam;
        public final TextView matakuliah;
        public final TextView ruang;

        final AdapterJadwalKuliah mAdapter;

        public View layout;

        public MatakuliahViewHolder(final View itemView, AdapterJadwalKuliah adapter) {
            super(itemView);
            layout = itemView;
            hari = itemView.findViewById(R.id.harikuliah);
            jam = itemView.findViewById(R.id.jamkuliah);
            matakuliah = itemView.findViewById(R.id.matakuliah);
            ruang = itemView.findViewById(R.id.ruangan);
            this.mAdapter = adapter;
        }

        @Override
        public void onClick(View view) {

            //int mPosition = getLayoutPosition();

//            Get	the	position	of	the	item	that	was	clicked.
//            int mPosition = getLayoutPosition();
//
//            //	Use	that	to	access	the	affected	item	in	mWordList.
//            String element = mWordListHome.get(mPosition);
//
//            //	Change	the	word	in	the	mWordList.
//            mWordListHome.set(mPosition, "Clicked!	" + element);
//
//            //	Notify	the	adapter,	that	the	data	has	changed	so	it	can
//            //	update	the	RecyclerView	to	display	the	data.
//            mAdapter.notifyDataSetChanged();
        }
    }
}