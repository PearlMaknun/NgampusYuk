package android.final_project.jadwalkuliah;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.LinkedList;

public class ListMatakuliah extends AppCompatActivity {

    private RecyclerView.LayoutManager mLayoutManager;
    private String filter = "";
    private JadwalKuliahHelper myDB;
    private RecyclerView mRecyclerView;
    private AdapterJadwalKuliah mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_matakuliah);

        mRecyclerView = findViewById(R.id.list_matakuliah);
        mLayoutManager = new LinearLayoutManager(this);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        populaterecyclerView(filter);

    }

    private void populaterecyclerView(String filter){
        myDB = new JadwalKuliahHelper(this);
        mAdapter = new AdapterJadwalKuliah(myDB.jadwalList(filter), this, mRecyclerView);
        mRecyclerView.setAdapter(mAdapter);

    }
}
