package android.final_project.jadwalkuliah;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Lu'lu' on 08/02/2018.
 */

public class AdapterJadwalKuliah extends RecyclerView.Adapter<AdapterJadwalKuliah.MatakuliahViewHolder> {
    Context mContext;
    private RecyclerView mRecyclerV;
    private List<JadwalKuliah> mListJadwal;

    public class MatakuliahViewHolder extends RecyclerView.ViewHolder{
        public final TextView hari;
        public final TextView jam;
        public final TextView matakuliah;
        public final TextView ruang;

        public View layout;

        public MatakuliahViewHolder(final View itemView) {
            super(itemView);
            layout = itemView;
            hari = itemView.findViewById(R.id.harikuliah);
            jam = itemView.findViewById(R.id.jamkuliah);
            matakuliah = itemView.findViewById(R.id.matakuliah);
            ruang = itemView.findViewById(R.id.ruangan);
        }
    }

    public AdapterJadwalKuliah(List<JadwalKuliah> myDataset, Context context, RecyclerView recyclerView) {
        mListJadwal = myDataset;
        mContext = context;
        mRecyclerV = recyclerView;
    }

    @Override
    public AdapterJadwalKuliah.MatakuliahViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View mItemView = inflater.inflate(R.layout.adapter_list_matakuliah, parent, false);
        MatakuliahViewHolder vh = new MatakuliahViewHolder(mItemView);
        return vh;
    }

    @Override
    public void onBindViewHolder(MatakuliahViewHolder holder, final int position) {

        final JadwalKuliah current = mListJadwal.get(position);

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

        holder.layout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle("Hapus Jadwal");
                builder.setMessage("Apakah anda bermaksud menghapus jadwal ini?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        JadwalKuliahHelper myDB = new JadwalKuliahHelper(mContext);
                        myDB.deleteData(current.getmId());

                        mListJadwal.remove(position);
                        mRecyclerV.removeViewAt(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, mListJadwal.size());
                        notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.create().show();
                return false;
            }
        });


    }

    public void add(int position, JadwalKuliah jadwalKuliah) {
        mListJadwal.add(position, jadwalKuliah);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        mListJadwal.remove(position);
        notifyItemRemoved(position);
    }

    public void getDetail(int id){
        Intent goToDetail = new Intent(mContext, DetailJadwal.class);
        goToDetail.putExtra("ID", id);
        mContext.startActivity(goToDetail);
    }

    @Override
    public int getItemCount() {
        return mListJadwal.size();
    }


}