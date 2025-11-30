package com.poc.sst.event;

import com.poc.sst.primitive.DomainEvent;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonCreated extends DomainEvent {

    private String personId;

    private String name;

    private String inn;

    public String GetEventType()
    {
        return this.getClass().getName();
    }
}
