package org.codevscovid19.threedprintingservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ContactInformation {
    @Column
    @JsonProperty
    private String telephone;

    @Column
    @JsonProperty
    private String eMail;
}
