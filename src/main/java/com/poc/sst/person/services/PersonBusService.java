package com.poc.sst.person.services;

import com.poc.sst.person.dtos.CreatePersonDto;
import com.poc.sst.person.entities.Person;
import reactor.core.publisher.Mono;

public interface PersonBusService {
    Mono<Void> publishPersonCreated(Person person);
}
