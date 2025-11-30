package com.poc.sst.event;

import com.poc.sst.primitive.DomainEvent;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PaymentCreated extends DomainEvent {

    public String id;

    public String accountId;

    public BigDecimal sum;

    public int paymentTypeCode;

    public String GetEventType()
    {
        return this.getClass().getName();
    }
}
