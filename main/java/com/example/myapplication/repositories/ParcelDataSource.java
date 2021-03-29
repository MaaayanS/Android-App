package com.example.myapplication.repositories;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.myapplication.entities.Parcel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;
import java.util.List;

public class ParcelDataSource
{
    public interface NotifyDataChange<T> {
        void OnDataChanged(T obj);

        void onFailure(Exception exception);

    }

    static DatabaseReference parcelRef;
    static List<Parcel> parcelList;
    private static ChildEventListener parcelRefChildEventListener;

    static {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        parcelRef = database.getReference("Parcels");
        parcelList = new ArrayList<>();
    }

    public ParcelDataSource() {
    }

    public void addParcel(Parcel parcel) {
        parcelRef.child("" + parcel.getPackageId()).setValue(parcel).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
            }
        });


    }

}
