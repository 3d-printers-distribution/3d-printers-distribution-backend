package org.codevscovid19.threedprintingservice.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Point;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.LinkedList;
import java.util.List;

@Entity
@JsonInclude
public class Producer extends User {
    @OneToMany(cascade = javax.persistence.CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "producer")
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
