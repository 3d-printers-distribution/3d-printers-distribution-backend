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
public class Demand extends Amount {

    @ManyToOne(cascade = javax.persistence.CascadeType.MERGE)
    @JsonBackReference
    private Consumer consumer;


    @Formula("(SELECT (d.amount - SUM(a.amount)) FROM Allocation a JOIN Demand d ON d.id = a.demand_id WHERE d.id = id GROUP BY d.id)")
    private Integer amountRemaining;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "demand")
    @NotNull
    private List<Allocation> allocationList;

    public Demand() {
    }

    public Demand(Product product, Integer amount, Consumer consumer) {
        super(product, amount);
        this.consumer = consumer;
        this.allocationList = new LinkedList<>();
    }

    public Consumer getConsumer() {
        return consumer;
    }

    @JsonProperty
    public Integer getAmountRemaining() {
        return Optional.ofNullable(amountRemaining).orElseGet(this::getAmount);
    }
}
