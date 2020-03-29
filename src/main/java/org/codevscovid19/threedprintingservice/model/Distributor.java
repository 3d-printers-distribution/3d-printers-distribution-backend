package org.codevscovid19.threedprintingservice.model;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class Distributor extends User {
    public Distributor() {
    }

    public Distributor(String id, @NotNull String name, ContactInformation contactInformation) {
        super(id, name, contactInformation);
    }
}
