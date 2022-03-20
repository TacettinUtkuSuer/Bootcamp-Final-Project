package com.example.emlakburada.service;

import com.example.emlakburada.model.models.CreditCard;
import com.example.emlakburada.model.models.PaymentMessage;
import com.example.emlakburada.model.models.User;
import com.example.emlakburada.repository.InfoRepository;
import com.example.emlakburada.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@Slf4j
@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    InfoRepository infoRepository;

    public String pay(long userId){

        User user = userRepository.getById(userId);

        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:9092/payandsavepayment";

        PaymentMessage paymentMessage = new PaymentMessage(user.getCreditCard().getId(), new BigDecimal(infoRepository.findByKey("Price").getValue()));
        ResponseEntity<Boolean> responseEntity = restTemplate.postForEntity(url,paymentMessage,Boolean.class);

        String message;
        if (responseEntity.getBody()){
            message = "The payment is successful.";
            log.info(message);
        } else {
            message = "There was a problem with the payment.";
            log.warn(message);
        }
        return message;
    }
}
