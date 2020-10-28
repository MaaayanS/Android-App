package com.example.myapplication.data.models;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.myapplication.entities.Parcel;
import com.example.myapplication.entities.ParcelAdapter;
import com.example.myapplication.repositories.ParcelRepository;

import java.util.List;

/*
view model for ROOM
*/
public class ParcelViewModel extends AndroidViewModel {
    private ParcelRepository mRepository;

    private LiveData<List<ParcelAdapter>> mAllpackegClasss;

    public ParcelViewModel(Application application)
    {
        super(application);
        mRepository = new ParcelRepository(application);
        mAllpackegClasss = mRepository.getAllpackegClasss();
    }

    LiveData<List<ParcelAdapter>> getAllpackegClasss() {
        return mAllpackegClasss;
    }

    public void insert(ParcelAdapter packegClass) {
        mRepository.insert(packegClass);
    }


    public void deletePackage(String packegID) {
        mRepository.deletePackage(packegID);
    }

    public void updatePackage(String name, String packegID) {
        mRepository.updatePackage(name, packegID);

    }

    public void updateStatus(String sta, String paID) {
        mRepository.updateStatus(sta, paID);

    }

}