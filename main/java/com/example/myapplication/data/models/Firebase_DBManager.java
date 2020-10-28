package com.example.myapplication.data.models;

import com.example.myapplication.entities.Parcel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class Firebase_DBManager {
    public interface Action<T> {
        void onSuccess(T obj);

        void onFailure(Exception exception);

        void onProgress(String status, double percent);
    }

    public interface NotifyDataChange<T> {
        void OnDataChanged(T obj);

        void onFailure(Exception exception);
    }

    static DatabaseReference ParcelsRef;
    static List<Parcel> ParcelList;

    static {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        ParcelsRef = database.getReference("Parcels");
        ParcelList = new ArrayList<>();

    }

}
