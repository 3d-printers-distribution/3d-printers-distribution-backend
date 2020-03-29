package org.codevscovid19.threedprintingservice.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.codevscovid19.threedprintingservice.model.*;
import org.codevscovid19.threedprintingservice.repositories.ConsumerRepository;
import org.codevscovid19.threedprintingservice.repositories.DistributorRepository;
import org.codevscovid19.threedprintingservice.repositories.ProducerRepository;
import org.codevscovid19.threedprintingservice.security.FirebaseTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/register")
public class UserController {
    private ProducerRepository producerRepository;
    private ConsumerRepository consumerRepository;
    private DistributorRepository distributorRepository;
    private FirebaseTokenProvider firebaseTokenProvider;

    @Autowired
    public UserController(ProducerRepository producerRepository, ConsumerRepository consumerRepository, DistributorRepository distributorRepository, FirebaseTokenProvider firebaseTokenProvider) {
        this.producerRepository = producerRepository;
        this.consumerRepository = consumerRepository;
        this.distributorRepository = distributorRepository;
        this.firebaseTokenProvider = firebaseTokenProvider;
    }

    @PostMapping("/producer")
    public ResponseEntity<Producer> registerProducer(@RequestBody ProducerRegistrationRequest request) {
        String firebaseId = this.firebaseTokenProvider.getSessionPrincipal().getUid();
        Producer producer = new Producer(firebaseId, request.name, request.contactInformation, request.location);
        return ResponseEntity.ok(this.producerRepository.save(producer));
    }

    @PostMapping("/consumer")
    public ResponseEntity<Consumer> registerConsumer(@RequestBody ConsumerRegistrationRequest request) {
        String firebaseId = this.firebaseTokenProvider.getSessionPrincipal().getUid();
        Consumer consumer = new Consumer(firebaseId, request.name, request.contactInformation, request.location, request.type);
        return ResponseEntity.ok(this.consumerRepository.save(consumer));
    }

    @PostMapping("/distributor")
    public ResponseEntity<Distributor> registerConsumer(@RequestBody DistributorRegistrationRequest request) {
        String firebaseId = this.firebaseTokenProvider.getSessionPrincipal().getUid();
        Distributor distributor = new Distributor(firebaseId, request.name, request.contactInformation);
        return ResponseEntity.ok(this.distributorRepository.save(distributor));
    }

    private static class ProducerRegistrationRequest {
        @JsonProperty
        String name;
        @JsonProperty
        ContactInformation contactInformation;
        @JsonProperty
        GeoLocation location;
    }

    private static class ConsumerRegistrationRequest {
        @JsonProperty
        String name;
        @JsonProperty
        ContactInformation contactInformation;
        @JsonProperty
        GeoLocation location;
        @JsonProperty
        ConsumerType type;
    }

    private static class DistributorRegistrationRequest {
        @JsonProperty
        String name;
        @JsonProperty
        ContactInformation contactInformation;
    }


}
