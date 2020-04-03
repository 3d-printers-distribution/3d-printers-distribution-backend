package org.codevscovid19.threedprintingservice.repositories;

import org.codevscovid19.threedprintingservice.model.Producer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

public interface ProducerRepository extends UserRepository<Producer> {

    Optional<Producer> findByFirebaseId(@NotNull String firebaseId);

    @Query(value = "SELECT p.*, ST_DistanceSphere(ST_MakePoint(?1,?2), p.location) as distance FROM Producer p WHERE ST_DistanceSphere(ST_MakePoint(?1,?2), p.location) < ?3 ORDER BY distance ASC", nativeQuery = true)
    List<Producer> findByLocationAndDistance(Float latitude, Float longitude, Float distance, Pageable pageable);
}
