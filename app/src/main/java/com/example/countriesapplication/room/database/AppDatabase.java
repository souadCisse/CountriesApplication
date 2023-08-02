package com.example.countriesapplication.room.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.countriesapplication.room.doa.CountryDoa;
import com.example.countriesapplication.room.entities.Country;



@Database(entities = {Country.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CountryDoa countryDao();
}
