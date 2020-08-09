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

import com.example.a10117251_travellingplaces.R;
import com.google.android.material.navigation.NavigationView;

public class ActivityProfile extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Toolbar menuToolbar = findViewById(R.id.menuToolBar);
        setSupportActionBar(menuToolbar);
        getSupportActionBar().setTitle("Profil");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drawer = findViewById(R.id.drawer_profile);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, menuToolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setCheckedItem(R.id.profile);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case  R.id.home :
                Intent intent2 = new Intent(ActivityProfile.this, PrimaryActivity.class);
                finish();
                startActivity(intent2);
                break;

            case R.id.profile :
                drawer.closeDrawer(GravityCompat.START);
                break;

            case  R.id.travelList :
                Intent intent3 = new Intent(ActivityProfile.this, PlacesActivity.class);
                finish();
                startActivity(intent3);
                break;

            case  R.id.favTravelList :
                Intent intent4 = new Intent(ActivityProfile.this, FavActivity.class);
                finish();
                startActivity(intent4);
                break;

            case R.id.exit :
                finish();
                System.exit(0);
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
