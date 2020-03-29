package org.codevscovid19.threedprintingservice.repositories;

import org.codevscovid19.threedprintingservice.model.Producer;
import org.codevscovid19.threedprintingservice.model.Stock;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StockRepository extends IdentifiableRepository<Stock> {
    List<Stock> findByProducer(Producer producer, Pageable pageable);
}
