package com.example.countriesapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.countriesapplication.CountriesApp;
import com.example.countriesapplication.R;
import com.example.countriesapplication.dialog.CallBackDialogConfirm;
import com.example.countriesapplication.dialog.DialogUpdate;
import com.example.countriesapplication.room.database.AppDatabase;
import com.example.countriesapplication.room.entities.Country;

import java.util.List;

public class CountriesAdapter extends RecyclerView.Adapter<CountriesAdapter.ViewHolder> implements CallBackDialogConfirm {

    private List<Country> countries;
    AppDatabase db;

    @Override
    public void onCallBackDialogConfirm(Country country) {
        db.countryDao().update(country);
        countries.clear();
        countries = db.countryDao().getAll();
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView country;
        private final TextView population;
        private final TextView capital;

        private final ImageView deleteButton;
        private final ImageView updateButton;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            country = (TextView) view.findViewById(R.id.tv_country);
            population = (TextView) view.findViewById(R.id.tv_population);
            capital = (TextView) view.findViewById(R.id.tv_capital);

            deleteButton = (ImageView) view.findViewById(R.id.delete);
            updateButton = (ImageView) view.findViewById(R.id.update);
        }

        public TextView getCountry() {
            return country;
        }

        public TextView getPopulation() {
            return population;
        }

        public TextView getCapital() {
            return capital;
        }

        public ImageView getDeleteButton() {
            return deleteButton;
        }

        public ImageView getUpdateButton() {
            return updateButton;
        }
    }

    public CountriesAdapter(List<Country> dataSet) {
        countries = dataSet;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.country_item, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        db = CountriesApp.getDatabase();

        viewHolder.getCountry().setText(countries.get(position).name);
        viewHolder.getPopulation().setText(countries.get(position).population.toString() + " millions");
        viewHolder.getCapital().setText(countries.get(position).capital);

        viewHolder.getDeleteButton().setOnClickListener(view -> {
            db.countryDao().delete(countries.get(position));
            countries.remove(position);
            notifyDataSetChanged();
        });

        viewHolder.getUpdateButton().setOnClickListener(view -> {
            new DialogUpdate(view.getContext(), this, countries.get(position)).show();
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return countries.size();
    }
}