package org.codevscovid19.threedprintingservice.repositories;

import org.codevscovid19.threedprintingservice.model.Product;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductRepository extends IdentifiableRepository<Product> {
    List<Product> findAll(Pageable pageable);
}
