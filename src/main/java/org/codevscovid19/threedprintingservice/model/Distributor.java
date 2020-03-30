package org.codevscovid19.threedprintingservice.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(indexes = @Index(columnList = "firebaseId"))
public class Distributor extends User {
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "distributor")
    @JsonManagedReference
    private List<Allocation> allocations;

    public Distributor() {
    }

    public Distributor(String id, @NotNull String name, ContactInformation contactInformation) {
        super(id, name, contactInformation);
        this.allocations = new LinkedList<>();
    }
}
