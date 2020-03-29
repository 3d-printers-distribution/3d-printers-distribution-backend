package org.codevscovid19.threedprintingservice.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.codevscovid19.threedprintingservice.exception.OverprovisioningException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Allocation extends GeneratedIdentifiable {
    @ManyToOne(cascade = {javax.persistence.CascadeType.MERGE, javax.persistence.CascadeType.REFRESH})
    @NotNull
    @JsonManagedReference
    private Stock stock;

    @ManyToOne(cascade = {javax.persistence.CascadeType.MERGE, javax.persistence.CascadeType.REFRESH})
    @NotNull
    @JsonManagedReference
    private Demand demand;

    @Column
    @NotNull
    @JsonProperty
    private Integer amount;

    @Column
    @NotNull
    @JsonProperty
    private Boolean fulfilled;

    public Allocation() {
    }

    public Allocation(Stock stock, Demand demand, Integer amount) throws OverprovisioningException {
        if (stock.getAmountRemaining() < amount || demand.getAmountRemaining() < amount) {
            throw new OverprovisioningException();
        }
        this.stock = stock;
        this.demand = demand;
        this.amount = amount;
        this.fulfilled = false;
    }

    public Stock getStock() {
        return stock;
    }

    public Demand getDemand() {
        return demand;
    }

    public Integer getAmount() {
        return amount;
    }

    public Boolean getFulfilled() {
        return fulfilled;
    }

    public void setFulfilled(Boolean fulfilled) {
        this.fulfilled = fulfilled;
    }
}
