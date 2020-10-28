package com.example.myapplication.repositories;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.myapplication.entities.Parcel;
import com.example.myapplication.entities.ParcelAdapter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Database(entities = {ParcelAdapter.class}, version = 1, exportSchema = false)
public abstract class ParcelDatabase extends RoomDatabase {


    public abstract ParcelDao packegClassDao();

    private static volatile ParcelDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static ParcelDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ParcelDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ParcelDatabase.class, "ParcelAdapter_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);

            // If you want to keep data through app restarts,
            // comment out the following block
            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more packegClasss, just add them.
                ParcelDao dao = INSTANCE.packegClassDao();
                //dao.deleteAll();

            });
        }
    };
}