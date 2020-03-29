package org.codevscovid19.threedprintingservice.repositories;

import org.codevscovid19.threedprintingservice.model.Consumer;
import org.codevscovid19.threedprintingservice.model.Demand;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DemandRepository extends IdentifiableRepository<Demand> {
    List<Demand> findByConsumer(Consumer consumer, Pageable pageable);
}
