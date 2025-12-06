package com.poc.sst.person.repositories;

import com.poc.sst.person.entities.Person;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import reactor.core.publisher.Mono;

import java.util.Objects;

public class PersonRepositoryCustomImpl implements PersonRepositoryCustom {
    @Autowired
    private ReactiveMongoTemplate reactiveMongoTemplate;

    @Override
    @SneakyThrows
    public Mono<Void> update(Person oldPerson, Person newPerson) {
        if (oldPerson.equals(newPerson)) {
            return Mono.empty();
        }

        Query query = new Query(Criteria.where("_id").is(oldPerson.getId()));
        Update update = new Update();

        if (!Objects.equals(oldPerson.getName(), newPerson.getName())) {
            update.set("name", newPerson.getName());
        }

        if (!Objects.equals(oldPerson.getInn(), newPerson.getInn())) {
            update.set("inn", newPerson.getInn());
        }

        return reactiveMongoTemplate.updateFirst(query, update, Person.class)
                .flatMap(result -> {
                    if (result.getMatchedCount() == 0) {
                        return Mono.error(new Exception("No persons found to update id: " + newPerson.getId()));
                    }
                    return Mono.empty(); // сигнализируем об успешном завершении без данных
                });

    }
}
