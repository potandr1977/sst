package com.poc.sst.account.dtos;

import com.poc.sst.event.AccountCreated;
import com.poc.sst.event.PersonCreated;

public record CreateAccountDto(String personId, String name){
    public AccountCreated toAccountCreatedEvent() {
        var res =  new AccountCreated();
        res.setAccountName(name);
        res.setPersonId(personId);
        return res;
    }
}
