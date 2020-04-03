package org.codevscovid19.threedprintingservice.repositories;

import org.codevscovid19.threedprintingservice.model.Consumer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

public interface ConsumerRepository extends UserRepository<Consumer> {
    Optional<Consumer> findByFirebaseId(@NotNull String firebaseId);

    @Query(value = "SELECT c.*, ST_DistanceSphere(ST_MakePoint(?1,?2), location) AS distance FROM Consumer c WHERE ST_DistanceSphere(ST_MakePoint(?1,?2), c.location) < ?3 ORDER BY distance ASC", nativeQuery = true)
    List<Consumer> findByLocationAndDistance(Float latitude, Float longitude, Float distance, Pageable pageable);
}
