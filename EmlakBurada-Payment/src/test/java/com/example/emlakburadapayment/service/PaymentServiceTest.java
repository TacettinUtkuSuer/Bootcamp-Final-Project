package com.example.emlakburadapayment.service;

import com.example.emlakburadapayment.bankService.AtBankPaymentService;
import com.example.emlakburadapayment.model.CreditCard;
import com.example.emlakburadapayment.repository.CreditCardRepository;
import com.example.emlakburadapayment.repository.PaymentRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;
import java.util.Optional;

@SpringBootTest
public class PaymentServiceTest {

    @InjectMocks
    PaymentService paymentService;

    @Mock
    CreditCardRepository creditCardRepository;

    @Mock
    PaymentRepository paymentRepository;

    @Mock
    AtBankPaymentService atBankPaymentService;

    @Test
    public void payTest_falsePaymentStatus(){

        //given
        long creditCardId = 1L;
        BigDecimal price = new BigDecimal("100");
        CreditCard creditCard = new CreditCard();
        creditCard.setNameOnCreditCard("Tacettin Utku Suer");
        creditCard.setCreditCardNumber("1111 2222 3333 3847");
        creditCard.setShortFormatExpirationYear("24");
        creditCard.setCVVnumber(456);
        Optional<CreditCard> creditCard1 = Optional.of(creditCard);
        //when
        Mockito.when(creditCardRepository.findById(any())).thenReturn(creditCard1);
        Mockito.when(atBankPaymentService.makePayment(creditCard, price)).thenReturn(false);
        //then
        assertFalse(paymentService.pay(creditCardId,price));
    }


}
