package android.final_project.jadwalkuliah;

/**
 * Created by RND05 on 2/6/2018.
 */

public class JadwalKuliah {

    private int mId;
    String JudulMatkul;
    String Hari;
    String JamMulai;
    String Ruangan;
    String NamaDosen;
    String NoHpDosen;
    String Catatan;

    public JadwalKuliah(){
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getJudulMatkul() {
        return JudulMatkul;
    }

    public void setJudulMatkul(String JudulMatkul) {
        this.JudulMatkul = JudulMatkul;
    }

    public String getHari() {
        return Hari;
    }

    public void setHari(String Hari) {
        this.Hari = Hari;
    }

    public String getJamMulai() {
        return JamMulai;
    }

    public void setJamMulai(String JamMulai) {
        this.JamMulai = JamMulai;
    }

    public String getRuangan() {
        return Ruangan;
    }

    public void setRuangan(String Ruangan) {
        this.Ruangan = Ruangan;
    }

    public String getNamaDosen() {
        return NamaDosen;
    }

    public void setNamaDosen(String NamaDosen) {
        this.NamaDosen = NamaDosen;
    }

    public String getNoHpDosen() {
        return NoHpDosen;
    }

    public void setNoHpDosen(String NoHpDosen) {
        this.NoHpDosen = NoHpDosen;
    }

    public String getCatatan() {
        return Catatan;
    }

    public void setCatatan(String Catatan) {
        this.Catatan = Catatan;
    }

}
