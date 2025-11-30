package com.poc.sst.person.handlers;


import com.poc.sst.account.dtos.CreateAccountDto;
import com.poc.sst.account.services.AccountService;
import com.poc.sst.event.PersonCreated;
import com.poc.sst.primitive.EventHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class PersonCreatedHandler extends EventHandler<PersonCreated, Mono<Void>> {

    private final AccountService accountService;

    @Override
    public Mono<Void> handle(PersonCreated personCreated) {
        this.CheckForRights();

        var accountDto = new CreateAccountDto(personCreated.getPersonId(),"Default Account");
        return accountService
                .createNewAccount(accountDto)
                .then();

    }
}
