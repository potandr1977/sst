package com.poc.sst.person;

import com.poc.sst.person.dtos.CreatePersonDto;
import com.poc.sst.person.entities.Person;
import com.poc.sst.person.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

//@CrossOrigin(origins = "http://localhost:8097")
@RestController
@RequestMapping("/api/person")
public class PersonController {

    @Autowired
    PersonService personService;

    @GetMapping("all")
    @ResponseStatus(HttpStatus.OK)
    public Flux<Person> getAllPersons() {
        return personService.findAll();
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Flux<Person> getPersons(@RequestParam(required = false) String title) {
        if (title == null)
            return personService.findAll();
        else
            return personService.findByNameContaining(title);
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Person> getPersonById(@PathVariable("id") String id) {
        return personService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Person> createPerson(@RequestBody CreatePersonDto personDto) {
        return personService.createNewPerson(personDto);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public Mono<Person> updatePerson(@RequestBody Person Person) {
        return personService.update(Person);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deletePerson(@PathVariable("id") String id) {
        return personService.deleteById(id);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteAllPersons() {
        return personService.deleteAll();
    }

    @GetMapping("name")
    @ResponseStatus(HttpStatus.OK)
    public Flux<Person> findByNameContaining(@RequestParam String name) {
        return personService.findByNameContaining(name);
    }
}

