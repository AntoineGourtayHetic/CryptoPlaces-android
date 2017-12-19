package com.antoinegourtay.mob_e16_android.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by antoinegourtay on 18/12/2017.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class CryptoValueResponse {

    private CryptoObjectResponse ticker;

    public CryptoValueResponse(){

    }

    public CryptoObjectResponse getTicker() {
        return ticker;
    }
}
