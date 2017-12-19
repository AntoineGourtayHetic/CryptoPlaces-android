package com.antoinegourtay.mob_e16_android.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.antoinegourtay.mob_e16_android.CryptoPlaceApplication;
import com.antoinegourtay.mob_e16_android.R;
import com.antoinegourtay.mob_e16_android.response.CryptoValueResponse;
import com.neopixl.spitfire.listener.RequestListener;
import com.neopixl.spitfire.request.BaseRequest;

public class ConvertorFragment extends Fragment {


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
        getCryptoValue();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_convertor, container, false);
    }

    // Function to get currency
    public void getCryptoValue(){
        BaseRequest<CryptoValueResponse> request =
                new BaseRequest.Builder<>(Request.Method.GET
                        , "https://api.cryptonator.com/api/ticker/btc-eur"
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
    }
}
