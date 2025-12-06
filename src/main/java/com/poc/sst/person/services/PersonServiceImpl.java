package com.poc.sst.person.services;

import com.google.gson.Gson;
import com.poc.sst.annotation.ExecutionTimeCount;
import com.poc.sst.person.repositories.PersonRepository;
import com.poc.sst.person.dtos.CreatePersonDto;
import com.poc.sst.person.entities.Person;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageDeliveryException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.NoSuchElementException;

@Slf4j
@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private PersonBusService busService;

    private final Gson gson;


    @ExecutionTimeCount
    @SneakyThrows
    public Flux<Person> findAll(){
        return personRepository.findAll().onErrorMap(err-> new NoSuchElementException("findAll exception", err));
    }

    public Mono<Person> findById(String id){
        return personRepository
                .findById(id)
                .onErrorMap(ex -> new NoSuchElementException(""));
    }

    public Flux<Person> findByNameContaining(String name){
        return personRepository.findByNameContaining(name);
    }

    public Mono<Person> save(Person person){
        return personRepository
                .save(person)
                .onErrorMap(ex -> new InternalError("Can't save person, id: "+person.getId(), ex));
    }

    @Override
    public Mono<Person> createNewPerson(CreatePersonDto personDto) {
        var person = Person.create(personDto.name(), personDto.inn());
        return busService.publishPersonCreated(person).then(Mono.defer(()->{
                     return save(person)
                       .onErrorMap( ex -> new MessageDeliveryException(
                            "Person was not saved, name: "+personDto.name() + ", inn: "+personDto.inn()));
        }));
    }

    public Mono<Void> update(Person person) {
        return personRepository.findById(person.getId())
                .flatMap(oldPerson -> personRepository.update(oldPerson, person));
    }

    public Mono<Void> deleteById(String id) {
        return personRepository.deleteById(id);
    }

    public Mono<Void> deleteAll() {
        return personRepository.deleteAll();
    }
}

