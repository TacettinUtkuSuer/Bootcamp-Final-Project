package com.example.emlakburada.model.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
public class PaymentMessage {
    private long creditCardId;
    private BigDecimal price;
}
