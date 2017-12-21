package com.antoinegourtay.mob_e16_android.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.antoinegourtay.mob_e16_android.CryptoPlaceApplication;
import com.antoinegourtay.mob_e16_android.R;
import com.antoinegourtay.mob_e16_android.activities.MainActivity;
import com.antoinegourtay.mob_e16_android.response.CryptoValueResponse;
import com.neopixl.spitfire.listener.RequestListener;
import com.neopixl.spitfire.request.BaseRequest;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by antoinegourtay on 20/12/2017.
 */

public class CoursFragment extends Fragment {

    /**
     * View elements
     */
    @BindView(R.id.textViewBTCBalance)
    TextView textViewBTCBalance;

    @BindView(R.id.textViewEurBalance)
    TextView textViewEURBalance;

    @BindView(R.id.textViewUSDBalance)
    TextView textViewUSDBalance;

    @BindView(R.id.ctaBuyBitcoinsWallet)
    Button ctaBuyBitcoins;

    String baseUrl = "https://blockchain.info/q/addressbalance/";
    String endUrl = "?confirmations=6";

    SharedPreferences preferences;

    private double satoshiMutliplicator = 0.00000001;
    private double valueOfOne;

    public static CoursFragment newInstance() {
        CoursFragment fragment = new CoursFragment();
        return fragment;
    }

    public CoursFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_cours, container, false);

        ButterKnife.bind(this, rootView);

        preferences =  getActivity().getSharedPreferences("my_preferences", MODE_PRIVATE);

        String apiPublicKey = preferences.getString("public_key", null);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, baseUrl + apiPublicKey + endUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(final String response) {
                        int balance = Integer.parseInt(response);
                        final double realBalance = balance * satoshiMutliplicator;
                        textViewBTCBalance.setText(String.valueOf(realBalance));

                        getCryptoValueOne("btc", "eur", new ConvertorFragment.APIConvertCallback() {
                            @Override
                            public void success(double value) {
                                Double result = valueOfOne * realBalance;
                                textViewEURBalance.setText(String.valueOf(result));

                                getCryptoValueOne("btc", "usd", new ConvertorFragment.APIConvertCallback() {
                                    @Override
                                    public void success(double value) {
                                        Double result = valueOfOne * realBalance;
                                        textViewUSDBalance.setText(String.valueOf(result));
                                    }

                                    @Override
                                    public void fail() {

                                    }
                                });
                            }

                            @Override
                            public void fail() {

                            }
                        });

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), error.toString(), Toast.LENGTH_LONG).show();

                    }
                });




        CryptoPlaceApplication cryptoPlaceApplication =
                (CryptoPlaceApplication) getActivity().getApplication();
        cryptoPlaceApplication.getRequestQueue().add(stringRequest);
        return rootView;
    }

    public void getCryptoValueOne(String baseCurrency, String targetCurrency, final ConvertorFragment.APIConvertCallback callback){

        BaseRequest<CryptoValueResponse> request =
                new BaseRequest.Builder<>(Request.Method.GET
                        , "https://api.cryptonator.com/api/ticker/" + baseCurrency.toLowerCase() + "-" + targetCurrency.toLowerCase()
                        , CryptoValueResponse.class)
                        .listener(new RequestListener<CryptoValueResponse>() {
                            @Override
                            public void onSuccess(Request request, NetworkResponse response, CryptoValueResponse result) {
                                valueOfOne = Double.parseDouble(result.getTicker().getPrice());
                                callback.success(valueOfOne);
                            }

                            @Override
                            public void onFailure(Request request, NetworkResponse response, VolleyError error) {
                                callback.fail();
                            }
                        })
                        .build();


        CryptoPlaceApplication cryptoPlaceApplication =
                (CryptoPlaceApplication) getActivity().getApplication();
        cryptoPlaceApplication.getRequestQueue().add(request);

    }

    @OnClick(R.id.ctaBuyBitcoinsWallet)
    void clickToBuy() {
        Uri uri = Uri.parse("https://www.coinbase.com/buy");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
}
