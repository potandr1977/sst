package com.poc.sst.payment.dtos;

import java.math.BigDecimal;

public record AddPaymentDto(String accountId, BigDecimal sum, int paymentTypeCode){
}