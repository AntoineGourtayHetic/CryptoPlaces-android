package com.antoinegourtay.mob_e16_android.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by antoinegourtay on 19/12/2017.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class CryptoObjectResponse {

    private String base;
    private String target;
    private String price;

    public CryptoObjectResponse() {
    }

    public String getBase() {
        return base;
    }

    public String getTarget() {
        return target;
    }

    public String getPrice() {
        return price;
    }
}
