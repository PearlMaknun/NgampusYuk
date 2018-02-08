package android.final_project.jadwalkuliah;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class EditJadwal extends AppCompatActivity {
    int id;
    JadwalKuliahHelper myDb = new JadwalKuliahHelper(this);
    AutoCompleteTextView Matkul, Hari, Jam, Ruangan, Dosen, NoHp, Catatan;
    Button Simpan, Reset;
    Calendar calendar = Calendar.getInstance();
    String jam;

    private static final String[] DAYS = new String[] {
            "Senin", "Selasa", "Rabu", "Kamis", "Jumat", "Sabtu"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_jadwal);

        Matkul = findViewById(R.id.nama_matkul);
        Hari = findViewById(R.id.hari);
        Jam = findViewById(R.id.jam_mulai);
        Ruangan = findViewById(R.id.kode_ruangan);
        Dosen = findViewById(R.id.nama_dosen);
        NoHp = findViewById(R.id.nohp_dosen);
        Catatan = findViewById(R.id.catatan);
        Simpan = findViewById(R.id.input_button);
        Reset = findViewById(R.id.reset_button);


        Jam.setOnClickListener(pilihJam);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, DAYS);
        Hari.setAdapter(adapter);

        Hari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                in.hideSoftInputFromWindow(view.getWindowToken(), 0);
                Hari.showDropDown();
            }
        });

        try {
            //get intent to get person id
            id = getIntent().getIntExtra("ID", 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        final JadwalKuliah detail = myDb.getOne(id);

        Matkul.setText(detail.getJudulMatkul());
        Hari.setText(detail.getHari());
        Jam.setText(detail.getJamMulai());
        Ruangan.setText(detail.getRuangan());
        Dosen.setText(detail.getNamaDosen());
        NoHp.setText(detail.getNoHpDosen());
        Catatan.setText(detail.getCatatan());

        Simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String matkul = Matkul.getText().toString();
                String hari = Hari.getText().toString();
                String jam = Jam.getText().toString();
                String ruangan = Ruangan.getText().toString();
                String dosen = Dosen.getText().toString();
                String nohp = NoHp.getText().toString();
                String catatan = Catatan.getText().toString();

                if (matkul.length()!= 0 && hari.length()!= 0 && jam.length()!= 0 && ruangan.length()!= 0 && dosen.length()!= 0) {
                    updateData(id, matkul, hari, jam, ruangan, dosen, nohp, catatan);
                }
                else
                    Toast.makeText(EditJadwal.this, "Pastikan kolom sudah terisi!", Toast.LENGTH_LONG).show();
            }
        });

        Reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Matkul.setText("");
                Hari.setText("");
                Jam.setText("");
                Ruangan.setText("");
                Dosen.setText("");
                NoHp.setText("");
                Catatan.setText("");
            }
        });
    }

    public View.OnClickListener pilihJam=new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            in.hideSoftInputFromWindow(v.getWindowToken(), 0);
            jamMulai();
        }
    };

    private void jamMulai(){
        new TimePickerDialog(this, t, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show();
    }

    TimePickerDialog.OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
            calendar.set(Calendar.MINUTE, minute);

            String hour_string = String.valueOf(hourOfDay);
            String minute_string = String.valueOf(minute);

            String jam = hour_string + ":" + minute_string;

            Jam.setText(jam);
        }
    };

    public void updateData(int _id, String m, String h, String j, String r, String d, String n, String c){
        try{
            myDb.updateData(_id, m, h, j, r, d, n, c);
            Toast.makeText(EditJadwal.this, "Jadwal Berhasil Di Edit!", Toast.LENGTH_LONG).show();
            Intent goToUpdate = new Intent(getApplicationContext(), DetailJadwal.class);
            goToUpdate.putExtra("ID", id);
            startActivity(goToUpdate);
        }catch (Exception e){
            Toast.makeText(EditJadwal.this, "Gagal Di Edit!", Toast.LENGTH_LONG).show();
        }
    }
}
