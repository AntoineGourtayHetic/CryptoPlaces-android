package com.antoinegourtay.mob_e16_android.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.antoinegourtay.mob_e16_android.CryptoPlaceApplication;
import com.antoinegourtay.mob_e16_android.R;
import com.antoinegourtay.mob_e16_android.response.CryptoValueResponse;
import com.neopixl.spitfire.listener.RequestListener;
import com.neopixl.spitfire.request.BaseRequest;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.textview_crypto)
    TextView cryptoValueTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        getCryptoValue();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void getCryptoValue(){
        BaseRequest<CryptoValueResponse> request =
                new BaseRequest.Builder<>(Request.Method.GET
                        , "https://api.cryptonator.com/api/ticker/btc-eur"
                        , CryptoValueResponse.class)
                        .listener(new RequestListener<CryptoValueResponse>() {
                            @Override
                            public void onSuccess(Request request, NetworkResponse response, CryptoValueResponse result) {
                                cryptoValueTextView.setText(result.getTicker().getPrice());
                            }

                            @Override
                            public void onFailure(Request request, NetworkResponse response, VolleyError error) {
                                Toast.makeText(MainActivity.this, "FAIL", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .build();


        CryptoPlaceApplication cryptoPlaceApplication =
                (CryptoPlaceApplication) getApplication();
        cryptoPlaceApplication.getRequestQueue().add(request);
    }
}
