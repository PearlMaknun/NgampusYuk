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

public class InputJadwal extends AppCompatActivity {
    JadwalKuliahHelper db = new JadwalKuliahHelper(this);
    AutoCompleteTextView Matkul, Hari, Jam, Ruangan, Dosen, NoHp, Catatan;
    Button Simpan;
    Calendar calendar = Calendar.getInstance();
    String jam;

    private static final String[] DAYS = new String[] {
            "Senin", "Selasa", "Rabu", "Kamis", "Jumat", "Sabtu"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_jadwal);

        Matkul = findViewById(R.id.nama_matkul);
        Hari = findViewById(R.id.hari);
        Jam = findViewById(R.id.jam_mulai);
        Ruangan = findViewById(R.id.kode_ruangan);
        Dosen = findViewById(R.id.nama_dosen);
        NoHp = findViewById(R.id.nohp_dosen);
        Catatan = findViewById(R.id.catatan);
        Simpan = findViewById(R.id.email_sign_in_button);

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

    public void simpan(View v){
        String matkul = Matkul.getText().toString();
        String hari = Hari.getText().toString();
        String jam = Jam.getText().toString();
        String ruangan = Ruangan.getText().toString();
        String dosen = Dosen.getText().toString();
        String nohp = NoHp.getText().toString();
        String catatan = Catatan.getText().toString();

        if (matkul.length()!= 0 && hari.length()!= 0 && jam.length()!= 0 && ruangan.length()!= 0 && dosen.length()!= 0) {
            tambahData(matkul, hari, jam, ruangan, dosen, nohp, catatan);
        }
        else
            Toast.makeText(InputJadwal.this, "Pastikan kolom sudah terisi!", Toast.LENGTH_LONG).show();
    }

    public void tambahData(String m, String h, String j, String r, String d, String n, String c){
        try{
            db.insertData(m, h, j, r, d, n, c);
            Toast.makeText(InputJadwal.this, "Jadwal Berhasil Di Tambah!", Toast.LENGTH_LONG).show();
            Intent i = new Intent(InputJadwal.this, Dasboard.class);
            startActivity(i);
            finish();
        }catch (Exception e){
            Toast.makeText(InputJadwal.this, "Gagal Di Tambahkan!", Toast.LENGTH_LONG).show();
        }
    }
}
