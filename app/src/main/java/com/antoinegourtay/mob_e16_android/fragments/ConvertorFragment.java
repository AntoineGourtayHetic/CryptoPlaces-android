package com.antoinegourtay.mob_e16_android.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.antoinegourtay.mob_e16_android.CryptoPlaceApplication;
import com.antoinegourtay.mob_e16_android.R;
import com.antoinegourtay.mob_e16_android.activities.MainActivity;
import com.antoinegourtay.mob_e16_android.response.CryptoValueResponse;
import com.neopixl.spitfire.listener.RequestListener;
import com.neopixl.spitfire.request.BaseRequest;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ConvertorFragment extends Fragment {

    MainActivity mainActivity;

    //Currencies lists
    private List<String> cryptoCurrencies;
    private List<String> basicCurrencies;

    @BindView(R.id.spinnerBase)
    Spinner spinnerBase;

    @BindView(R.id.spinnerTarget)
    Spinner spinnerTarget;

    //Helping varibles
    private boolean isOnBasicSpinner = true;

    public ConvertorFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ConvertorFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ConvertorFragment newInstance() {
        ConvertorFragment fragment = new ConvertorFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_convertor, container, false);
        ButterKnife.bind(this, view);

        mainActivity = (MainActivity) getActivity();
        mainActivity.getSupportActionBar().setTitle("Convertisseur");

        //Adding currencies to spinners on basic way
        addCryptoCurrenciesToSpinner();
        addBasicCurrenciesToSpinner();


        return view;
    }

    // Function to get currency
    public double getCryptoValueOne(String baseCurrency, String targetCurrency){
        BaseRequest<CryptoValueResponse> request =
                new BaseRequest.Builder<>(Request.Method.GET
                        , "https://api.cryptonator.com/api/ticker/" + baseCurrency.toLowerCase() + "-" + targetCurrency.toLowerCase()
                        , CryptoValueResponse.class)
                        .listener(new RequestListener<CryptoValueResponse>() {
                            @Override
                            public void onSuccess(Request request, NetworkResponse response, CryptoValueResponse result) {
                            }

                            @Override
                            public void onFailure(Request request, NetworkResponse response, VolleyError error) {
                                Toast.makeText(getActivity(), "FAIL", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .build();


        CryptoPlaceApplication cryptoPlaceApplication =
                (CryptoPlaceApplication) getActivity().getApplication();
        cryptoPlaceApplication.getRequestQueue().add(request);

        return 0;
    }

    /**
     * Events on clicks
     */
    @OnClick(R.id.imageButtonConvertor)
    void changeCurrenciesSide() {
        if (isOnBasicSpinner) {

            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                    android.R.layout.simple_spinner_item, basicCurrencies);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerBase.setAdapter(dataAdapter);

            ArrayAdapter<String> dataAdapterTwo = new ArrayAdapter<String>(getActivity(),
                    android.R.layout.simple_spinner_item, cryptoCurrencies);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerTarget.setAdapter(dataAdapterTwo);

            isOnBasicSpinner = false;

        } else {

            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                    android.R.layout.simple_spinner_item, cryptoCurrencies);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerBase.setAdapter(dataAdapter);

            ArrayAdapter<String> dataAdapterTwo = new ArrayAdapter<String>(getActivity(),
                    android.R.layout.simple_spinner_item, basicCurrencies);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerTarget.setAdapter(dataAdapterTwo);

            isOnBasicSpinner = true;

        }
    }

    /**
     * Other methods for adding currencies to spinners
     */


    private void addCryptoCurrenciesToSpinner() {
        cryptoCurrencies = new ArrayList<>();
        cryptoCurrencies.add("BTC");
        cryptoCurrencies.add("ETH");
        cryptoCurrencies.add("BCH");
        cryptoCurrencies.add("LTC");
        cryptoCurrencies.add("XRP");


        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, cryptoCurrencies);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBase.setAdapter(dataAdapter);

    }

    private void addBasicCurrenciesToSpinner() {
        basicCurrencies = new ArrayList<>();
        basicCurrencies.add("EUR");
        basicCurrencies.add("USD");
        basicCurrencies.add("GBP");
        basicCurrencies.add("JPY");
        basicCurrencies.add("XRP");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, basicCurrencies);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTarget.setAdapter(dataAdapter);
    }
}
