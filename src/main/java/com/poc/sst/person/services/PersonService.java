package com.poc.sst.person.services;

import com.poc.sst.person.dtos.CreatePersonDto;
import com.poc.sst.person.entities.Person;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PersonService {

    Flux<Person> findAll();

    Mono<Person> findById(String id);

    Flux<Person> findByNameContaining(String name);

    Mono<Person> save(Person person);

    Mono<Person> createNewPerson(CreatePersonDto createPersonDto);

    Mono<Void> update(Person person);

    Mono<Void> deleteById(String id);

    Mono<Void> deleteAll();

}
