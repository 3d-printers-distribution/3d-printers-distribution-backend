package org.codevscovid19.threedprintingservice.repositories;

import org.codevscovid19.threedprintingservice.model.User;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface UserRepository<T extends User> extends IdentifiableRepository<T> {
}
