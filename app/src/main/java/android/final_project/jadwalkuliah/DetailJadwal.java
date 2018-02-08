package android.final_project.jadwalkuliah;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DetailJadwal extends AppCompatActivity {
    Button btnEdit;
    int id;
    TextView matkul, hari, jam, ruang, dosen, nodosen, catatan;
    Button edit;
    JadwalKuliahHelper myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_jadwal);
        btnEdit = findViewById(R.id.btn_edit);

        myDb  = new JadwalKuliahHelper(this);

        matkul = findViewById(R.id.matkul);
        hari = findViewById(R.id.hari);
        jam = findViewById(R.id.jam);
        ruang = findViewById(R.id.ruang);
        dosen = findViewById(R.id.dosen);
        nodosen = findViewById(R.id.hp);
        catatan = findViewById(R.id.catatan);

        try {
            //get intent to get person id
            id = getIntent().getIntExtra("ID", 1);

        } catch (Exception e) {
            e.printStackTrace();
        }

        final JadwalKuliah detail = myDb.getOne(id);
        //Toast.makeText(DetailJadwal.this, detail.getHari(), Toast.LENGTH_LONG).show();

        matkul.setText(": " + detail.getJudulMatkul());
        hari.setText(": " + detail.getHari());
        jam.setText(": " + detail.getJamMulai());
        ruang.setText(": " + detail.getRuangan());
        dosen.setText(": " + detail.getNamaDosen());
        nodosen.setText(": " + detail.getNoHpDosen());
        catatan.setText(": " + detail.getCatatan());

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getEdit(detail.getmId());
            }
        });

    }

    private void getEdit(int id){
        Intent goToUpdate = new Intent(getApplicationContext(), EditJadwal.class);
        goToUpdate.putExtra("ID", id);
        startActivity(goToUpdate);
    }
}
