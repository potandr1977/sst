package com.poc.sst.person.services;

import com.google.gson.Gson;
import com.poc.sst.event.PersonCreated;
import com.poc.sst.person.entities.Person;
import com.poc.sst.utils.KafkaUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.MessageDeliveryException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class PersonBusServiceImpl implements PersonBusService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final Gson gson;

    @Override
    public Mono<Void> publishPersonCreated(Person person) {

        var personCreated = new PersonCreated();
        personCreated.setPersonId(person.getId());
        personCreated.setName(person.getName());
        personCreated.setInn(person.getInn());
        var payload = gson.toJson(personCreated);

        return Mono.fromFuture(kafkaTemplate.send(KafkaUtils.PERSON_TOPIC_NAME, payload))
                .then()
                .onErrorMap( ex -> new MessageDeliveryException(
                        "PersonCreated event was not sent, personId: "+ person.getId()));

    }
}
