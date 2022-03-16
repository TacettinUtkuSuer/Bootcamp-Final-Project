package com.example.emlakburada.model.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class PaymentMessage {
    private long creditCardId;
    private String price;
}
