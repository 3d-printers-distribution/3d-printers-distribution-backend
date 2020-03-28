package org.codevscovid19.threedprintingservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

@MappedSuperclass
public abstract class Identifiable {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )

    @Column(columnDefinition = "uuid", updatable = false)
    @JsonProperty
    private UUID uuid;

    public UUID getUuid() {
        return uuid;
    }
}
