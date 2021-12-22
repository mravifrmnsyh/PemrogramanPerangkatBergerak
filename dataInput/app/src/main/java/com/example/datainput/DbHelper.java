package com.example.datainput;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "data_penduduk.db";
    public static final String TABLE_NAME = "table_penduduk";
    public static final String COL_1 = "nama";
    public static final String COL_2 = "nik";
    public static final String COL_3 = "ttl";
    public static final String COL_4 = "alamat";
    public static final String COL_5 = "telp";
    public static final String COL_6 = "gender";
    public static final int DATABASE_VERTION = 1;

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERTION);

        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE table_penduduk (nama text null, nik text null primary key, ttl text null, alamat text null, telp text null, gender text null)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    // method untuk tambah data
    public Boolean insertData(String nama, String nik, String ttl, String alamat, String telp, String gender){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, nama);
        contentValues.put(COL_2, nik);
        contentValues.put(COL_3, ttl);
        contentValues.put(COL_4, alamat);
        contentValues.put(COL_5, telp);
        contentValues.put(COL_6, gender);
        long result = db.insert(TABLE_NAME,null, contentValues);

        if (result == -1){
            return false;
        }else{
            return true;
        }
    }

    // method edit data
    public Boolean updateData(String nama, String nik, String ttl, String alamat, String telp, String gender){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        if (nama.equals("")) {
        }else{
            contentValues.put(COL_1, nama);
        }
        if (nik.equals("")) {
        }else{
            contentValues.put(COL_2, nik);
        }
        if (ttl.equals("")) {
        }else{
            contentValues.put(COL_3, ttl);
        }
        if (alamat.equals("")) {
        }else{
            contentValues.put(COL_4, alamat);
        }
        if (telp.equals("")) {
        }else{
            contentValues.put(COL_5, telp);
        }
        if (gender.equals("--Pilih--")) {
        }else{
            contentValues.put(COL_6, gender);
        }
        Cursor cursor = db.rawQuery("Select * from table_penduduk where nik = ?", new String[]{nik});
        if (cursor.getCount() > 0) {
            long result = db.update(TABLE_NAME, contentValues, "nik=?", new String[]{nik});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    //delete data
    public Boolean deleteData(String nik){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from table_penduduk where nik = ?", new String[]{nik});
        if (cursor.getCount() > 0) {
            long result = db.delete("table_penduduk", "nik=?", new String[]{nik});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    // method untuk menampilkan data
    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from table_penduduk", null);
        return res;
    }
}
