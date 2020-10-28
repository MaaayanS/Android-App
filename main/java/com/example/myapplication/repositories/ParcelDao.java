package com.example.myapplication.repositories;

import androidx.lifecycle.LiveData;
import androidx.room.*;

import java.util.List;

import com.example.myapplication.entities.Parcel;
import com.example.myapplication.entities.ParcelAdapter;

@Dao
public interface ParcelDao {

    // allowing the insert of the same packegClass multiple times by passing a
    // conflict resolution strategy
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insert(ParcelAdapter packegClass);


    @Query("DELETE FROM parcles WHERE PackageId= :packegID")
    void deletePackage(String packegID);


    @Query("Update parcles SET deliverName= :NA WHERE PackageId=:paID")
    void updatePackage(String NA, String paID);

    @Query("Update parcles SET status = :sta WHERE PackageId=:paID")
    void updateStatus(String sta, String paID);


    @Query("DELETE FROM parcles")
    void deleteAll();


    @Query("SELECT * from parcles ORDER BY PackageId ASC")
    LiveData<List<ParcelAdapter>> getAlphabetizedpackegClasss();

}