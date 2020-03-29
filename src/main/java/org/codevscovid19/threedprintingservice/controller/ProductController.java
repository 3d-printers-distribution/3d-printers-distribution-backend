package org.codevscovid19.threedprintingservice.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.codevscovid19.threedprintingservice.exception.ProductNotFoundException;
import org.codevscovid19.threedprintingservice.model.Product;
import org.codevscovid19.threedprintingservice.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/v1/product")
public class ProductController {

    private ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public ResponseEntity<Iterable<Product>> getProducts(@PageableDefault(20) @SortDefault("name") Pageable pageable) {
        return ResponseEntity.ok(this.productRepository.findAll(pageable));
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody ProductCreationRequest request) {
        Product product = new Product(request.name);
        return ResponseEntity.ok(this.productRepository.save(product));
    }

    @DeleteMapping("/{productId}")
    public void deleteProduct(@PathVariable UUID productId) throws ProductNotFoundException {
        Product product = this.productRepository.findById(productId).orElseThrow(ProductNotFoundException::new);
        this.productRepository.delete(product);
    }

    private static class ProductCreationRequest {
        @JsonProperty
        String name;
    }
}
