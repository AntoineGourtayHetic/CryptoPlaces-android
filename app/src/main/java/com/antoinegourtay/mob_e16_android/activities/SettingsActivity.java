package com.antoinegourtay.mob_e16_android.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.antoinegourtay.mob_e16_android.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingsActivity extends AppCompatActivity {

    @BindView(R.id.editTextPublicKeySettings)
    EditText editTextPublickeySettings;

    @BindView(R.id.buttonValidatePublickeyUpdate)
    Button buttonValidatePublickeyUpdate;

    SharedPreferences preferences;

    private final String PREF_FILE_NAME = "my_preferences";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        ButterKnife.bind(this);

        preferences = getSharedPreferences(PREF_FILE_NAME, MODE_PRIVATE);

        editTextPublickeySettings.setText(preferences.getString("public_key", null));

    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @OnClick(R.id.buttonValidatePublickeyUpdate)
    void updatePublicKey() {
        getSharedPreferences(PREF_FILE_NAME, MODE_PRIVATE)
                .edit()
                .putString("public_key", editTextPublickeySettings.getText().toString())
                .apply();

        Toast.makeText(SettingsActivity.this, "New key is set : " + editTextPublickeySettings.getText().toString(), Toast.LENGTH_LONG).show();
    }
}
