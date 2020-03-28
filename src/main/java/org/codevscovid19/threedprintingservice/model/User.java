package org.codevscovid19.threedprintingservice.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User extends Identifiable {
    @Column
    @NotNull
    private String name;

    @Embedded
    private ContactInformation contactInformation;
}
