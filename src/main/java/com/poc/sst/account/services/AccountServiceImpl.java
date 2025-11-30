package com.poc.sst.account.services;

import com.google.gson.Gson;
import com.poc.sst.account.dtos.CreateAccountDto;
import com.poc.sst.account.entity.Account;
import com.poc.sst.account.repositories.AccountRepository;
import com.poc.sst.annotation.ExecutionTimeCount;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageDeliveryException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {


    private AccountRepository accountRepository;
    @Autowired
    private AccountBusService busService;

    private final Gson gson;


    @ExecutionTimeCount
    @SneakyThrows
    public Flux<Account> findAll(){
        return accountRepository.findAll().onErrorMap(err-> new NoSuchElementException("findAll exception", err));
    }

    public Mono<Account> findById(String id){
        return accountRepository
                .findById(id)
                .onErrorMap(ex -> new NoSuchElementException(""));
    }

    public Flux<Account> findByNameContaining(String name){
        return accountRepository.findByNameContaining(name);
    }

    public Mono<Account> save(Account account){
        return accountRepository
                .save(account)
                .onErrorMap(ex -> new InternalError("Can't save account, id: "+account.getId(), ex));
    }

    public Mono<Account> createNewAccount(CreateAccountDto accountDto) {
        return busService.publishAccountCreated(accountDto).then(Mono.defer(()->{
                    var account = Account.create(accountDto.personId(), accountDto.name());
                    return save(account)
                            .onErrorMap( ex -> new MessageDeliveryException(
                                "Account was not saved, name: "+accountDto.name() + ", personId: "+accountDto.personId()));
        }));
    }

    public Mono<Account> update(Account account) {
        return accountRepository.findById(account.getId()).map(Optional::of).defaultIfEmpty(Optional.empty())
                .flatMap(optionalPerson -> {
                    if (optionalPerson.isPresent()) {
                        return accountRepository.save(account);
                    }

                    return Mono.empty();
                });
    }

    public Mono<Void> deleteById(String id) {
        return accountRepository.deleteById(id);
    }

    public Mono<Void> deleteAll() {
        return accountRepository.deleteAll();
    }

}

