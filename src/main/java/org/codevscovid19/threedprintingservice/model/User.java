package org.codevscovid19.threedprintingservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

@MappedSuperclass
public abstract class User extends GeneratedIdentifiable {
    @Column()
    @NotNull
    protected String firebaseId;

    @Column
    @NotNull
    @JsonProperty
    protected String name;

    @Embedded
    @JsonProperty
    protected ContactInformation contactInformation;

    public User() {
    }

    protected User(String firebaseId, @NotNull String name, ContactInformation contactInformation) {
        this.firebaseId = firebaseId;
        this.name = name;
        this.contactInformation = contactInformation;
    }

    public String getFirebaseId() {
        return firebaseId;
    }

    public String getName() {
        return name;
    }

    public ContactInformation getContactInformation() {
        return contactInformation;
    }
}
