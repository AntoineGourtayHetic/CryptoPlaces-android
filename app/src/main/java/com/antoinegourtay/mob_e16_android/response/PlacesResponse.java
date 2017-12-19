package com.antoinegourtay.mob_e16_android.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by antoinegourtay on 19/12/2017.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class PlacesResponse {

    private List<SinglePlaceResponse> results;

    public PlacesResponse() {
    }

    public List<SinglePlaceResponse> getResults() {
        return results;
    }
}
