package com.example.countriesapplication.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.countriesapplication.R;
import com.example.countriesapplication.room.entities.Country;

public class DialogUpdate extends Dialog {

    private final Country country;
    private final CallBackDialogConfirm callBackDialogConfirm;

    TextView countryName;
    EditText countryPopulation;
    EditText countryCapital;

    public DialogUpdate(Context context, CallBackDialogConfirm callBackDialogConfirm, Country country) {
        super(context);
        this.country = country;
        this.callBackDialogConfirm = callBackDialogConfirm;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.dialog_update);

        countryName = findViewById(R.id.countryName);
        countryPopulation = findViewById(R.id.countryPopulation);
        countryCapital = findViewById(R.id.countryCapital);

        this.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        countryName.setText(country.name);
        countryPopulation.setText(String.valueOf(country.population));
        countryCapital.setText(country.capital);

        ((Button) findViewById(R.id.buttonUpdate)).setOnClickListener(view -> {
            country.population = Long.parseLong(countryPopulation.getText().toString());
            country.capital = countryCapital.getText().toString();

            callBackDialogConfirm.onCallBackDialogConfirm(country);
            dismiss();
        });


    }
}
