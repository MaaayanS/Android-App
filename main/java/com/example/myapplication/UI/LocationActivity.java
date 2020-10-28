package com.example.myapplication.UI;

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
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

//Activity that finds the location of the package

public class LocationActivity extends AppCompatActivity {

    TextView tv_Adress;
    // Acquire a reference to the system Location Manager
    LocationManager locationManager;
    // Define a listener that responds to location updates
    LocationListener locationListener;
    String stringLocation;
    public static final String EXTRA_TEXT = "com.example.myapplication.UI.EXTRA_TEXT";
    Button addParselButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        tv_Adress = findViewById(R.id.TextViewLocation);

        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);


        // Define a listener that responds to location updates
        locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                tv_Adress.setText(getPlace(location));
                stringLocation = "" + tv_Adress.getText();
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            public void onProviderEnabled(String provider) {
            }

            public void onProviderDisabled(String provider) {
            }
        };

        Button get_adress_button = findViewById(R.id.buttonLocation);
        get_adress_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setLocationManager();
            }
        });


        addParselButton = (Button) findViewById(R.id.addParcelButton);
        addParselButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LocationActivity.this, MainActivity.class);
                i.putExtra(EXTRA_TEXT, stringLocation);
                startActivity(i);
            }
        });


    }


    private void setLocationManager() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 5);
        } else {
            // Android version is lesser than 6.0 or the permission is already granted.
            locationManager.requestLocationUpdates(locationManager.GPS_PROVIDER, 0, 0, locationListener);
        }
    }

    public String getPlace(Location location) {

        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        List<Address> addresses = null;
        try {
            addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            if (addresses.size() > 0) {

                String address = addresses.get(0).getAddressLine(0);
                Double latitude = addresses.get(0).getLatitude();
                Double longitude = addresses.get(0).getLongitude();
                return latitude + "\n" + longitude + "\n" + address;
            }
            return "no place: \n (" + location.getLongitude() + " , " + location.getLatitude() + ")";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "IOException ...";
    }

}
