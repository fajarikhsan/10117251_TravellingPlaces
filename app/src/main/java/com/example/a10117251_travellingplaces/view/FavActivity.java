package com.example.a10117251_travellingplaces.view;

//Tanggal pengerjaan : 09-08-2020
////NIM : 10117251
////Nama : Fajar Ikhsanul Faaizin
////Kelas : IF-8

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ButtonBarLayout;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.example.a10117251_travellingplaces.R;
import com.example.a10117251_travellingplaces.controller.DBDataSource;
import com.example.a10117251_travellingplaces.model.Model;
import com.example.a10117251_travellingplaces.view.adapter.PlacesListAdapter;
import com.google.android.material.navigation.NavigationView;

//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import com.example.a10117251_travellingplaces.R;

public class FavActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private ListView listView;
    private Button action_button;

    //inisialisasi kontroller
    private DBDataSource dataSource;

    //inisialisasi arraylist
    private ArrayList<Model> values;

    PlacesListAdapter placesListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav);
        listView = findViewById(R.id.favList);

        Toolbar placesToolbar = findViewById(R.id.favToolbar);
        setSupportActionBar(placesToolbar);
        getSupportActionBar().setTitle("Favorite Places");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drawer = findViewById(R.id.drawer_fav);
        NavigationView navigationView = findViewById(R.id.nav_fav_places);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, placesToolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setCheckedItem(R.id.favTravelList);

        dataSource = new DBDataSource(this);
        // buka kontroller
        dataSource.open();

        // ambil semua data barang
        values = dataSource.getFavData();

        placesListAdapter = new PlacesListAdapter(FavActivity.this, values);

//        // masukkan data barang ke array adapter
//        ArrayAdapter<Model> adapter = new ArrayAdapter<Model>(this,
//                android.R.layout.simple_list_item_1, values);

        // set adapter pada list
        ListView listView = (ListView) findViewById(R.id.favList);
        listView.setAdapter(placesListAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ListView listView = (ListView) findViewById(R.id.favList);
                final Model b = (Model) listView.getAdapter().getItem(i);
                String[] pilihan = {"Ubah","Hapus"};
                AlertDialog.Builder builder = new AlertDialog.Builder(FavActivity.this);
                builder.setTitle("Pilihan");
                builder.setItems(pilihan, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i){
                            case 0 :
                                Intent detail = new Intent(FavActivity.this, DetailActivity.class);
                                detail.putExtra("id", b.getId());
                                detail.putExtra("nama", b.getNama());
                                startActivity(detail);
                                break;

                            case 1 :
                                startActivity(getIntent());
                                break;
                        }
                    }
                });
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case  R.id.home :
                Intent intent2 = new Intent(FavActivity.this,PrimaryActivity.class);
                finish();
                startActivity(intent2);
                break;

            case R.id.profile :
                Intent intent1 = new Intent(FavActivity.this, ActivityProfile.class);
                finish();
                startActivity(intent1);
                break;

            case  R.id.travelList :
                Intent intent3 = new Intent(FavActivity.this, PlacesActivity.class);
                finish();
                startActivity(intent3);
                break;

            case  R.id.favTravelList :
                drawer.closeDrawer(GravityCompat.START);
                break;

            case R.id.exit :
                finish();
                System.exit(0);
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}