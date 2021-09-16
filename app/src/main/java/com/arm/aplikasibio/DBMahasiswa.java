package com.arm.aplikasibio;

import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class DBMahasiswa extends SQLiteOpenHelper {
    //InnerClass, untuk mengatur artibut seperti Nama Tabel, nama-nama kolom dan Query
    static abstract class MyColumns implements BaseColumns {
        //Menentukan Nama Table dan Kolom
        static final String NamaTabel = "Mahasiswa";
        static final String NIM = "NIM";
        static final String Nama = "Nama_Mahasiswa";
        static final String Jurusan = "Jurusan";
        static final String JenisKelamin = "Jenis_Kelamin";
        static final String TanggalLahir = "Tanggal_Lahir";
        static final String Alamat = "Alamat";
    }

    private static final String NamaDatabse = "unpi.db";
    private static final int VersiDatabase = 14;

    //Query yang digunakan untuk membuat Tabel
    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE "+MyColumns.NamaTabel+
            "("+MyColumns.NIM+" TEXT PRIMARY KEY, "+MyColumns.Nama+" TEXT NOT NULL, "+MyColumns.Jurusan+
            " TEXT NOT NULL, "+MyColumns.JenisKelamin+" TEXT NOT NULL, "+MyColumns.TanggalLahir+
            " TEXT NOT NULL, "+MyColumns.Alamat+" TEXT NOT NULL)";

    //Query yang digunakan untuk mengupgrade Tabel
    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS "+MyColumns.NamaTabel;

    DBMahasiswa(Context context) {
        super(context, NamaDatabse, null, VersiDatabase);
    }
}
