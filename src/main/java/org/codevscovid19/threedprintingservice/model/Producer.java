package org.codevscovid19.threedprintingservice.model;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Producer extends User {
    @OneToMany
    private List<ProductAmount> stock;

    @Embedded
    private GeoLocation location;
}
