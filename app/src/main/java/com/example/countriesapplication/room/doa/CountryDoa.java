package com.example.countriesapplication.room.doa;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.countriesapplication.room.entities.Country;

import java.util.List;



@Dao
public interface CountryDoa {
    @Query("SELECT * FROM country")
    List<Country> getAll();

    @Insert
    void insert(Country country);

    @Delete
    void delete(Country country);

    @Update
    void update(Country country);
}
