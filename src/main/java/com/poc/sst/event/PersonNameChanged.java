package com.poc.sst.event;

import com.poc.sst.primitive.DomainEvent;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonNameChanged extends DomainEvent {

    public String newName;
}
