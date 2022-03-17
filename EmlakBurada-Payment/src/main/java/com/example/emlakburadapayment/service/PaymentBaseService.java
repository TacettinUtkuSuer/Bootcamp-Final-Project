package com.example.emlakburadapayment.service;


import com.example.emlakburadapayment.model.CreditCard;
import com.example.emlakburadapayment.model.PaymentInfo;

import java.math.BigDecimal;

public class PaymentBaseService {

    public PaymentInfo convertFromCreditCardAndPriceToPaymentInfo(CreditCard creditCard, BigDecimal price){

        PaymentInfo paymentInfo = new PaymentInfo();

        paymentInfo.setNameOnCreditCard(creditCard.getNameOnCreditCard());
        String lastFourDigitsOfCreditCardNumber = "**** **** **** " + creditCard.getCreditCardNumber().substring(creditCard.getCreditCardNumber().length()-5);
        paymentInfo.setCreditCardNumber(lastFourDigitsOfCreditCardNumber);
        paymentInfo.setPrice(price);

        return paymentInfo;
    }

}
