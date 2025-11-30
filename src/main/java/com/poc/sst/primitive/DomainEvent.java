package com.poc.sst.primitive;

import com.poc.sst.event.PaymentSaldoCounted;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
public class DomainEvent {

    private String id = UUID.randomUUID().toString();

    private String type = this.getClass().getName();
}
