package com.example.emlakburadapayment.model;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class PaymentMessage {
    private long creditCardId;
    private BigDecimal price;
}
