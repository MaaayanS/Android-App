package com.example.myapplication.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.location.LocationManager;
import android.widget.TextView;
import android.widget.Toast;
//import android.widget.Toolbar;

import com.example.myapplication.R;
import com.google.android.material.navigation.NavigationView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

//The main activity with which the app opens
//enter datiels in room & FireBase
public class EnterActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Button addParselButton;
    private Button historyButton;

    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.draw_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener((toggle));
        toggle.syncState();

    }


    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        //if the draw is not open
        else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            //open add_Location activity
            case R.id.add_location:
                Intent intent1 = new Intent(this, LocationActivity.class);
                startActivity(intent1);
                break;
            //open info_us fragment
            case R.id.info_us:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new AboutUsFragment()).commit();
                break;
            //open deliver activity
            case R.id.deliver:
                Intent intent3 = new Intent(this, deliverActivity.class);
                startActivity(intent3);
                break;
            //open history_parcel activity
            case R.id.history_parcel:
                Intent intent4 = new Intent(this, HistoryParchelActivity.class);
                startActivity(intent4);
                break;

        }
        return true;
    }

}
