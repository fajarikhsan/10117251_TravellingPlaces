package com.example.a10117251_travellingplaces.view;

//Tanggal pengerjaan : 08-08-2020
////NIM : 10117251
////Nama : Fajar Ikhsanul Faaizin
////Kelas : IF-8

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.example.a10117251_travellingplaces.controller.DBDataSource;
import com.example.a10117251_travellingplaces.model.Model;
import com.example.a10117251_travellingplaces.R;
import com.example.a10117251_travellingplaces.view.adapter.ViewPagerAdapter;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class PrimaryActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ViewPagerAdapter viewPagerAdapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private DrawerLayout drawer;
    private long backPressedTime;
    private Toast backToast;

    private DBDataSource dataSource;
    private ArrayList<Model> values;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primary);

        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Travelling Places");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Tabbed activity
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        //viewPagerAdapter
        viewPagerAdapter.addFragment(new Page1(), "Home");
        viewPagerAdapter.addFragment(new Page2(), "Settings");
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_home_black_24dp);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_settings_black_24dp);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        dataSource = new DBDataSource(this);
        dataSource.open();
        values = dataSource.getAllData();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case  R.id.home :
                drawer.closeDrawer(GravityCompat.START);
                break;

            case R.id.profile :
                Intent intent = new Intent(PrimaryActivity.this,ActivityProfile.class);
                startActivity(intent);
                break;

            case  R.id.travelList :
                Intent intent2 = new Intent(PrimaryActivity.this, PlacesActivity.class);
                startActivity(intent2);
                break;

            case  R.id.favTravelList :
                Intent intent4 = new Intent(PrimaryActivity.this, FavActivity.class);
                startActivity(intent4);
                break;

            case R.id.exit :
                System.exit(0);
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (backPressedTime + 2000 > System.currentTimeMillis() && !drawer.isDrawerOpen(GravityCompat.START)) {
            backToast.cancel();
            super.onBackPressed();
            return;
        } else {
            backToast = Toast.makeText(getBaseContext(), "Press back again to exit", Toast.LENGTH_SHORT);
            backToast.show();
        }
        backPressedTime = System.currentTimeMillis();
    }
}