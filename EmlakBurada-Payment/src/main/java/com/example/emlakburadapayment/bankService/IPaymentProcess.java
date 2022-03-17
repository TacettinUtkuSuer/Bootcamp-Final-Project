package com.example.emlakburadapayment.bankService;

import com.example.emlakburadapayment.model.CreditCard;

import java.math.BigDecimal;

public interface IPaymentProcess {

    // A comprehensive interface has been created so that other bank transactions can make.
    public boolean makePayment(CreditCard creditCard, BigDecimal price);
}
