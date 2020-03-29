package org.codevscovid19.threedprintingservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class GeoLocation {
    @Column
    @NotNull
    @JsonProperty
    private Float latitude;

    @Column
    @NotNull
    @JsonProperty
    private Float longitude;

    public GeoLocation(Float latitude, Float longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public GeoLocation() {
    }
}
