package com.poc.sst.account.services;

import com.google.gson.Gson;
import com.poc.sst.account.dtos.CreateAccountDto;
import com.poc.sst.person.dtos.CreatePersonDto;
import com.poc.sst.utils.KafkaUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.MessageDeliveryException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AccountBusServiceImpl implements AccountBusService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final Gson gson;

    public Mono<Void> publishAccountCreated(CreateAccountDto accountDto) {

        var payload = gson.toJson(accountDto.toAccountCreatedEvent());

        return Mono.fromFuture(kafkaTemplate.send(KafkaUtils.ACCOUNT_TOPIC_NAME, payload))
                .then()
                .onErrorMap( ex -> new MessageDeliveryException(
                        "AccountCreated event was not sent, name: "+accountDto.name() + ", personId: "+accountDto.personId()));

    }
}
