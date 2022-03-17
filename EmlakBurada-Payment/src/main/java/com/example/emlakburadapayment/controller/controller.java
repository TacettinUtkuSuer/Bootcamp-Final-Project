package com.example.emlakburadapayment.controller;

import com.example.emlakburadapayment.model.CreditCard;
import com.example.emlakburadapayment.model.PaymentInfo;
import com.example.emlakburadapayment.repository.PaymentRepository;
import com.example.emlakburadapayment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;
import java.util.List;

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

    @PostMapping(value = "/savepayment/{id}")
    public ResponseEntity<Boolean> payle(@PathVariable(required = false) long id){

        return new ResponseEntity<>(paymentService.pay(id, new BigDecimal("300")),HttpStatus.OK);

    }

}
