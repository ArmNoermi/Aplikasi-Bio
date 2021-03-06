package com.arm.aplikasibio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText NIM, Nama, TanggalLahir, Alamat;
    private Spinner Jurusan;
    private RadioButton Male, Female;

    //Variable Untuk Menyimpan Input Dari Ueer
    private String setNIM, setNama, setTanggalLahir, setAlamat, setJurusan, setJenisKelamin;

    //Variable Untuk Inisialisasi Database DBMahasiswa
    private DBMahasiswa dbMahasiswa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button simpan = findViewById(R.id.save);
        NIM = findViewById(R.id.nim);
        Nama = findViewById(R.id.nama);
        TanggalLahir = findViewById(R.id.date);
        Male = findViewById(R.id.male);
        Female = findViewById(R.id.female);
        Jurusan = findViewById(R.id.jurusan);
        Alamat = findViewById(R.id.alamat);

        //Inisialisasi dan Mendapatkan Konteks dari DBMahasiswa
        dbMahasiswa = new DBMahasiswa(getBaseContext());

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setData();
                saveData();
                Toast.makeText(getApplicationContext(),"Data Berhasil Disimpan", Toast.LENGTH_SHORT).show();
                clearData();
            }
        });
    }

    //Berisi Statement-Statement Untuk Mendapatkan Input Dari User
    private void setData(){
        setNIM = NIM.getText().toString();
        setNama = Nama.getText().toString();
        setJurusan = Jurusan.getSelectedItem().toString();
        if(Male.isChecked()){
            setJenisKelamin = Male.getText().toString();
        }else if (Female.isChecked()){
            setJenisKelamin = Female.getText().toString();
        }
        setTanggalLahir = TanggalLahir.getText().toString();
        setAlamat = Alamat.getText().toString();
    }

    //Berisi Statement-Statement Untuk Menyimpan Data Pada Database
    private void saveData(){
        //Mendapatkan Repository dengan Mode Menulis
        SQLiteDatabase create = dbMahasiswa.getWritableDatabase();

        //Membuat Map Baru, Yang Berisi Nama Kolom dan Data Yang Ingin Dimasukan
        ContentValues values = new ContentValues();
        values.put(DBMahasiswa.MyColumns.NIM, setNIM);
        values.put(DBMahasiswa.MyColumns.Nama, setNama);
        values.put(DBMahasiswa.MyColumns.Jurusan, setJurusan);
        values.put(DBMahasiswa.MyColumns.JenisKelamin, setJenisKelamin);
        values.put(DBMahasiswa.MyColumns.TanggalLahir, setTanggalLahir);
        values.put(DBMahasiswa.MyColumns.Alamat, setAlamat);

        //Menambahkan Baris Baru, Berupa Data Yang Sudah Diinputkan pada Kolom didalam Database
        create.insert(DBMahasiswa.MyColumns.NamaTabel, null, values);
    }

    private void clearData(){
        NIM.setText("");
        Nama.setText("");
        TanggalLahir.setText("");
        Alamat.setText("");
    }
}