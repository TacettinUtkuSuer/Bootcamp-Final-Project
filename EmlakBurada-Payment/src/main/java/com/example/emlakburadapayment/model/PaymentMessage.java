package com.example.emlakburadapayment.model;

import lombok.Getter;

@Getter
public class PaymentMessage {
    private CreditCard creditCardId;
    private String price;
}
