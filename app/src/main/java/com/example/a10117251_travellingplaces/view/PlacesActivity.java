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

public class PlacesActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

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
        setContentView(R.layout.activity_places);
        listView = findViewById(R.id.placesList);

        Toolbar placesToolbar = findViewById(R.id.placesToolBar);
        setSupportActionBar(placesToolbar);
        getSupportActionBar().setTitle("Places to Travel");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drawer = findViewById(R.id.drawer_places);
        NavigationView navigationView = findViewById(R.id.nav_view_places);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, placesToolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setCheckedItem(R.id.travelList);

        dataSource = new DBDataSource(this);
        // buka kontroller
        dataSource.open();

        // ambil semua data barang
        values = dataSource.getAllData();

        placesListAdapter = new PlacesListAdapter(PlacesActivity.this, values);

//        // masukkan data barang ke array adapter
//        ArrayAdapter<Model> adapter = new ArrayAdapter<Model>(this,
//                android.R.layout.simple_list_item_1, values);

        // set adapter pada list
        ListView listView = (ListView) findViewById(R.id.placesList);
        listView.setAdapter(placesListAdapter);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case  R.id.home :
                Intent intent2 = new Intent(PlacesActivity.this,PrimaryActivity.class);
                startActivity(intent2);
                break;

            case R.id.profile :
                Intent intent3 = new Intent(PlacesActivity.this, ActivityProfile.class);
                startActivity(intent3);
                break;

            case  R.id.travelList :
                drawer.closeDrawer(GravityCompat.START);
                break;

            case  R.id.favTravelList :
                Intent intent4 = new Intent(PlacesActivity.this, FavActivity.class);
                startActivity(intent4);
                break;

            case R.id.exit :
                System.exit(0);
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}