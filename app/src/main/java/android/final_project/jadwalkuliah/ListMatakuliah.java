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

    private final LinkedList<String> Jam = new LinkedList<>();
    private final LinkedList<String> Hari = new LinkedList<>();
    private final LinkedList<String> Matakuliah = new LinkedList<>();
    private final LinkedList<String> Ruangan = new LinkedList<>();

    private JadwalKuliahHelper mDB;
    private RecyclerView mRecyclerView;
    private AdapterJadwalKuliah mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_matakuliah);

        mDB = new JadwalKuliahHelper(this);

        /*for (int i = 0; i < 3; i++) {
            mWordList1.addLast(timkita[i]);
            mWordList2.addLast(timlawan[i]);
        }*/

        //	Get	a	handle	to	the	RecyclerView.
        mRecyclerView = findViewById(R.id.list_matakuliah);

        //	Create	an	adapter	and	supply	the	data	to	be	displayed.
        mAdapter = new AdapterJadwalKuliah(this, mDB);

        //	Connect	the	adapter	with	the	RecyclerView.
        mRecyclerView.setAdapter(mAdapter);

        //	Give	the	RecyclerView	a	default	layout	manager.
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}
