package org.codevscovid19.threedprintingservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class Product extends GeneratedIdentifiable {
    @NotNull
    @Column
    @JsonProperty
    private String name;

    public Product() {
    }

    public Product(String name) {
        this.name = name;
    }
}
