package com.poc.sst.payment.enums;

import lombok.Getter;

@Getter
public enum PaymentType {
    DEBIT(1, true),
    CREDIT(2, true);

    private final int code;
    private final Boolean isProfit;

    PaymentType(int code, Boolean isProfit){
        this.code = code;
        this.isProfit = isProfit;
    }

    public static PaymentType fromCode(int code) {
        for(var paymentType : PaymentType.values()) {
            if (paymentType.code == code) {
                return paymentType;
            }
        }
        throw new IllegalArgumentException("There is no payment type with code " + code);
    }
}
