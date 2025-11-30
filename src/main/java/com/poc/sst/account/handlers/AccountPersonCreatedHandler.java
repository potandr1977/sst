package com.poc.sst.account.handlers;


import com.poc.sst.account.dtos.CreateAccountDto;
import com.poc.sst.account.services.AccountService;
import com.poc.sst.primitive.EventHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class AccountPersonCreatedHandler extends EventHandler<CreateAccountDto, Mono<Void>> {

    private final AccountService accountService;

    @Override
    public Mono<Void> handle(CreateAccountDto accountDto) {
        this.CheckForRights();

        return accountService.createNewAccount(accountDto).then();
    }
}
