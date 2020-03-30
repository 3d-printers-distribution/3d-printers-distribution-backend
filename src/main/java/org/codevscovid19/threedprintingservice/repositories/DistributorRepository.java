package org.codevscovid19.threedprintingservice.repositories;

import org.codevscovid19.threedprintingservice.model.Distributor;

import javax.validation.constraints.NotNull;
import java.util.Optional;

public interface DistributorRepository extends UserRepository<Distributor> {
    Optional<Distributor> findByFirebaseId(@NotNull String firebaseId);
}
