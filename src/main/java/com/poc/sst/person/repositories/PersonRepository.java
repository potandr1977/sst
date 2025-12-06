package com.poc.sst.person.repositories;

import com.poc.sst.person.entities.Person;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface PersonRepository extends ReactiveMongoRepository<Person, String>, PersonRepositoryCustom {
    Flux<Person> findByNameContaining(String name);
}
