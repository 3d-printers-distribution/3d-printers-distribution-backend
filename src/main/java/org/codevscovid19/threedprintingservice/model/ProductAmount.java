package org.codevscovid19.threedprintingservice.model;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class ProductAmount extends Identifiable {

    @ManyToOne
    @Cascade(CascadeType.PERSIST)
    private Product product;

    @Column
    @NotNull
    private Integer amount;
}
