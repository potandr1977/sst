package com.poc.sst.account.services;

import com.poc.sst.account.dtos.CreateAccountDto;
import com.poc.sst.account.entity.Account;
import com.poc.sst.person.dtos.CreatePersonDto;
import com.poc.sst.person.entities.Person;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AccountService {

    Flux<Account> findAll();

    Mono<Account> findById(String id);

    Flux<Account> findByNameContaining(String name);

    Mono<Account> save(Account account);

    Mono<Account> createNewAccount(CreateAccountDto accountDto);

    Mono<Account> update(Account account);

    Mono<Void> deleteById(String id);

    Mono<Void> deleteAll();

}
