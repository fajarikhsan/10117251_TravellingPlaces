package com.example.a10117251_travellingplaces.controller;

//Tanggal pengerjaan : 08-08-2020
////NIM : 10117251
////Nama : Fajar Ikhsanul Faaizin
////Kelas : IF-8

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;

public class DBHelper extends SQLiteOpenHelper {

    private DBDataSource dbDataSource;

    public static final String TABLE_NAME = "tbl_wisata";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAMA = "nama";
    public static final String COLUMN_DESKRIPSI = "deskripsi";
    public static final String COLUMN_LATITUDE = "latitude";
    public static final String COLUMN_LONGITUDE = "longitude";
    public static final String COLUMN_IMAGE = "image";
    public static final String COLUMN_STATUS = "status";
    private static final String db_name = "db_wisata.db";
    private static final int db_version = 1;

    //Perintah SQL untuk membuat tabel database baru
    private static final String db_create = "CREATE TABLE IF NOT EXISTS "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_NAMA + " VARCHAR(100), "
            + COLUMN_DESKRIPSI + " VARCHAR(1000), "
            + COLUMN_LATITUDE + " VARCHAR(100), "
            + COLUMN_LONGITUDE + " VARCHAR(100),"
            + COLUMN_IMAGE + " VARCHAR(100),"
            + COLUMN_STATUS + " VARCHAR(5));";

    public static final String tangkuban_perahu = "Gunung Tangkuban Parahu adalah salah satu gunung " +
            "berapi yang masih aktif di Jawa Barat. Jika dipandang dari atas Gedung Sate di kota Bandung, " +
            "bentuk gunung ini terlihat jelas seperti perahu besar yang terbalik. Memang dalam bahasa Sunda, " +
            "tangkuban parahu berarti perahu terbalik. Bentuk gunung ini dikaitkan dengan cerita rakyat yang " +
            "berjudul Sangkuriang. Dalam cerita itu dikisahkan, secara singkat, Sangkuriang yang marah karena " +
            "merasa tidak mampu menyelesaikan persyaratan untuk bisa menikahi Dayang Sumbi, ibunya sendiri, " +
            "akhirnya menendang perahu buatannya sehingga terbalik dan membentuk gunung.";

    public static final String gunung_putri = "Gunung Putri Lembang adalah salah satu pegunungan yang " +
            "terletak di kecamatan Lembang, Kabupaten Bandung Barat. Gunung ini berlokasi di Desa Cihideung " +
            "yang lokasinya tidak jauh dari Gunung Tangkuban Parahu. Gunung Putri Lembang merupakan salah " +
            "satu tempat wisata di Lembang yang berupa sebuah bukit yang arealnya banyak ditumbuhi dengan " +
            "rumput dan pohon kecil lainnya yang ditengahnya terdapat sebuah Tugu bernama Tugu Sespim.\n" +
            "\n" +
            "Kawasan hutan Gunung Putri Lembang dibina langsung oleh Perum Kesatuan Pemangkunan Hutan (KPH) " +
            "Bandung Utara sejak 28 juni 2016.";

    public static final String tebing_keraton = "Tebing Keraton atau Tebing Karaton merupakan sebuah " +
            "tebing yang berada di dalam kawasan Taman Hutan Raya Ir. H. Djuanda. Tebing ini terletak di " +
            "Kampung Ciharegem Puncak, Desa Ciburial, Bandung, Jawa Barat, Indonesia. Dari Tebing Keraton " +
            "dapat menikmati pemandangan spektakuler. Bukan lampu kota, melainkan hutan!";

    public static final String situ_patenggang = "Danau Situ Patenggang atau biasa disebut dengan Situ Patengan " +
            "merupakan danau yang letaknya di bagian Selatan Bandung, tepatnya di kawasan Ciwidey. Situ " +
            "Patenggang terletak berdekatan dengan salah satu tempat wisata favorit di daerah Bandung " +
            "Selatan yaitu Kawah Putih, jadi kalau anda sedang berlibur ke daerah Bandung Selatan, kawasan " +
            "Situ Patenggang ini bisa dijadikan dalam daftar tempat yang akan dikunjungi.";

    private static final String query = "INSERT INTO " + TABLE_NAME +
            "(nama, deskripsi, latitude, longitude, image, status)" +
            " VALUES ('Tangkuban Perahu', '" + tangkuban_perahu + "', '-6.7596', '107.6098', 'tangkuban_perahu', '0');";

    private static final String query1 = "INSERT INTO " + TABLE_NAME +
            "(nama, deskripsi, latitude, longitude, image, status)" +
            " VALUES ('Gunung Putri', '" + gunung_putri + "', '-6.8044', '107.6275', 'gunung_putri', '0');";

    private static final String query2 = "INSERT INTO " + TABLE_NAME +
            "(nama, deskripsi, latitude, longitude, image, status)" +
            " VALUES ('Tebing Keraton', '" + tebing_keraton + "', '-6.83406', '107.66361', 'tebing_keraton', '0');";

    private static final String query3 = "INSERT INTO " + TABLE_NAME +
            "(nama, deskripsi, latitude, longitude, image, status)" +
            " VALUES ('Situ Patenggang', '" + situ_patenggang + "', '-7.166965', '107.357534', 'situ_patenggang', '0');";


    public DBHelper(Context context) {
        super(context, db_name, null, db_version);
    }

    //Mengeksekusi perintah SQL di atas untuk membuat tabel database
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(db_create);
        db.execSQL(query);
        db.execSQL(query1);
        db.execSQL(query2);
        db.execSQL(query3);
    }

    //dijalankan ketika ingin mengupgrade database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(DBHelper.class.getName(), "Upgrading database from version "
                + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
