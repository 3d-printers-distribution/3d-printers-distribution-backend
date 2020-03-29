package org.codevscovid19.threedprintingservice.repositories;

import org.codevscovid19.threedprintingservice.model.Allocation;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface AllocationRepository extends IdentifiableRepository<Allocation> {

    List<Allocation> findAll(Pageable pageable);

    List<Allocation> findAllByStock_Id(UUID stockId, Pageable pageable);

    List<Allocation> findAllByDemand_Id(UUID demandId, Pageable pageable);

    List<Allocation> findAllByDemand_IdAndStock_Id(UUID demandId, UUID stockId, Pageable pageable);


}
