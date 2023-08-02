package com.example.countriesapplication;

import android.app.Application;

import androidx.room.Room;

import com.example.countriesapplication.room.database.AppDatabase;


public class CountriesApp extends Application {
    static AppDatabase db;

    @Override
    public void onCreate() {
        super.onCreate();
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "countries_database").allowMainThreadQueries().build();
    }

    public static AppDatabase getDatabase() {
        return db;
    }
}
