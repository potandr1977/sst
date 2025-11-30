package com.poc.sst.person.dtos;

import com.poc.sst.event.PersonCreated;

public record CreatePersonDto(String personId, String name, String inn){
    public PersonCreated toPersonCreatedEvent() {
        var res =  new PersonCreated();
        res.setPersonId(personId);
        res.setName(name);
        res.setInn(inn);
        return res;
    }
}
