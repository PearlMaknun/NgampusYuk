package android.final_project.jadwalkuliah;

/**
 * Created by RND05 on 2/6/2018.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import static android.content.ContentValues.TAG;

/**
 * Created by Lu'lu' on 02/02/2018.
 */

public class JadwalKuliahHelper extends SQLiteOpenHelper {

    //private static final String TAG = JadwalKuliahHelper.class.getSimpleName();

    private static final int DATABASE_VERSION = 3;
    private static final String TABLE_NAME = "jadwalkuliah";
    private static final String DATABASE_NAME = "final_project.db";
    private static final String KEY_ID = "ID";
    private static final String COLUMNS_ONE = "JUDUL";
    private static final String COLUMNS_TWO = "HARI";
    private static final String COLUMNS_THREE = "JAM";
    private static final String COLUMNS_FOUR = "RUANGAN";
    private static final String COLUMNS_FIVE = "DOSEN";
    private static final String COLUMNS_SIX = "NO_DOSEN";
    private static final String COLUMNS_SEVEN = "CATATAN";

    SQLiteDatabase mReadableDB;

    public JadwalKuliahHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE jadwalkuliah (ID INTEGER PRIMARY KEY AUTOINCREMENT, JUDUL TEXT NOT NULL, HARI TEXT NOT NULL, JAM TEXT NOT NULL, RUANGAN TEXT NOT NULL, DOSEN TEXT NOT NULL, NO_DOSEN TEXT, CATATAN TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public JadwalKuliah query(int position) {
        String query = "SELECT  * FROM " + TABLE_NAME +
                " ORDER BY " + KEY_ID + " ASC " +
                "LIMIT " + position + ",1";

        Cursor cursor = null;
        JadwalKuliah entry = new JadwalKuliah();

        try {
            if (mReadableDB == null) {mReadableDB = getReadableDatabase();}
            cursor = mReadableDB.rawQuery(query, null);
            cursor.moveToFirst();
            entry.setmId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
            entry.setHari(cursor.getString(cursor.getColumnIndex(COLUMNS_TWO)));
            entry.setJamMulai(cursor.getString(cursor.getColumnIndex(COLUMNS_THREE)));
            entry.setJudulMatkul(cursor.getString(cursor.getColumnIndex(COLUMNS_ONE)));
            entry.setRuangan(cursor.getString(cursor.getColumnIndex(COLUMNS_FOUR)));
        } catch (Exception e) {
            Log.d(TAG, "QUERY EXCEPTION! " + e.getMessage());
        } finally {
            // Must close cursor and db now that we are done with it.
            cursor.close();
            return entry;
        }
    }

    public JadwalKuliah getOne(int position) {
        String query = "SELECT * FROM " + TABLE_NAME +
                " WHERE " + KEY_ID + " = " + position;

        Cursor cursor = null;
        JadwalKuliah entry = new JadwalKuliah();

        try {
            if (mReadableDB == null) {mReadableDB = getReadableDatabase();}
            cursor = mReadableDB.rawQuery(query, null);
            cursor.moveToFirst();
            entry.setmId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
            entry.setJudulMatkul(cursor.getString(cursor.getColumnIndex(COLUMNS_ONE)));
            entry.setHari(cursor.getString(cursor.getColumnIndex(COLUMNS_TWO)));
            entry.setJamMulai(cursor.getString(cursor.getColumnIndex(COLUMNS_THREE)));
            entry.setRuangan(cursor.getString(cursor.getColumnIndex(COLUMNS_FOUR)));
            entry.setNamaDosen(cursor.getString(cursor.getColumnIndex(COLUMNS_FIVE)));
            entry.setNoHpDosen(cursor.getString(cursor.getColumnIndex(COLUMNS_SIX)));
            entry.setCatatan(cursor.getString(cursor.getColumnIndex(COLUMNS_SEVEN)));
        } catch (Exception e) {
            Log.d(TAG, "QUERY EXCEPTION! " + e.getMessage());
        } finally {
            // Must close cursor and db now that we are done with it.
            cursor.close();
            return entry;
        }
    }

    public long count() {
        if (mReadableDB == null) {mReadableDB = getReadableDatabase();}
        return DatabaseUtils.queryNumEntries(mReadableDB, TABLE_NAME);
    }

    public void insertData(String Judul, String Hari, String Jam, String Ruangan, String Dosen, String NoDosen, String Catatan) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMNS_ONE, Judul);
        values.put(COLUMNS_TWO, Hari);
        values.put(COLUMNS_THREE, Jam);
        values.put(COLUMNS_FOUR, Ruangan);
        values.put(COLUMNS_FIVE, Dosen);
        values.put(COLUMNS_SIX, NoDosen);
        values.put(COLUMNS_SEVEN, Catatan);
        db.insert(TABLE_NAME, null, values);
    }

    public void updateData(int id, String Judul, String Hari, String Jam, String Ruangan, String Dosen, String NoDosen, String Catatan) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ID, id);
        values.put(COLUMNS_ONE, Judul);
        values.put(COLUMNS_TWO, Hari);
        values.put(COLUMNS_THREE, Jam);
        values.put(COLUMNS_FOUR, Ruangan);
        values.put(COLUMNS_FIVE, Dosen);
        values.put(COLUMNS_SIX, NoDosen);
        values.put(COLUMNS_SEVEN, Catatan);
        String _id = String.valueOf(id);
        db.update(TABLE_NAME, values, "ID=?", new String[] {_id});
    }
}
