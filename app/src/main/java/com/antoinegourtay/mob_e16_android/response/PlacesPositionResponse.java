package com.antoinegourtay.mob_e16_android.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by antoinegourtay on 19/12/2017.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class PlacesPositionResponse {

    private String latitude;
    private String longitude;

    public PlacesPositionResponse() {
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }
}
