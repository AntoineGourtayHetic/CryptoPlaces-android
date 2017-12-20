package com.antoinegourtay.mob_e16_android.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by antoinegourtay on 19/12/2017.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class SinglePlaceResponse {

    private String name;
    private List<String> currences_accepted;
    private PlacesPositionResponse position;
    private String adresse;
    private String category;
    private String details;


    public SinglePlaceResponse() {
    }

    public String getName() {
        return name;
    }

    public List<String> getCurrences_accepted() {
        return currences_accepted;
    }

    public PlacesPositionResponse getPosition() {
        return position;
    }
}
