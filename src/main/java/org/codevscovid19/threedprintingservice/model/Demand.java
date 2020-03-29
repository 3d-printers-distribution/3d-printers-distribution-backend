package org.codevscovid19.threedprintingservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Demand extends Amount {

    @ManyToOne(cascade = javax.persistence.CascadeType.MERGE)
    @JsonBackReference
    private Consumer consumer;

    public Demand() {
    }

    public Demand(Product product, Integer amount, Consumer consumer) {
        super(product, amount);
        this.consumer = consumer;
    }
}
