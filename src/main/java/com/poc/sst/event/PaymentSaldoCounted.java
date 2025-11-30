package com.poc.sst.event;

import com.poc.sst.primitive.DomainEvent;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PaymentSaldoCounted extends DomainEvent {

    /*
    public PaymentSaldoCounted() {
        this.setType(this.getClass().getName());
    }
    */

    private String accountId;

    private String personId;

    private BigDecimal saldo;
}
