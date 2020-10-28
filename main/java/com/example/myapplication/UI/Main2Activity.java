package com.example.myapplication.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.myapplication.R;
import com.google.android.gms.common.internal.Objects;

//for the recycler view
public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        String teamone = getIntent().getStringExtra("teamone");
        String teamtwo = getIntent().getStringExtra("teamtwo");
        Toast.makeText(this, "" + teamone, Toast.LENGTH_SHORT).show();

    }
}
