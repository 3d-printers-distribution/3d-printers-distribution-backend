package org.codevscovid19.threedprintingservice.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatus;

public class RestError {

    @JsonProperty
    private int status;
    @JsonProperty
    private String message;

    public RestError(HttpStatus status, String message) {
        this.status = status.value();
        this.message = message;
    }
}
