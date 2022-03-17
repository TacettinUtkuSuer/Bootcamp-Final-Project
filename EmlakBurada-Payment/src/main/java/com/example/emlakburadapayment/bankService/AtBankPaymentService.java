package com.example.emlakburadapayment.bankService;

import com.example.emlakburadapayment.model.CreditCard;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AtBankPaymentService implements IPaymentProcess {

    public boolean makePayment(CreditCard creditCard, BigDecimal price){
        // Payment transactions will be included in this method.
        return true;
    }
}
