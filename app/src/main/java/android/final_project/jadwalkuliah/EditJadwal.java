package android.final_project.jadwalkuliah;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class EditJadwal extends AppCompatActivity {
    Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_jadwal);

        spinner = (Spinner)findViewById(R.id.hari);
        String[] hari={"Senin","Selasa","Rabu","Kamis","Jum'at","Sabtu","Minggu"};
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,hari);
        spinner.setAdapter(adapter);
    }
}
