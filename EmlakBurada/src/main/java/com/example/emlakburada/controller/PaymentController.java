package com.example.emlakburada.controller;

import com.example.emlakburada.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PaymentController {

   /* @Autowired
    PaymentService paymentService;

    @GetMapping(value = "/pay")
    public ResponseEntity<Boolean> pays(){
        return new ResponseEntity<>(paymentService.pay(),HttpStatus.OK);
    }*/
}
