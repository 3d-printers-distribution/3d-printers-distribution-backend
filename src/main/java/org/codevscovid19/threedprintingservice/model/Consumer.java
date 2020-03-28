package org.codevscovid19.threedprintingservice.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Consumer extends User {
    @OneToMany
    private List<ProductAmount> demand;

    @Enumerated(EnumType.STRING)
    private ConsumerType type;

    @Embedded
    private GeoLocation location;
}
