package org.codevscovid19.threedprintingservice.controller;

import org.codevscovid19.threedprintingservice.exception.ConsumerNotFoundException;
import org.codevscovid19.threedprintingservice.exception.PartialArgumentsException;
import org.codevscovid19.threedprintingservice.model.Consumer;
import org.codevscovid19.threedprintingservice.model.Demand;
import org.codevscovid19.threedprintingservice.repositories.ConsumerRepository;
import org.codevscovid19.threedprintingservice.repositories.DemandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping("/v1/consumer")
public class ConsumerController {

    private DemandRepository demandRepository;
    private ConsumerRepository consumerRepository;

    @Autowired
    public ConsumerController(DemandRepository demandRepository, ConsumerRepository consumerRepository) {
        this.demandRepository = demandRepository;
        this.consumerRepository = consumerRepository;
    }

    @GetMapping("")
    public ResponseEntity<Iterable<Consumer>> getConsumers(@RequestParam("latitude") Optional<Float> latitude,
                                                           @RequestParam("longitude") Optional<Float> longitude,
                                                           @RequestParam("distance") Optional<Float> distance,
                                                           @PageableDefault(20) @SortDefault("name") Pageable pageable) throws PartialArgumentsException {
        if (latitude.isEmpty() && longitude.isEmpty() && distance.isEmpty()) {
            return ResponseEntity.ok(this.consumerRepository.findAll());
        }
        if (latitude.isEmpty() || longitude.isEmpty() || distance.isEmpty()) {
            throw new PartialArgumentsException();
        }
        return ResponseEntity.ok(this.consumerRepository.findByLocationAndDistance(latitude.get(), longitude.get(), distance.get(), pageable));
    }

    @GetMapping("/{consumerId}/demands")
    public ResponseEntity<List<Demand>> getDemandsOfConsumer(@PathVariable UUID consumerId) throws ConsumerNotFoundException {
        Consumer consumer = this.consumerRepository.findById(consumerId).orElseThrow(ConsumerNotFoundException::new);
        return ResponseEntity.ok(this.demandRepository.findByConsumer(consumer));
    }
}
