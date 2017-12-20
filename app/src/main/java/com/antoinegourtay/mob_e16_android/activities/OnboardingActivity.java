package com.antoinegourtay.mob_e16_android.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.antoinegourtay.mob_e16_android.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OnboardingActivity extends AppCompatActivity {

    private List<String> currencies;

    private SharedPreferences preferences;

    @BindView(R.id.spinnerCurrency)
    Spinner spinner;
    @BindView(R.id.buttonStart)
    Button startButton;
    @BindView(R.id.editTextPublicKey)
    EditText publicKeyEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        ButterKnife.bind(this);

        addItemsOnSpinner();
    }

    @SuppressLint("CommitPrefEdits")
    @OnClick(R.id.buttonStart)
    void onClickButtonStart(){
        if (publicKeyEditText.getText().toString().trim().equalsIgnoreCase("")){
            publicKeyEditText.setError("Ce champ ne peut pas être vide");
        } else {
            Toast.makeText(OnboardingActivity.this, String.valueOf(spinner.getSelectedItem()), Toast.LENGTH_LONG).show();

            preferences = getSharedPreferences("my_preferences", MODE_PRIVATE);

            preferences.edit().putString("pref_currency", String.valueOf(spinner.getSelectedItem())).apply();

            preferences.edit().putBoolean("onboarding_complete", true).apply();

            preferences.edit().putString("public_key", publicKeyEditText.getText().toString()).apply();

            Intent intent = new Intent(OnboardingActivity.this, MainActivity.class);
            startActivity(intent);

            finish();
        }
    }

    @OnClick(R.id.buttonIgnore)
    void onClickButtonIgnore() {
        preferences = getSharedPreferences("my_preferences", MODE_PRIVATE);
        preferences.edit().putBoolean("onboarding_complete", true).apply();

        Intent intent = new Intent(OnboardingActivity.this, MainActivity.class);
        startActivity(intent);

        finish();
    }

    private void addItemsOnSpinner(){
        currencies = new ArrayList<>();
        currencies.add("BTC");
        currencies.add("ETH");
        currencies.add("BCH");
        currencies.add("LTC");
        currencies.add("XRP");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, currencies);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }
}
