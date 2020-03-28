package org.codevscovid19.threedprintingservice.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ContactInformation {
    @Column
    private String telephone;

    @Column
    private String eMail;
}
