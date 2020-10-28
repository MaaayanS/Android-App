package com.example.myapplication.data.models;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.repositories.FirebaseQueryLiveData;
import com.example.myapplication.entities.*;
//import com.example.myapplication.entities.ParcelAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/*
accses to the fireBase
 */

public class ParcelFireBaseViewModel extends ViewModel {
    private static final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("Parcels");
    private static final DatabaseReference deliverParcel = FirebaseDatabase.getInstance().getReference("deliverParcels");

    private final FirebaseQueryLiveData liveData = new FirebaseQueryLiveData(mDatabase);
    private final FirebaseQueryLiveData liveData2 = new FirebaseQueryLiveData(deliverParcel);
    private ParcelViewModel parcelViewModel;


    @NonNull
    public LiveData<DataSnapshot> getdataSnapshotLiveData() {
        return liveData;
    }

    public String insert(Parcel newParcel) {

        ParcelAdapter parcelAdapter = new ParcelAdapter(newParcel);
        String idvalue = mDatabase.push().getKey();
        parcelAdapter.setPackageId(idvalue);
        mDatabase.child(idvalue).setValue(parcelAdapter);
        return idvalue;

    }

    public void remove(String parcelId) {
        DatabaseReference remove = FirebaseDatabase.getInstance().getReference("Parcels").child(parcelId);
        String deliverParcel = (remove.toString());

        remove.removeValue();
    }
}
