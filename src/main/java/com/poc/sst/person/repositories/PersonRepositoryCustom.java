package com.poc.sst.person.repositories;

import com.poc.sst.person.entities.Person;
import reactor.core.publisher.Mono;

public interface PersonRepositoryCustom {
    Mono<Void> update(Person oldPerson, Person newPerson);
}
