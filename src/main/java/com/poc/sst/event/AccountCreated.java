package com.poc.sst.event;

import com.poc.sst.primitive.DomainEvent;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountCreated extends DomainEvent {

    private String accountName;

    private String personId;

    public String GetEventType() { return this.getClass().getName(); }
}
