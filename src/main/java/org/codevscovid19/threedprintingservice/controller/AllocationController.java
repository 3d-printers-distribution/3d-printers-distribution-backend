package org.codevscovid19.threedprintingservice.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.codevscovid19.threedprintingservice.exception.AllocationNotFoundException;
import org.codevscovid19.threedprintingservice.exception.DemandNotFoundException;
import org.codevscovid19.threedprintingservice.exception.OverprovisioningException;
import org.codevscovid19.threedprintingservice.exception.StockNotFoundException;
import org.codevscovid19.threedprintingservice.model.Allocation;
import org.codevscovid19.threedprintingservice.model.Demand;
import org.codevscovid19.threedprintingservice.model.Stock;
import org.codevscovid19.threedprintingservice.repositories.AllocationRepository;
import org.codevscovid19.threedprintingservice.repositories.DemandRepository;
import org.codevscovid19.threedprintingservice.repositories.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/v1/allocation")
public class AllocationController {

    private AllocationRepository allocationRepository;
    private DemandRepository demandRepository;
    private StockRepository stockRepository;

    @Autowired
    public AllocationController(AllocationRepository allocationRepository, DemandRepository demandRepository, StockRepository stockRepository) {
        this.allocationRepository = allocationRepository;
        this.demandRepository = demandRepository;
        this.stockRepository = stockRepository;
    }

    @GetMapping
    public Iterable<Allocation> getAllocations(@RequestParam(required = false) Optional<UUID> stockId, @RequestParam(required = false) Optional<UUID> demandId, Pageable pageable) {
        if (stockId.isPresent() && demandId.isPresent()) {
            return this.allocationRepository.findAllByDemand_IdAndStock_Id(demandId.get(), stockId.get(), pageable);
        } else if (stockId.isPresent()) {
            return this.allocationRepository.findAllByStock_Id(stockId.get(), pageable);
        } else if (demandId.isPresent()) {
            return this.allocationRepository.findAllByDemand_Id(demandId.get(), pageable);
        }
        return this.allocationRepository.findAll(pageable);
    }


    @PostMapping
    @Transactional
    public ResponseEntity<Allocation> createAllocation(@RequestBody CreateAllocationRequest createAllocationRequest) throws DemandNotFoundException, StockNotFoundException, OverprovisioningException {
        Demand demand = this.demandRepository.findById(createAllocationRequest.demandId).orElseThrow(DemandNotFoundException::new);
        Stock stock = this.stockRepository.findById(createAllocationRequest.stockId).orElseThrow(StockNotFoundException::new);
        Allocation allocation = new Allocation(stock, demand, createAllocationRequest.amount);
        return ResponseEntity.ok(this.allocationRepository.save(allocation));
    }

    @PatchMapping("/{allocationId}")
    @Transactional
    public ResponseEntity<Allocation> markAllocationFulfilled(@PathVariable UUID allocationId) throws AllocationNotFoundException {
        Allocation allocation = this.allocationRepository.findById(allocationId).orElseThrow(AllocationNotFoundException::new);
        allocation.setFulfilled(true);
        return ResponseEntity.ok(this.allocationRepository.save(allocation));
    }

    @DeleteMapping("/{allocationId}")
    public void deleteAllocation(@PathVariable UUID allocationId) {
        this.allocationRepository.deleteById(allocationId);
    }


    private static class CreateAllocationRequest {
        @JsonProperty
        UUID stockId;
        @JsonProperty
        UUID demandId;
        @JsonProperty
        Integer amount;
    }
}
