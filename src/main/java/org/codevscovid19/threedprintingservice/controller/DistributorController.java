package org.codevscovid19.threedprintingservice.controller;

import org.codevscovid19.threedprintingservice.exception.DistributorNotFoundException;
import org.codevscovid19.threedprintingservice.exception.PartialArgumentsException;
import org.codevscovid19.threedprintingservice.model.Allocation;
import org.codevscovid19.threedprintingservice.model.Distributor;
import org.codevscovid19.threedprintingservice.repositories.AllocationRepository;
import org.codevscovid19.threedprintingservice.repositories.DistributorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/v1/distributor")
public class DistributorController {

    private AllocationRepository allocationRepository;
    private DistributorRepository distributorRepository;

    @Autowired
    public DistributorController(AllocationRepository allocationRepository, DistributorRepository distributorRepository) {
        this.allocationRepository = allocationRepository;
        this.distributorRepository = distributorRepository;
    }

    @GetMapping("")
    public ResponseEntity<Iterable<Distributor>> getDistributors(@PageableDefault(20) @SortDefault("name") Pageable pageable) throws PartialArgumentsException {
        return ResponseEntity.ok(this.distributorRepository.findAll());
    }

    @GetMapping("/{distributorId}/allocation")
    public ResponseEntity<List<Allocation>> getAllocationsOfDistributor(@PathVariable UUID distributorId,
                                                                        @PageableDefault(20) @SortDefault(value = "creationTime", direction = Sort.Direction.ASC) Pageable pageable) throws DistributorNotFoundException {
        Distributor distributor = this.distributorRepository.findById(distributorId).orElseThrow(DistributorNotFoundException::new);
        return ResponseEntity.ok(this.allocationRepository.findByDistributor(distributor, pageable));
    }
}
