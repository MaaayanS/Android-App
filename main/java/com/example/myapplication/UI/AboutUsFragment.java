package com.example.myapplication.UI;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

//a Fragment that telling  about the app

public class AboutUsFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return  inflater.inflate(R.layout.fragment_about_us,container,false) ;

        View viewYoutube = inflater.inflate(R.layout.fragment_about_us, container, false);
        FloatingActionButton fab = (FloatingActionButton) viewYoutube.findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.youtube.com/watch?v=L3HQMbQAWRc"));
                startActivity(intent);
            }
        });
        return viewYoutube;

    }
}