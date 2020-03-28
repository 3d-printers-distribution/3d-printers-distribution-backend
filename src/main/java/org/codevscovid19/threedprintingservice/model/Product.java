package org.codevscovid19.threedprintingservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class Product extends Identifiable {
    @NotNull
    @Column
    private String name;
}
