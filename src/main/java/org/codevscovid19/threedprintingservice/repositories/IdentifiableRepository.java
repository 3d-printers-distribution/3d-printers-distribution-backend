package org.codevscovid19.threedprintingservice.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.UUID;

@NoRepositoryBean
public interface IdentifiableRepository<T> extends CrudRepository<T, UUID> {
}
