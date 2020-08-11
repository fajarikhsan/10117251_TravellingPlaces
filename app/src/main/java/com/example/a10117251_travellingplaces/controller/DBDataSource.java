package com.example.a10117251_travellingplaces.controller;

//Tanggal pengerjaan : 08-08-2020
////NIM : 10117251
////Nama : Fajar Ikhsanul Faaizin
////Kelas : IF-8

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a10117251_travellingplaces.model.Model;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class DBDataSource extends AppCompatActivity {

    //inisialiasi SQLite Database
    private SQLiteDatabase database;

    //inisialisasi kelas DBHelper
    private DBHelper dbHelper;

    //ambil semua nama kolom
    private String[] allColumns = { DBHelper.COLUMN_ID, DBHelper.COLUMN_NAMA, DBHelper.COLUMN_DESKRIPSI,
            DBHelper.COLUMN_LATITUDE, DBHelper.COLUMN_LONGITUDE, DBHelper.COLUMN_IMAGE, DBHelper.COLUMN_STATUS};

    //DBHelper diinstantiasi pada constructor
    public DBDataSource(Context context) {
        dbHelper = new DBHelper(context);
    }

    //membuka/membuat sambungan baru ke database
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    //menutup sambungan ke database
    public void close() {
        dbHelper.close();
    }

    private Model cursorToModel(Cursor cursor)
    {
        // buat objek barang baru
        Model model = new Model();
        // debug LOGCAT
        Log.v("info", "The setLatLng "+cursor.getString(0)+","+cursor.getString(1));

        /* Set atribut pada objek model dengan
         * data kursor yang diambil dari database*/
        model.setId(cursor.getLong(0));
        model.setNama(cursor.getString(1));
        model.setDeskripsi(cursor.getString(2));
        model.setLatitude(cursor.getString(3));
        model.setLongitude(cursor.getString(4));
        model.setImage(cursor.getString(5));
        model.setStatus(cursor.getString(6));

        //kembalikan sebagai objek model
        return model;
    }

    //mengambil semua data barang
    public ArrayList<Model> getAllData() {
        ArrayList<Model> listPlaces = new ArrayList<Model>();

        // select all SQL query
        Cursor cursor = database.query(DBHelper.TABLE_NAME,
                allColumns, null, null, null, null, null);

        // pindah ke data paling pertama
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Model daftarTempat = cursorToModel(cursor);
            listPlaces.add(daftarTempat);
            cursor.moveToNext();
        }
        // Make sure to close the cursor
        cursor.close();
        return listPlaces;
    }

    //update favorite
    public void updateFav(Model b)
    {
        //ambil id teman
        String strFilter = "id=" + b.getId();
        //memasukkan ke content values
        ContentValues args = new ContentValues();
        //masukkan data sesuai dengan kolom pada database
        args.put(DBHelper.COLUMN_NAMA, b.getNama());
        args.put(DBHelper.COLUMN_DESKRIPSI, b.getDeskripsi());
        args.put(DBHelper.COLUMN_LATITUDE, b.getLatitude());
        args.put(DBHelper.COLUMN_LONGITUDE, b.getLongitude());
        args.put(DBHelper.COLUMN_IMAGE, b.getImage());
        args.put(DBHelper.COLUMN_STATUS, b.getStatus());
        //update query
        database.update(DBHelper.TABLE_NAME, args, strFilter, null);
    }

    public ArrayList<Model> getFavData() {
        ArrayList<Model> listPlaces = new ArrayList<Model>();

        String whereClause = "status = ?";
        String[] whereArgs = new String[] {
                "1"
        };

        // select all SQL query
        Cursor cursor = database.query(DBHelper.TABLE_NAME,
                allColumns, whereClause, whereArgs, null, null, null);

        // pindah ke data paling pertama
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Model daftarTempat = cursorToModel(cursor);
            listPlaces.add(daftarTempat);
            cursor.moveToNext();
        }
        // Make sure to close the cursor
        cursor.close();
        return listPlaces;
    }
}