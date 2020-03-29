package org.codevscovid19.threedprintingservice.controller;

import org.codevscovid19.threedprintingservice.controller.request.model.CreateAmountRequest;
import org.codevscovid19.threedprintingservice.exception.ProducerNotFoundException;
import org.codevscovid19.threedprintingservice.exception.ProductNotFoundException;
import org.codevscovid19.threedprintingservice.exception.StockNotFoundException;
import org.codevscovid19.threedprintingservice.model.Producer;
import org.codevscovid19.threedprintingservice.model.Product;
import org.codevscovid19.threedprintingservice.model.Stock;
import org.codevscovid19.threedprintingservice.repositories.ProducerRepository;
import org.codevscovid19.threedprintingservice.repositories.ProductRepository;
import org.codevscovid19.threedprintingservice.repositories.StockRepository;
import org.codevscovid19.threedprintingservice.security.FirebaseTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/v1/stock")
public class StockController {

    private StockRepository stockRepository;
    private ProductRepository productRepository;
    private ProducerRepository producerRepository;
    private FirebaseTokenProvider firebaseTokenProvider;

    @Autowired
    public StockController(StockRepository stockRepository, ProductRepository productRepository, ProducerRepository producerRepository, FirebaseTokenProvider firebaseTokenProvider) {
        this.stockRepository = stockRepository;
        this.productRepository = productRepository;
        this.producerRepository = producerRepository;
        this.firebaseTokenProvider = firebaseTokenProvider;
    }

    @PostMapping
    public void createStock(@RequestBody CreateAmountRequest createAmountRequest) throws ProductNotFoundException, ProducerNotFoundException {
        Product product = this.productRepository.findById(createAmountRequest.getProductId()).orElseThrow(ProductNotFoundException::new);
        Producer producer = this.producerRepository.findByFirebaseId(this.firebaseTokenProvider.getSessionPrincipal().getUid()).orElseThrow(ProducerNotFoundException::new);
        Stock stock = new Stock(product, createAmountRequest.getAmount(), producer);
        this.stockRepository.save(stock);
    }

    @DeleteMapping("/{stockId}")
    public void deleteStock(@PathVariable UUID stockId) throws StockNotFoundException {
        Stock stock = this.stockRepository.findById(stockId).orElseThrow(StockNotFoundException::new);
        this.stockRepository.delete(stock);
    }


}
