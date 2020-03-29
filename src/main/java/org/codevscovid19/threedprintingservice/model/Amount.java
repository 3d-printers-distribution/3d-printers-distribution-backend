package org.codevscovid19.threedprintingservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

@MappedSuperclass
public abstract class Amount extends GeneratedIdentifiable {

    @Column
    @JsonProperty
    private Long creationTime;

    @ManyToOne(cascade = {javax.persistence.CascadeType.MERGE, javax.persistence.CascadeType.REFRESH})
    @JsonProperty
    private Product product;

    @Column
    @NotNull
    @JsonProperty
    private Integer amount;

    public Amount() {
        this.creationTime = System.currentTimeMillis();
    }

    public Amount(Product product, Integer amount) {
        this();
        this.product = product;
        this.amount = amount;
    }
}
