package org.codevscovid19.threedprintingservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Stock extends Amount {

    @ManyToOne(cascade = CascadeType.MERGE)
    @JsonBackReference
    private Producer producer;

    public Stock() {
    }

    public Stock(Product product, Integer amount, Producer producer) {
        super(product, amount);
        this.producer = producer;
    }
}
