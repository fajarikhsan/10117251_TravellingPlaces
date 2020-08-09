package com.example.a10117251_travellingplaces.view;

//Tanggal pengerjaan : 09-08-2020
////NIM : 10117251
////Nama : Fajar Ikhsanul Faaizin
////Kelas : IF-8

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.codesgood.views.JustifiedTextView;
import com.example.a10117251_travellingplaces.R;
import com.example.a10117251_travellingplaces.controller.DBDataSource;
import com.example.a10117251_travellingplaces.model.Model;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener {

    private DBDataSource dataSource;

    private long id;
    private String nama, deskripsi, latitude, longitude, image;

    private TextView tv_nama, tv_deskripsi;
    private ImageView iv_image;

    private int img;

    private FloatingActionButton locationButton;

    private Model model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        // Set Collapsing Toolbar layout to the screen
        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);

        tv_nama = (TextView) findViewById(R.id.detail_title);
        tv_deskripsi = (JustifiedTextView) findViewById(R.id.detail_deskripsi);
        iv_image = (ImageView) findViewById(R.id.detail_image);

        //buat sambungan baru ke database
        dataSource = new DBDataSource(this);
        dataSource.open();

        // ambil data dari extras
        Bundle bun = this.getIntent().getExtras();
        id = bun.getLong("id");
        nama = bun.getString("nama");
        deskripsi = bun.getString("deskripsi");
        latitude = bun.getString("latitude");
        longitude = bun.getString("longitude");
        image = bun.getString("image");
        img = bun.getInt("img");

        // masukan data data nya
        tv_deskripsi.setText(Html.fromHtml(deskripsi));
        tv_nama.setText("Deskripsi");
        tv_deskripsi.setText(deskripsi);
        iv_image.setImageResource(img);

        // Set title of Detail page
         collapsingToolbar.setTitle(nama);

        locationButton = (FloatingActionButton) findViewById(R.id.fab);
        locationButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent i = new Intent(DetailActivity.this, MapActivity.class);
        i.putExtra("id", id);
        i.putExtra("nama", nama);
        i.putExtra("deskripsi", deskripsi);
        i.putExtra("latitude", latitude);
        i.putExtra("longitude", longitude);
        i.putExtra("image", image);
        i.putExtra("img", img);
        startActivity(i);
    }
}