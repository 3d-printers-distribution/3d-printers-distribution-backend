package org.codevscovid19.threedprintingservice.repositories;

import org.codevscovid19.threedprintingservice.model.Consumer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

public interface ConsumerRepository extends UserRepository<Consumer> {
    Optional<Consumer> findByFirebaseId(@NotNull String firebaseId);

    @Query(value = "SELECT * FROM Consumer c WHERE ST_Distance_Sphere(ST_MakePoint(?1,?2), ST_MakePoint(c.latitude, c.longitude)) < ?3 ORDER BY ST_Distance_Sphere(ST_MakePoint(?1,?2), ST_MakePoint(c.latitude, c.longitude)) ASC", nativeQuery = true)
    List<Consumer> findByLocationAndDistance(Float latitude, Float longitude, Float distance, Pageable pageable);
}
