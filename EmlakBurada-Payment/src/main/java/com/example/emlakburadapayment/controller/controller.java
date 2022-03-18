package com.example.emlakburadapayment.controller;

import ch.qos.logback.classic.Logger;
import com.example.emlakburadapayment.model.CreditCard;
import com.example.emlakburadapayment.model.PaymentInfo;
import com.example.emlakburadapayment.model.PaymentMessage;
import com.example.emlakburadapayment.repository.PaymentRepository;
import com.example.emlakburadapayment.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.cert.Extension;
import java.util.List;

@Slf4j
@Controller
public class controller {

    @Autowired
    PaymentRepository paymentRepository;

    @GetMapping(value = "/paymentlist")
    public ResponseEntity<List<PaymentInfo>> payList(){
        return new ResponseEntity<>(paymentRepository.findAll(),HttpStatus.OK);
    }

    @Autowired
    PaymentService paymentService;

    @PostMapping(value = "/payandsavepayment")
    public ResponseEntity<Boolean> pay(@RequestBody PaymentMessage paymentMessage) {
        paymentService.pay(paymentMessage.getCreditCardId(), paymentMessage.getPrice());
        log.info("Message has been taken.");
        return new ResponseEntity<>(true,HttpStatus.OK);
    }

}
