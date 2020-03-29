package org.codevscovid19.threedprintingservice.controller;

import org.codevscovid19.threedprintingservice.exception.PartialArgumentsException;
import org.codevscovid19.threedprintingservice.exception.ProducerNotFoundException;
import org.codevscovid19.threedprintingservice.model.Producer;
import org.codevscovid19.threedprintingservice.model.Stock;
import org.codevscovid19.threedprintingservice.repositories.ProducerRepository;
import org.codevscovid19.threedprintingservice.repositories.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping("/v1/producer")
public class ProducerController {

    private StockRepository stockRepository;
    private ProducerRepository producerRepository;

    @Autowired
    public ProducerController(StockRepository stockRepository, ProducerRepository producerRepository) {
        this.stockRepository = stockRepository;
        this.producerRepository = producerRepository;
    }

    @GetMapping("")
    public ResponseEntity<Iterable<Producer>> getProducers(@RequestParam("latitude") Optional<Float> latitude,
                                                           @RequestParam("longitude") Optional<Float> longitude,
                                                           @RequestParam("distance") Optional<Float> distance,
                                                           @PageableDefault(20) @SortDefault("name") Pageable pageable) throws PartialArgumentsException {
        if (latitude.isEmpty() && longitude.isEmpty() && distance.isEmpty()) {
            return ResponseEntity.ok(this.producerRepository.findAll());
        }
        if (latitude.isEmpty() || longitude.isEmpty() || distance.isEmpty()) {
            throw new PartialArgumentsException();
        }
        return ResponseEntity.ok(this.producerRepository.findByLocationAndDistance(latitude.get(), longitude.get(), distance.get(), pageable));
    }

    @GetMapping("/{producerId}/stocks")
    public ResponseEntity<List<Stock>> getStocksOfProducer(@PathVariable UUID producerId,
                                                           @PageableDefault(20) @SortDefault(value = "creationTime", direction = Sort.Direction.ASC) Pageable pageable) throws ProducerNotFoundException {
        Producer producer = this.producerRepository.findById(producerId).orElseThrow(ProducerNotFoundException::new);
        return ResponseEntity.ok(this.stockRepository.findByProducer(producer, pageable));
    }
}
