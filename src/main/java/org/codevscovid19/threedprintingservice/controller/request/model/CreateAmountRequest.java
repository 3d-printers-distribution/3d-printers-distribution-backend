package org.codevscovid19.threedprintingservice.controller.request.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class CreateAmountRequest {
    @JsonProperty
    private UUID productId;
    @JsonProperty
    private Integer amount;

    public UUID getProductId() {
        return productId;
    }

    public Integer getAmount() {
        return amount;
    }
}
