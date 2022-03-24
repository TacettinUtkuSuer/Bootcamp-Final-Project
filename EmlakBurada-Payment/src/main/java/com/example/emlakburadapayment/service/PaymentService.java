package com.example.emlakburadapayment.service;

import com.example.emlakburadapayment.bankService.AtBankPaymentService;
import com.example.emlakburadapayment.model.CreditCard;
import com.example.emlakburadapayment.repository.CreditCardRepository;
import com.example.emlakburadapayment.repository.PaymentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@Service
public class PaymentService extends PaymentBaseService{

    @Autowired
    private CreditCardRepository creditCardRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private AtBankPaymentService atBankPaymentService;

    public boolean pay(long creditCardId, BigDecimal price){

        CreditCard creditCard = creditCardRepository.findById(creditCardId);

        boolean paymentStatus = atBankPaymentService.makePayment(creditCard, price);
         if( paymentStatus ){
             paymentRepository.save(convertFromCreditCardAndPriceToPaymentInfo(creditCard, price));
            return true;
        }

        return false;
    }

}
