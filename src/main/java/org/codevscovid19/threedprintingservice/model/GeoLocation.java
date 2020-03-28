package org.codevscovid19.threedprintingservice.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class GeoLocation {
    @Column
    @NotNull
    private Float latitude;

    @Column
    @NotNull
    private Float longitude;
}
