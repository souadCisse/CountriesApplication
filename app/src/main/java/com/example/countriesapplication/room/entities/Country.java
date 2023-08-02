package com.example.countriesapplication.room.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;



@Entity
public class Country {
    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "population")
    public Long population;

    @ColumnInfo(name = "capital")
    public String capital;

    public Country(String name, long population, String capital) {
        this.name = name;
        this.population = population;
        this.capital = capital;
    }
}
