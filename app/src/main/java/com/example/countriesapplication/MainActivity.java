package com.example.countriesapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.countriesapplication.adapter.CountriesAdapter;
import com.example.countriesapplication.room.database.AppDatabase;
import com.example.countriesapplication.room.doa.CountryDoa;
import com.example.countriesapplication.room.entities.Country;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button addButton;
    Button resetButton;
    EditText countryName;
    EditText countryPopulation;
    EditText countryCapital;

    RecyclerView countriesRecyclerView;

    AppDatabase db;
    CountryDoa countryDoa;

    List<Country> countries;

    CountriesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addButton = findViewById(R.id.buttonAdd);
        resetButton = findViewById(R.id.buttonReset);

        countryName = findViewById(R.id.countryName);
        countryPopulation = findViewById(R.id.countryPopulation);
        countryCapital = findViewById(R.id.countryCapital);

        countriesRecyclerView = findViewById(R.id.countriesRecycler);

        db = CountriesApp.getDatabase();

        countryDoa = db.countryDao();

        countries = countryDoa.getAll();

        adapter = new CountriesAdapter(countries);
        countriesRecyclerView.setAdapter(adapter);

        addButton.setOnClickListener(view -> {
            addCountry();
        });

        resetButton.setOnClickListener(view -> {
            countryName.getText().clear();
            countryPopulation.getText().clear();
            countryCapital.getText().clear();
        });
    }

    private void addCountry() {
        String name = countryName.getText().toString();
        String population = countryPopulation.getText().toString();
        String capital = countryCapital.getText().toString();

        if (name.isEmpty() || population.isEmpty() || capital.isEmpty()) {
            Toast.makeText(this, "Merci de remplir tous les champs", Toast.LENGTH_LONG).show();
            return;
        }

        Country country = new Country(name, Long.parseLong(population), capital);

        countryDoa.insert(country);
        countries.add(country);

        adapter.notifyDataSetChanged();


    }

}