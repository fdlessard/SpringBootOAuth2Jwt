package io.fdlessard.codebites.oauth2jwt.authorization;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


import java.util.Optional;


@RepositoryRestResource(path = "users")
public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByUsername(String username);

}
