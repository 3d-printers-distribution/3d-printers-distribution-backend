package org.codevscovid19.threedprintingservice.controller.request.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GeoLocation {
    @JsonProperty
    private float latitude;
    @JsonProperty
    private float longitude;

    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
    }
}
