package com.antoinegourtay.mob_e16_android.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by antoinegourtay on 18/12/2017.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class CryptoValueResponse {

    private double USD;
    private double EUR;
    private double GBP;

    public CryptoValueResponse(){

    }

    public double getUSD() {
        return USD;
    }

    public double getEUR() {
        return EUR;
    }

    public double getGBP() {
        return GBP;
    }
}
