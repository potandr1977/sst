package com.poc.sst.account.services;

import com.poc.sst.account.dtos.CreateAccountDto;
import reactor.core.publisher.Mono;

public interface AccountBusService {
    Mono<Void> publishAccountCreated(CreateAccountDto accountDto);
}
