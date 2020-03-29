package org.codevscovid19.threedprintingservice.repositories;

import org.codevscovid19.threedprintingservice.model.Producer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

public interface ProducerRepository extends UserRepository<Producer> {

    Optional<Producer> findByFirebaseId(@NotNull String firebaseId);

    @Query(value = "SELECT * FROM Producer p WHERE ST_Distance_Sphere(ST_MakePoint(?1,?2), ST_MakePoint(p.latitude, p.longitude)) < ?3 ORDER BY ST_Distance_Sphere(ST_MakePoint(?1,?2), ST_MakePoint(p.latitude, p.longitude)) ASC", nativeQuery = true)
    List<Producer> findByLocationAndDistance(Float latitude, Float longitude, Float distance, Pageable pageable);
}
