package com.example.myapplication.repositories;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Query;

import com.example.myapplication.entities.*;
import com.example.myapplication.entities.ParcelAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;


public class ParcelRepository {

    private ParcelDao mpackegClassDao;
    private LiveData<List<ParcelAdapter>> mAllpackegClasss;

    public ParcelRepository(Application application) {
        ParcelDatabase db = ParcelDatabase.getDatabase(application);
        mpackegClassDao = db.packegClassDao();
        mAllpackegClasss = mpackegClassDao.getAlphabetizedpackegClasss();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<ParcelAdapter>> getAllpackegClasss() {
        return mAllpackegClasss;
    }


    public void updatePackage(String name, String packegID) {
        ParcelDatabase.databaseWriteExecutor.execute(() -> {
            mpackegClassDao.updatePackage(name, packegID);//

        });
    }

    public void updateStatus(String sta, String paID) {
        ParcelDatabase.databaseWriteExecutor.execute(() -> {
            mpackegClassDao.updateStatus(sta, paID);//

        });
    }

    public void insert(ParcelAdapter packegClass) {
        ParcelDatabase.databaseWriteExecutor.execute(() -> {
            mpackegClassDao.insert(packegClass);//
            Log.e("repo ", "repo");
        });

    }

    public void deletePackage(String packegId) {
        ParcelDatabase.databaseWriteExecutor.execute(() -> {
            mpackegClassDao.deletePackage(packegId);//
            Log.e("repo ", "delete");
        });

    }

}

