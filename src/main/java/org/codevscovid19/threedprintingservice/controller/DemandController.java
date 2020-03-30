package org.codevscovid19.threedprintingservice.controller;

import org.codevscovid19.threedprintingservice.controller.request.model.CreateAmountRequest;
import org.codevscovid19.threedprintingservice.exception.ConsumerNotFoundException;
import org.codevscovid19.threedprintingservice.exception.DemandNotFoundException;
import org.codevscovid19.threedprintingservice.exception.ProductNotFoundException;
import org.codevscovid19.threedprintingservice.model.Consumer;
import org.codevscovid19.threedprintingservice.model.Demand;
import org.codevscovid19.threedprintingservice.model.Product;
import org.codevscovid19.threedprintingservice.repositories.ConsumerRepository;
import org.codevscovid19.threedprintingservice.repositories.DemandRepository;
import org.codevscovid19.threedprintingservice.repositories.ProductRepository;
import org.codevscovid19.threedprintingservice.security.FirebaseTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/v1/demand")
public class DemandController {

    private DemandRepository demandRepository;
    private ProductRepository productRepository;
    private ConsumerRepository consumerRepository;
    private FirebaseTokenProvider firebaseTokenProvider;

    @Autowired
    public DemandController(DemandRepository demandRepository, ProductRepository productRepository, ConsumerRepository consumerRepository, FirebaseTokenProvider firebaseTokenProvider) {
        this.demandRepository = demandRepository;
        this.productRepository = productRepository;
        this.consumerRepository = consumerRepository;
        this.firebaseTokenProvider = firebaseTokenProvider;
    }

    @PostMapping
    public void createDemand(@RequestBody CreateAmountRequest createAmountRequest) throws ProductNotFoundException, ConsumerNotFoundException {
        Product product = this.productRepository.findById(createAmountRequest.getProductId()).orElseThrow(ProductNotFoundException::new);
        Consumer consumer = this.consumerRepository.findByFirebaseId(this.firebaseTokenProvider.getSessionPrincipal().getUid()).orElseThrow(ConsumerNotFoundException::new);
        Demand demand = new Demand(product, createAmountRequest.getAmount(), consumer);
        this.demandRepository.save(demand);
    }

    @DeleteMapping("/{demandId}")
    public void deleteDemand(@PathVariable UUID demandId) throws DemandNotFoundException {
        Demand demand = this.demandRepository.findById(demandId).orElseThrow(DemandNotFoundException::new);
        this.demandRepository.delete(demand);
    }
}
