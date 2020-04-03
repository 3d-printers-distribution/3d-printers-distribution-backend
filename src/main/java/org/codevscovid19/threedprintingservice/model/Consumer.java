package org.codevscovid19.threedprintingservice.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.locationtech.jts.geom.Point;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(indexes = @Index(columnList = "firebaseId"))
public class Consumer extends User {
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "consumer")
    @JsonManagedReference
    private List<Demand> demand;

    @Enumerated(EnumType.STRING)
    @JsonProperty
    private ConsumerType type;

    @Column
    @JsonProperty
    private Point location;

    public Consumer() { // Hibernate constructor
    }

    public Consumer(String id, String name, ContactInformation contactInformation, Point location, ConsumerType type) {
        super(id, name, contactInformation);
        this.location = location;
        this.type = type;
        this.demand = new LinkedList<>();
    }

}
