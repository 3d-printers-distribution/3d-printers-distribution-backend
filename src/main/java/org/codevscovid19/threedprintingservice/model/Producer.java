package org.codevscovid19.threedprintingservice.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Point;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(indexes = @Index(columnList = "firebaseId"))
public class Producer extends User {
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "producer")
    @JsonManagedReference
    private List<Stock> stock;

    @Column
    @JsonProperty
    private Geometry location;

    public Producer() { // Hibernate constructor
    }

    public Producer(String userId, String name, ContactInformation contactInformation, Point location) {
        super(userId, name, contactInformation);
        this.location = location;
        this.stock = new LinkedList<>();
    }
}
