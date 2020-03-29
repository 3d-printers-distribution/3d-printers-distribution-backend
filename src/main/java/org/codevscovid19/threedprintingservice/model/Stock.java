package org.codevscovid19.threedprintingservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Entity
public class Stock extends Amount {

    @ManyToOne(cascade = CascadeType.MERGE)
    @JsonBackReference
    private Producer producer;

    @Formula("(SELECT (s.amount - SUM(a.amount)) FROM allocation a JOIN stock s ON s.id = a.stock_id WHERE s.id = id GROUP BY s.id)")
    private Integer amountRemaining;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "stock")
    @NotNull
    private List<Allocation> allocationList;

    public Stock() {
    }

    public Stock(Product product, Integer amount, Producer producer) {
        super(product, amount);
        this.producer = producer;
        this.allocationList = new LinkedList<>();
    }

    public Producer getProducer() {
        return producer;
    }

    @JsonProperty
    public Integer getAmountRemaining() {
        return Optional.ofNullable(amountRemaining).orElseGet(this::getAmount);
    }
}
