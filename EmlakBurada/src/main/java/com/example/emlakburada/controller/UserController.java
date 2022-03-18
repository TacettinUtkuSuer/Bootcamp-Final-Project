package com.example.emlakburada.controller;

import com.example.emlakburada.model.models.CreditCard;
import com.example.emlakburada.model.models.PaymentMessage;
import com.example.emlakburada.model.models.User;
import com.example.emlakburada.queue.RabbitMqService;
import com.example.emlakburada.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@Slf4j
@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = "/hello")
    public ResponseEntity<String> save(){

        User user = new User();
        userRepository.save(user);

        User user2 = userRepository.getById(user.getId());

        CreditCard creditCard = new CreditCard("Tacettin Utku","1234 5678","24",111, user2);
        user2.setCreditCard(creditCard);

        userRepository.save(user2);

        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:9091/payandsavepayment";
        PaymentMessage paymentMessage = new PaymentMessage(user2.getCreditCard().getId(), new BigDecimal("400"));
        ResponseEntity<Boolean> responseEntity = restTemplate.postForEntity(url,paymentMessage,Boolean.class);
        if (responseEntity.getBody()){
            log.info("The payment is successful.");
        } else {
            log.warn("There was a problem with the payment.");
        }

        return new ResponseEntity<>("Hello", HttpStatus.OK);
    }


}
