package com.example.emlakburada.controller;

import com.example.emlakburada.model.models.CreditCard;
import com.example.emlakburada.model.models.User;
import com.example.emlakburada.queue.RabbitMqService;
import com.example.emlakburada.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.math.BigDecimal;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    RabbitMqService rabbitMqService;

    @GetMapping(value = "/hello")
    public ResponseEntity<String> save(){

        User user = new User();
        userRepository.save(user);

        User user2 = userRepository.getById(user.getId());

        CreditCard creditCard = new CreditCard("Tacettin Utku","1234 5678","24",111, user2);
        user2.setCreditCard(creditCard);

        userRepository.save(user2);

        rabbitMqService.sendPayment(user2.getCreditCard().getId(), new BigDecimal("300"));

        return new ResponseEntity<>("Hello", HttpStatus.OK);
    }


}
