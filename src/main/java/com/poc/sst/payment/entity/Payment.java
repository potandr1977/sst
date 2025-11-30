package com.poc.sst.payment.entity;

import com.poc.sst.payment.enums.PaymentType;
import com.poc.sst.primitive.Entity;

import java.math.BigDecimal;

public class Payment extends Entity {

    private String paymentId;

    private String personId;

    private String accountId;

    private PaymentType paymentType;

    private BigDecimal sum;

    private String purpose;

    private AccountInfo accountInfo;

    private PersonInfo personInfo;
}
